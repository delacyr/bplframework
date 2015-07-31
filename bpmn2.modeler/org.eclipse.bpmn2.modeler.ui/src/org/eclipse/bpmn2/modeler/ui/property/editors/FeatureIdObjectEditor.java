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
 * @author Marcelo Figueiredo Terenciani
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property.editors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
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

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.xml.XmlFeatureModelReader;
/*
import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.xml.XmlFeatureModelReader;
*/
public class FeatureIdObjectEditor extends ComboObjectEditor {


	
	public FeatureIdObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature) {
		super(parent, object, feature);
	}

	public FeatureIdObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature,
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
		return value;
	}
	
	protected EObject createObject() throws Exception {
		Hashtable<String,Object> choices = getChoiceOfValues(object, feature);
		VariantEditingDialog dialog = new VariantEditingDialog(
				getDiagramEditor().getEditorSite().getShell(), 
				Messages.VariantTypeObjectEditor_Create_New_Title, 
				choices, null);
		if ( dialog.open() == Window.OK)
			return ModelUtil.createStringWrapper( dialog.getValue() );
		throw new OperationCanceledException(Messages.VariantTypeObjectEditor_Dialog_Cancelled);
	}
	
	protected EObject editObject(EObject value) throws Exception {
		Hashtable<String,Object> choices = getChoiceOfValues(object, feature);
		final String oldValue = ModelUtil.getStringWrapperValue(value);
		VariantEditingDialog dialog = new VariantEditingDialog(
				getDiagramEditor().getEditorSite().getShell(), 
				Messages.VariantTypeObjectEditor_Edit_Title, 
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
								EStructuralFeature f = o.eClass().getEStructuralFeature("featureId"); //$NON-NLS-1$
								if (f!=null) {
									String variant = (String)o.eGet(f);
									if (oldValue.equals(variant)) {
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
		throw new OperationCanceledException(Messages.VariantTypeObjectEditor_Dialog_Cancelled);
	}
	
	protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature) {
		Hashtable<String, Object> choices = new Hashtable<String, Object>();

		String name = ModelUtil.getDiagramEditor(object).getPartName();
		String root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		String pathString =  object.eResource().getURI().toPlatformString(true);
		IFile bpmnfile = ResourcesPlugin.getWorkspace().getRoot().getFile(org.eclipse.core.runtime.Path.fromOSString(pathString));
		IProject project = bpmnfile.getProject();

		FeatureModel featureModel = new FeatureModel();
		File file = new File(root+"/"+project.getName()+"/FeatureModel/"+name+".xml");
		try {
			new XmlFeatureModelReader(featureModel).readFromFile(file);
			for (String f : featureModel.getFeatureNames()) {
				choices.put(f, ModelUtil.createStringWrapper(f));
			}
		} catch (FileNotFoundException f ) {
			
		} catch (UnsupportedModelException e){
			
		}
		Hashtable<String, Object> otherChoices = ModelUtil.getChoiceOfValues(object, feature);
		if (otherChoices!=null)
			choices.putAll(otherChoices);

		Definitions definitions = ModelUtil.getDefinitions(object);
		if (definitions!=null) {
			TreeIterator<EObject> iter = definitions.eAllContents();
			while (iter.hasNext()) {
				EObject o = iter.next();
				EStructuralFeature f = o.eClass().getEStructuralFeature("featureId"); //$NON-NLS-1$
				if (f!=null) {
					String variant = (String)o.eGet(f);
					if (variant!=null && !variant.isEmpty() &&
							!variant.startsWith("##")) { //$NON-NLS-1$
						if (!choices.containsKey(variant)) {
							choices.put(variant, ModelUtil.createStringWrapper(variant));
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
	
	public class VariantEditingDialog extends InputDialog {
		public VariantEditingDialog(Shell shell, String title, final Map<String,Object> choices, final String uriString) {
			super(
					shell,
					title,
					Messages.VariantTypeObjectEditor_Variant_Title,
					uriString,
					new IInputValidator() {

						@Override
						public String isValid(String newText) {
							if (newText==null || newText.isEmpty())
								return Messages.VariantTypeObjectEditor_Invalid_Empty;
							if (newText.equals(uriString))
								return null;
							if (choices.containsKey(newText) || choices.containsValue(newText))
								return NLS.bind(Messages.VariantTypeObjectEditor_Invalid_Duplicate,newText);
							URI uri = URI.createURI(newText);
							if (!(uri.hasAuthority() && uri.scheme()!=null)) {
								return Messages.VariantTypeObjectEditor_Invalid_URI;
							}
							return null;
						}
					}
				);
		}
	}
}
