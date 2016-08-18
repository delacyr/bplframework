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

package org.eclipse.bpmn2.modeler.ui.property.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.BooleanObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.editors.FeatureIdObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.VarPointTypeObjectEditor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.xml.XmlFeatureModelReader;



/**
 * This class renders the property sheet tab for I/O Parameters defined in
 * Activities, CallableElements and ThrowEvents.
 * 
 * TODO: handle ThrowEvent parameters
 */
public class VrSpecificationDetailComposite extends AbstractDetailComposite {

	protected VarPointTypeObjectEditor varPointEditor;
	
	protected ObjectEditor varPoint;
	protected ObjectEditor variant;
	protected FeatureIdObjectEditor featureIdEditor; 
	protected Button noneButton;
	protected Button mandatoryButton;
	protected Button optionalButton;
	public FeatureModel featureModel;
	Composite buttonComposite;
	Label label;

	public VrSpecificationDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public VrSpecificationDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider == null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] { "varPoint", //VrTask //$NON-NLS-1$
						"varPointType", //VrTask //$NON-NLS-1$
						"variant", //VrTask //$NON-NLS-1$
						"featureType", //VrTask //$NON-NLS-1$
						"featureId", //VrTask //$NON-NLS-1$
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		redrawParent();
	}
	@Override
	public void cleanBindings() {
		super.cleanBindings();
		varPointEditor = null;
		varPoint = null;
		variant = null;
		featureIdEditor = null;
	}
