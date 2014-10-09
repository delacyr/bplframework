/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013 Red Hat, Inc.
 * All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	Red Hat, Inc. - initial API and VarPoint
 * 
 * @author
 * 	Marcelo F. Terenciani 2014-09-18 
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property.editors;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;

public class VarPointTypeObjectEditor extends ComboObjectEditor {

	public static String ABSTRACT_LABEL = Messages.VarPointTypeObjectEditor_Abstract_Label;
	public static String ABSTRACT_VALUE = "##abstract"; //$NON-NLS-1$
	public static String ALTERNATIVE_LABEL = Messages.VarPointTypeObjectEditor_Alternative_Label;
	public static String ALTERNATIVE_VALUE = "##alternative"; //$NON-NLS-1$
	public static String COMBINED_LABEL = Messages.VarPointTypeObjectEditor_Combined_Label;
	public static String COMBINED_VALUE = "##combined"; //$NON-NLS-1$
	public static String NULL_LABEL = Messages.VarPointTypeObjectEditor_Null_Label;
	public static String NULL_VALUE = "##null"; //$NON-NLS-1$
	public static String OPTIONAL_LABEL = Messages.VarPointTypeObjectEditor_Optional_Label;
	public static String OPTIONAL_VALUE = "##optional"; //$NON-NLS-1$
	public static String VARPOINT_LABEL = Messages.VarPointTypeObjectEditor_Var_Point_Label;
	public static String VARPOINT_VALUE = "##varpoint"; //$NON-NLS-1$
	public static String NONE_LABEL = ""; //$NON-NLS-1$
	

	
	public VarPointTypeObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature) {
		super(parent, object, feature);
	}

	public VarPointTypeObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature,
			EClass featureEType) {
		super(parent, object, feature, featureEType);
	}

	@Override
	public boolean setValue(Object result) {
		if (ModelUtil.isStringWrapper(result)) {
			result = ModelUtil.getStringWrapperValue(result);
		}
		return super.setValue(result);
	}
	
	public Object getValue() {
		Object value = object.eGet(feature);
		if (ABSTRACT_VALUE.equals(value)) {
			value = ABSTRACT_LABEL;
		}
		else if (ALTERNATIVE_VALUE.equals(value)) {
			value = ALTERNATIVE_LABEL;
		}
		else if (COMBINED_VALUE.equals(value)) {
			value = COMBINED_LABEL;
		}
		else if (NULL_VALUE.equals(value)) {
			value = NULL_LABEL;
		}
		else if (OPTIONAL_VALUE.equals(value)) {
			value = OPTIONAL_LABEL;
		}
		else if (VARPOINT_VALUE.equals(value)) {
			value = VARPOINT_LABEL;
		}
		if (value == null)
			value = NONE_LABEL;
		return value;
	}
	
	protected EObject createObject() throws Exception {
		Hashtable<String,Object> choices = getChoiceOfValues(object, feature);
		VarPointEditingDialog dialog = new VarPointEditingDialog(
				getDiagramEditor().getEditorSite().getShell(), 
				Messages.VarPointTypeObjectEditor_Create_New_Title, 
				choices, null);
		if ( dialog.open() == Window.OK)
			return ModelUtil.createStringWrapper( dialog.getValue() );
		throw new OperationCanceledException(Messages.VarPointTypeObjectEditor_Dialog_Cancelled);
	}
	
	protected EObject editObject(EObject value) throws Exception {
		Hashtable<String,Object> choices = getChoiceOfValues(object, feature);
		final String oldValue = ModelUtil.getStringWrapperValue(value);
		VarPointEditingDialog dialog = new VarPointEditingDialog(
				getDiagramEditor().getEditorSite().getShell(), 
				Messages.VarPointTypeObjectEditor_Edit_Title, 
				choices, oldValue);
		if ( dialog.open() == Window.OK) {
			final String newValue = dialog.getValue();
			if (!newValue.equals(value)) {
				final Definitions definitions = ModelUtil.getDefinitions(object);
				if (definitions!=null) {
					TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							TreeIterator<EObject> iter = definitions.eAllContents();
							while (iter.hasNext()) {
								EObject o = iter.next();
								EStructuralFeature f = o.eClass().getEStructuralFeature("varPointType"); //$NON-NLS-1$
								if (f!=null) {
									String varPoint = (String)o.eGet(f);
									if (oldValue.equals(varPoint)) {
										o.eSet(f, newValue);
									}
								}
							}
						}
					});
				}
	
				return ModelUtil.createStringWrapper( dialog.getValue() );
			}
		}
		throw new OperationCanceledException(Messages.VarPointTypeObjectEditor_Dialog_Cancelled);
	}
	
	protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature) {
		Hashtable<String, Object> choices = new Hashtable<String, Object>();
		choices.put(ABSTRACT_LABEL, ModelUtil.createStringWrapper(ABSTRACT_VALUE));
		choices.put(ALTERNATIVE_LABEL, ModelUtil.createStringWrapper(ALTERNATIVE_VALUE));
		choices.put(COMBINED_LABEL, ModelUtil.createStringWrapper(COMBINED_VALUE));
		choices.put(NULL_LABEL, ModelUtil.createStringWrapper(NULL_VALUE));
		choices.put(OPTIONAL_LABEL, ModelUtil.createStringWrapper(OPTIONAL_VALUE));
		choices.put(VARPOINT_LABEL, ModelUtil.createStringWrapper(VARPOINT_VALUE));
		Hashtable<String, Object> otherChoices = ModelUtil.getChoiceOfValues(object, feature);
		if (otherChoices!=null)
			choices.putAll(otherChoices);

		Definitions definitions = ModelUtil.getDefinitions(object);
		if (definitions!=null) {
			TreeIterator<EObject> iter = definitions.eAllContents();
			while (iter.hasNext()) {
				EObject o = iter.next();
				EStructuralFeature f = o.eClass().getEStructuralFeature("varPointType"); //$NON-NLS-1$
				if (f!=null) {
					String varPoint = (String)o.eGet(f);
					if (varPoint!=null && !varPoint.isEmpty() &&
							!varPoint.startsWith("##")) { //$NON-NLS-1$
						if (!choices.containsKey(varPoint)) {
							choices.put(varPoint, ModelUtil.createStringWrapper(varPoint));
						}
					}
				}
			}
		}
		return choices;
	}
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == -1) {
			//updateText();
			super.notifyChanged(notification);
		}
		else if (object == notification.getNotifier()) {
			// TODO: Verifica��o no xml do featuremodel se existe a tag
			if (notification.getFeature() instanceof EStructuralFeature) {
				EStructuralFeature f = (EStructuralFeature)notification.getFeature();
				if (f!=null && (f.getName().equals(feature.getName()) ||
						f.getName().equals("mixed")) ) { // handle the case of FormalExpression.body //$NON-NLS-1$
					super.notifyChanged(notification);
				}
			}
		}
	}
	
	public class VarPointEditingDialog extends InputDialog {
		public VarPointEditingDialog(Shell shell, String title, final Map<String,Object> choices, final String uriString) {
			super(
					shell,
					title,
					Messages.VarPointTypeObjectEditor_VarPoint_Title,
					uriString,
					new IInputValidator() {

						@Override
						public String isValid(String newText) {
							if (newText==null || newText.isEmpty())
								return Messages.VarPointTypeObjectEditor_Invalid_Empty;
							if (newText.equals(uriString))
								return null;
							if (choices.containsKey(newText) || choices.containsValue(newText))
								return NLS.bind(Messages.VarPointTypeObjectEditor_Invalid_Duplicate,newText);
							URI uri = URI.createURI(newText);
							if (!(uri.hasAuthority() && uri.scheme()!=null)) {
								return Messages.VarPointTypeObjectEditor_Invalid_URI;
							}
							return null;
						}
					}
				);
		}
	}
}