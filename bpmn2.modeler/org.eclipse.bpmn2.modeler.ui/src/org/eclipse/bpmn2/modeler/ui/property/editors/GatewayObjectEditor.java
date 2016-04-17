/******************************************************************************* 
 * Copyright (c) 2011, 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Delacyr Almeida Monteiro Ferreira
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

public class GatewayObjectEditor extends ComboObjectEditor {

	public static String AND_LABEL = Messages.GatewayObjectEditor_And_Label;
	public static String AND_VALUE = "##AND"; //$NON-NLS-1$
	public static String OR_LABEL = Messages.GatewayObjectEditor_Or_Label;
	public static String OR_VALUE = "##OR"; //$NON-NLS-1$
	public static String XOR_LABEL = Messages.GatewayObjectEditor_Xor_Label;
	public static String XOR_VALUE = "##XOR"; //$NON-NLS-1$
	
	public GatewayObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature) {
		super(parent, object, feature);
	}

	public GatewayObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature,
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
		if (AND_VALUE.equals(value)) {
			value = AND_LABEL;
		}
		else if (OR_VALUE.equals(value)) {
			value = OR_LABEL;
		}
		else if (XOR_VALUE.equals(value)) {
			value = XOR_LABEL;
		}
		
		return value;
	}
	
	protected EObject createObject() throws Exception {
		Hashtable<String,Object> choices = getChoiceOfValues(object, feature);
		GatewayEditingDialog dialog = new GatewayEditingDialog(
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
		GatewayEditingDialog dialog = new GatewayEditingDialog(
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
								EStructuralFeature f = o.eClass().getEStructuralFeature("type"); //$NON-NLS-1$
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
		choices.put(AND_LABEL, ModelUtil.createStringWrapper(AND_VALUE));
		choices.put(OR_LABEL, ModelUtil.createStringWrapper(OR_VALUE));
		choices.put(XOR_LABEL, ModelUtil.createStringWrapper(XOR_VALUE));
		Hashtable<String, Object> otherChoices = ModelUtil.getChoiceOfValues(object, feature);
		if (otherChoices!=null)
			choices.putAll(otherChoices);

		Definitions definitions = ModelUtil.getDefinitions(object);
		if (definitions!=null) {
			TreeIterator<EObject> iter = definitions.eAllContents();
			while (iter.hasNext()) {
				EObject o = iter.next();
				EStructuralFeature f = o.eClass().getEStructuralFeature("type"); //$NON-NLS-1$
				if (f!=null) {
					String gateway = (String)o.eGet(f);
					if (gateway!=null && !gateway.isEmpty() &&
							!gateway.startsWith("##")) { //$NON-NLS-1$
						if (!choices.containsKey(gateway)) {
							choices.put(gateway, ModelUtil.createStringWrapper(gateway));
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
			if (notification.getFeature() instanceof EStructuralFeature) {
				EStructuralFeature f = (EStructuralFeature)notification.getFeature();
				if (f!=null && (f.getName().equals(feature.getName()) ||
						f.getName().equals("mixed")) ) { // handle the case of FormalExpression.body //$NON-NLS-1$
					super.notifyChanged(notification);
				}
			}
		}
	}
	
	public class GatewayEditingDialog extends InputDialog {
		public GatewayEditingDialog(Shell shell, String title, final Map<String,Object> choices, final String uriString) {
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