//	BPL2.0
	public void createBindings(EObject be) {
		String name = ModelUtil.getDiagramEditor(be).getPartName();
		String root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		String pathString =  be.eResource().getURI().toPlatformString(true);
		IFile bpmnfile = ResourcesPlugin.getWorkspace().getRoot().getFile(org.eclipse.core.runtime.Path.fromOSString(pathString));
		IProject project = bpmnfile.getProject();
		Boolean pass;
		featureModel = new FeatureModel();
		File file = new File(root+"/"+project.getName()+"/FeatureModel/"+project.getName()+"_FM"+".xml");
//		File file = new File(root+"/"+project.getName()+"/FeatureModel/"+name+".xml");
//		File file = new File(BPMN2Editor.getActiveEditor().getBpmnDiagram().getFeatureModel());
		try {
			pass=true;
			new XmlFeatureModelReader(featureModel).readFromFile(file);
//			new XmlFeatureModelReader(featureModel).readFromString(BPMN2Editor.getActiveEditor().getBpmnDiagram().getFeatureModel());
	

		} 
		catch (FileNotFoundException f ) {
			pass=false;
//			MessageDialog.openError(null,"Error","N�o � possivel encontrar o Feature Model, verifique a exist�ncia do arquivo "+name+".xml"+" na pasta /FeatureModel");
			MessageDialog.openError(null, "Error", "Please, check if there is a FeatureModel/"+project.getName()+"_FM"+".xml");
			

			
		} 
		catch (UnsupportedModelException e){
			pass=false;
		}
		
		
		createWidgetsVarPoint(be);
		createWidgetsVarPointType(be);
		createWidgetsVariant(be);
		createWidgetsFeatureId(be);
		createWidgetButons(be);
		
		if (!pass){
			variant.setVisible(false);
			label.setVisible(false);
			varPoint.setVisible(false);
			buttonComposite.setVisible(false);
			featureIdEditor.setVisible(false);
			varPointEditor.setVisible(false);
		}
		
	}
	private void createWidgetButons(EObject be) {
		EStructuralFeature f = be.eClass().getEStructuralFeature("featureType"); //$NON-NLS-1$
		String name="";
		if (f!=null) {
			if ((String)be.eGet(f) != null)
				name = (String)be.eGet(f);
			else
				name = "##none";
		}
		
			
		Composite composite = getAttributesParent();
		
		label = toolkit.createLabel(composite, Messages.VrSpecificationDetailComposite_Feature_Type_Label);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		buttonComposite = toolkit.createComposite(composite);
		buttonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		FillLayout layout = new FillLayout();
		layout.marginWidth = 20;
		buttonComposite.setLayout(layout);
		
		mandatoryButton = toolkit.createButton(buttonComposite, Messages.VrSpecificationDetailComposite_Mandatory, SWT.RADIO);
		mandatoryButton.setSelection(name.equals("##mandatory"));
		mandatoryButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (mandatoryButton.getSelection()) {
					TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							/*Definitions definitions = ModelUtil.getDefinitions(businessObject);
							TreeIterator<EObject> iter = definitions.eAllContents();
							while (iter.hasNext()) {
								EObject obj = iter.next();
								if (obj instanceof SequenceFlow){
									SequenceFlow flow = (SequenceFlow)obj;
									if ((flow.getSourceRef() instanceof Activity) && (flow.getSourceRef() == businessObject)){
										redo();
										redraw();
										redrawPage();
										redrawParent();
										refresh();
									}
								}
							}*/
							EStructuralFeature feature = businessObject.eClass().getEStructuralFeature("featureType"); //$NON-NLS-1$
							businessObject.eSet(feature, "##mandatory");
							setBusinessObject(businessObject);
							
						}
					});
				}
			}
		});
		optionalButton = toolkit.createButton(buttonComposite, Messages.VrSpecificationDetailComposite_Optional, SWT.RADIO);
		optionalButton.setSelection(name.equals("##optional"));
		optionalButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				
				if (optionalButton.getSelection()) {
					TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							EStructuralFeature feature = businessObject.eClass().getEStructuralFeature("featureType"); //$NON-NLS-1$
							businessObject.eSet(feature, "##optional");
							setBusinessObject(businessObject);
						}
					});
				}
			}
		});
		noneButton = toolkit.createButton(buttonComposite, Messages.VrSpecificationDetailComposite_None, SWT.RADIO);
		noneButton.setSelection(name.equals("##none"));
		noneButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				
				if (noneButton.getSelection()) {
					TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							EStructuralFeature feature = businessObject.eClass().getEStructuralFeature("featureType"); //$NON-NLS-1$
							businessObject.eSet(feature, null);
							setBusinessObject(businessObject);
						}
					});
					
				}
			}
		});
	}
	
	private void createWidgetsVarPoint(EObject be) {
		EStructuralFeature varPointE = getFeature(be, "varPoint");
		varPoint = new BooleanObjectEditor(this, be, varPointE){
			protected boolean setValue(final Object result) {
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if (hasVariant())
							MessageDialog.openError(null,"Error",Messages.VrSpecificationDetailComposite_Remove_Input_Association);
						else{
							object.eSet(feature, button.getSelection());
							if (button.getSelection()){
								varPointEditor.setValue("##AND");
							}else{							
								if (!(button.getSelection()) && !(Boolean)variant.getValue()){
									EStructuralFeature feature = object.eClass().getEStructuralFeature("featureType"); //$NON-NLS-1$
									object.eSet(feature, null);
									noneButton.setSelection(true);
									optionalButton.setSelection(false);
									mandatoryButton.setSelection(false);
								}
							}
							redrawParent();
						}
					}
				});
				return true;
			}
		};
			
		
		varPoint.createControl(getAttributesParent(), "    Is VarPoint");	
	}
	public boolean hasVariant() {
		if (businessObject instanceof Activity){
			Activity activity = (Activity) businessObject;
			Activity source;
			List<? extends SequenceFlow> sequenceFlow = null;
			sequenceFlow = activity.getIncoming();
			if (sequenceFlow != null) {
				for (SequenceFlow a : sequenceFlow) {
					if (a.getSourceRef() instanceof Activity){
						source = (Activity) a.getSourceRef();
						if (source.isVariant())
							return true;
					}
				}
			}
		}/*
		if (businessObject instanceof ItemAwareElement){
			Definitions definitions = ModelUtil.getDefinitions(businessObject);
			TreeIterator<EObject> iter = definitions.eAllContents();
			while (iter.hasNext()) {
				EObject obj = iter.next();
				if (obj instanceof DataOutputAssociation &&
					((DataOutputAssociation)obj).getTargetRef() == businessObject) {
					return true;
				}
				if (obj instanceof DataInputAssociation &&
						((DataInputAssociation)obj).getSourceRef() == businessObject) {
						return true;
					}
			}				
		}*/
		return false;
	}
	

	private void createWidgetsVarPointType(EObject be) {
		EStructuralFeature feature = getFeature(be, "varPointType");
		varPointEditor = new VarPointTypeObjectEditor(this, be, feature){
			@Override
			public void setVisible(boolean visible) {
				if (!visible)
					setValue(null);
				super.setVisible(visible);
			}
			@Override
			public boolean setValue(Object result) {
				if (hasVariant())
					MessageDialog.openError(null,"Error",Messages.VrSpecificationDetailComposite_Remove_Input_Association);
				else{
					return super.setValue(result);
				}
				return false;
				
			}
		};
		varPointEditor.createControl(getAttributesParent(), "VarPoint Type");
	}
	

	private void createWidgetsFeatureId(EObject be) {
		EStructuralFeature feature = getFeature(be, "featureId");
		featureIdEditor = new FeatureIdObjectEditor(this, be, feature){
			@Override
			public void setVisible(boolean visible) {
				if (!visible)
					setValue(null);
				super.setVisible(visible);
			}
		};
		featureIdEditor.createControl(getAttributesParent(), "Feature ID");
	}

	private void createWidgetsVariant(EObject be) {
		EStructuralFeature variantE = getFeature(be, "variant");
		variant = new BooleanObjectEditor(this, be, variantE) {
			@Override

			protected boolean setValue(final Object result) {
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if (canChange()){
							object.eSet(feature, button.getSelection());
							if (!(button.getSelection()) && !(Boolean)varPoint.getValue()){
								EStructuralFeature feature = businessObject.eClass().getEStructuralFeature("featureType"); //$NON-NLS-1$
								object.eSet(feature, null);
								noneButton.setSelection(true);
								optionalButton.setSelection(false);
								mandatoryButton.setSelection(false);
							}
							redrawParent();
						}
					}
				});
				return true;
			}
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				redrawParent();
				
			}
			private boolean canChange() {
				
				if (businessObject instanceof Activity){
					Activity activity = (Activity) businessObject;
					
					List<? extends SequenceFlow> sequenceFlow = null;
					sequenceFlow = activity.getIncoming();
					if (sequenceFlow.size() > 0) {
						MessageDialog.openError(null,"Error",Messages.VrSpecificationDetailComposite_Remove_Input_Association);
						return false;
					}
					sequenceFlow=null;
					sequenceFlow = activity.getOutgoing();
					if (sequenceFlow.size()>0) {
						MessageDialog.openError(null,"Error",Messages.VrSpecificationDetailComposite_Remove_Output_Association);
						return false;
					}
				}
				if (businessObject instanceof ItemAwareElement){
					/*					
					Definitions definitions = ModelUtil.getDefinitions(businessObject);
					TreeIterator<EObject> iter = definitions.eAllContents();
					while (iter.hasNext()) {
						EObject obj = iter.next();
						if (obj instanceof DataOutputAssociation &&
							((DataOutputAssociation)obj).getTargetRef() == businessObject) {
							MessageDialog.openError(null,"Error",Messages.VrSpecificationDetailComposite_Remove_Input_Association);
							return false;
						}
						if (obj instanceof DataInputAssociation){
							DataInputAssociation data = (DataInputAssociation)obj;	
							List<ItemAwareElement> itemAE = data.getSourceRef();
							for (ItemAwareElement i: itemAE){
								if (i==businessObject){
								MessageDialog.openError(null,"Error",Messages.VrSpecificationDetailComposite_Remove_Output_Association);
								return false;
								}
							}
						}
					}
					ItemAwareElement itemAE = (ItemAwareElement)businessObject;
					// Se o elemento possui Data input association ele n�o pode se tornar uma variant 
					// (uma variant deve estar conectada � um varPoint)
					if (itemAE.getDataInputAssociations().size() > 0){
						MessageDialog.openError(null,"Error",Messages.VrSpecificationDetailComposite_Remove_Input_Association); 
						return false;
					}
					// Se o elemento possui Data out association ele n�o pode se tornar uma variant 
					// (uma variant deve estar conectada � um varPoint)
					if (itemAE.getDataOutputAssociations().size() > 0){
						MessageDialog.openError(null,"Error",Messages.VrSpecificationDetailComposite_Remove_Output_Association);
						return false;
					}*/
				}	
				return true;
			}

		};
		variant.createControl(getAttributesParent(), "Is Variant");

	}
	

	private void redrawParent() {
		updateName();
		varPointEditor.setVisible((Boolean)varPoint.getValue());
		if (!(Boolean)variant.getValue()){
			featureIdEditor.setVisible((Boolean)varPoint.getValue());
			buttonComposite.setVisible((Boolean)varPoint.getValue());
			label.setVisible((Boolean)varPoint.getValue());
		}
		if (!(Boolean)varPoint.getValue()){
			featureIdEditor.setVisible((Boolean)variant.getValue());
			buttonComposite.setVisible((Boolean)variant.getValue());
			label.setVisible((Boolean)variant.getValue());
		}
		// this DetailComposite should be sitting in a SashForm created
		// by a ListComposite. layout this thing first
		getAttributesParent().layout();
		layout();
		// and then search for the DetailComposite that contains the list
		Composite parent = getParent();
		while (parent != null) {
			parent = parent.getParent();
			if (parent instanceof AbstractDetailComposite) {
				parent.layout();
				parent.getParent().layout();
				break;
			}
		}
	}
	
	public void updateName(){
		
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				String str;
				
				EStructuralFeature name = businessObject.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
				EStructuralFeature feature = businessObject.eClass().getEStructuralFeature("featureType");
				EStructuralFeature featureId = businessObject.eClass().getEStructuralFeature("featureId");
				if (feature!=null) {
					str = (String)businessObject.eGet(feature);
					str = getName((String)businessObject.eGet(name), (String)businessObject.eGet(feature), (String)businessObject.eGet(featureId));
					if (!str.equals((String)businessObject.eGet(name)))
						businessObject.eSet(name, str);
				}
				
				
			}
		});
	}
	public String getName(String name, String featureType, String featureId){
		String str;
		boolean aux=false;
		
		int indice = name.indexOf("<<")-1;
		str=null;
		if (indice > 0)
			str = name.substring(0, indice);
		else
			str=name;
		
		if ((Boolean)varPoint.getValue()){
			str=str+" <<varpoint>>";
			aux = true;
		}
		else if ((Boolean)variant.getValue()){
			str=str+" <<variant>>";
			aux = true;
		}
		if (featureType != null){
			if (featureType.equals("##mandatory"))
				str=str + " <<mandatory>>";
			if (featureType.equals("##optional"))
				str=str + " <<optional>>";
		}
		if (aux && featureId != null){
			str = str + " {feature = "+featureId+"}";
		}
		return str;
		
	}
}