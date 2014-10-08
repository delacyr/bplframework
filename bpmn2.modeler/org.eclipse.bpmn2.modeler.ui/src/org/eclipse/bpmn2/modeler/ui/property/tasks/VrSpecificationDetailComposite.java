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
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.property.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.bpmn2.modeler.ui.diagram.BPMNFeatureProvider;
import org.eclipse.bpmn2.modeler.ui.features.flow.DataAssociationFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.flow.SequenceFlowFeatureContainer;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.di.DIUtils;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.BooleanObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.property.editors.FeatureIdObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.TypeObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.VarPointTypeObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.VariantTypeObjectEditor;
import org.eclipse.bpmn2.presentation.Bpmn2EditorPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchWindow;

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
	protected VariantTypeObjectEditor variantEditor;
	protected ObjectEditor varPoint;
	protected ObjectEditor variant;
	protected TextObjectEditor sequentialEditor;
	protected FeatureIdObjectEditor featureIdEditor;
	protected int modified;
	protected TypeObjectEditor typeEditor;

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
						"variantType", //VrTask //$NON-NLS-1$
						"sequential", //VrTask //$NON-NLS-1$
						"featureId", //VrTask //$NON-NLS-1$
						"type",//VrTask //$NON-NLS-1$
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
	public void cleanBindings() {
		super.cleanBindings();
		varPointEditor = null;
		variantEditor = null;
		varPoint = null;
		variant = null;
		sequentialEditor = null;
		featureIdEditor = null;
		typeEditor=null;
	}

	public void createBindings(EObject be) {
		createWidgetsVarPoint(be);
		createWidgetsVarPointType(be);
		createWidgetsType(be);
		createWidgetsVariant(be);
		createWidgetsVariantType(be);
		createWidgetsFeatureId(be);
		createWidgetsSequential(be);
	}
	private void updateVisible(boolean varPointW, boolean visible){
		if (varPointW){
			varPointEditor.setVisible(visible);
			
			typeEditor.setVisible(visible);
		}else{
			variantEditor.setVisible(visible);
		}
		if (!visible){
			if (!(((Boolean) variant.getValue())||((Boolean) varPoint.getValue()))){
				if (featureIdEditor.isVisible() != visible){
					sequentialEditor.setVisible(visible);
					featureIdEditor.setVisible(visible);
				}
			}
		}else{
			if (featureIdEditor.isVisible() != visible){
				sequentialEditor.setVisible(visible);
				featureIdEditor.setVisible(visible);
			}
		}
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
							MessageDialog.openError(null,"Error","Não é possivel mudar o valor, o VarPoint possui Variants");
						else{
							object.eSet(feature, button.getSelection());
							if(button.getSelection()){
								updateVisible(true, true);
							} else {
								updateVisible(true, false);
							}
						}
					}
					
				});
				
				return true;
			}
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				if (notification.getEventType() == -1){
					if (button.getSelection()) {
						updateVisible(true, true);
					} else {
						updateVisible(true, false);
					}
				}
			}
			
			private boolean hasVariant() {
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
				}
				if (businessObject instanceof ItemAwareElement){
					List<Connection> allConnections = ModelUtil.getDiagramEditor(businessObject).getDiagramTypeProvider().getDiagram().getConnections();
					if (allConnections != null) {
						for (Connection a : allConnections) {
							DataAssociation data = BusinessObjectUtil.getFirstElementOfType(a, DataAssociation.class);
							if (data.getTargetRef()==businessObject)
								return true;
						}
					}
				}				
				return false;
			}
		};
		varPoint.createControl(getAttributesParent(), "    Is VarPoint");	
	}
	
	

	private void createWidgetsVarPointType(EObject be) {
		EStructuralFeature feature = getFeature(be, "varPointType");
		varPointEditor = new VarPointTypeObjectEditor(this, be, feature){
			@Override
			public void notifyChanged(Notification notification) {
			
				if (varPointEditor.getValue().equals("<<Combined>>")){
					if (!typeEditor.isVisible())
						typeEditor.setVisible(true);
				}else{
					if (typeEditor.isVisible())
						typeEditor.setVisible(false);
				}
				if (notification.getEventType() == 1){
					updateName();
				}
				super.notifyChanged(notification);
			}
			@Override
			public void setVisible(boolean visible) {
				if ((!visible) && !(varPointEditor.getValue().equals(""))){
					setValue(null);
					updateName();
				}
				else if  ((visible) && (varPointEditor.getValue().equals(""))){
					setValue("##varpoint");
				}
				super.setVisible(visible);
			}
		};
		varPointEditor.createControl(getAttributesParent(), "VarPoint Type");
	}
	
	private void createWidgetsType(EObject be) {
		EStructuralFeature feature = getFeature(be, "type");
		typeEditor = new TypeObjectEditor(this, be, feature){
			@Override
			public void setVisible(boolean visible) {
				if (!visible)
					setValue(null);
			
				super.setVisible(visible);
				redrawParent();
			}
			@Override
			public void notifyChanged(Notification notification) {
				if (notification.getEventType() == 1){
					updateName();
				}
				super.notifyChanged(notification);
			}
		};
		typeEditor.createControl(getAttributesParent(), "Type");
		
	}
	
	private void createWidgetsSequential(EObject be) {
		EStructuralFeature sequential = getFeature(be, "sequential");
		sequentialEditor = new TextObjectEditor(this, be, sequential) {
			public void setVisible(boolean visible) {
				super.setVisible(visible);
				text.setVisible(visible);
				GridData data = (GridData) text.getLayoutData();
				data.exclude = !visible;
				if (!visible) {
					setText(null);
				}
			}
			@Override
			public boolean setValue(Object result) {
				updateName();
				return super.setValue(result);
			}
			
		};
		sequentialEditor.createControl(getAttributesParent(), "Sequential");
	}

	private void createWidgetsFeatureId(EObject be) {
		EStructuralFeature feature = getFeature(be, "featureId");
		featureIdEditor = new FeatureIdObjectEditor(this, be, feature){
			@Override
			public void setVisible(boolean visible) {
				if (!visible)
					featureIdEditor.setValue(null);
				
				super.setVisible(visible);
				
				redrawParent();
				
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
							if(button.getSelection()){
								updateVisible(false, true);
							} else {
								updateVisible(false, false);
							}
						}
					}
				});
				redrawParent();
				return true;
			}
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				if (notification.getEventType() == -1){		
					if (button.getSelection()) {
						updateVisible(false, true);
					} else {
						updateVisible(false, false);
					}
				}
			}
			private boolean canChange() {
				if (businessObject instanceof Activity){
					Activity activity = (Activity) businessObject;
					List<? extends SequenceFlow> sequenceFlow = null;
					sequenceFlow = activity.getIncoming();
					if (sequenceFlow.size() > 0) {
						MessageDialog.openError(null,"Error","Remova os fluxos de entrada");
						return false;
					}
					sequenceFlow=null;
					sequenceFlow = activity.getOutgoing();
					if (sequenceFlow.size()>0) {
						MessageDialog.openError(null,"Error","Remova os fluxos de saída");
						return false;
					}
				}
				if (businessObject instanceof ItemAwareElement){
					List<Connection> allConnections = ModelUtil.getDiagramEditor(businessObject).getDiagramTypeProvider().getDiagram().getConnections();
					if (allConnections != null) {
						for (Connection a : allConnections) {
							DataAssociation data = BusinessObjectUtil.getFirstElementOfType(a, DataAssociation.class);
							if (data.getTargetRef()==businessObject){
								MessageDialog.openError(null,"Error","Remova os fluxos de entrada");
								return false;
							}
						}
					}
					if (businessObject instanceof ItemAwareElement){

						ItemAwareElement teste = (ItemAwareElement)businessObject;
						
						if (teste.getDataOutputAssociations().size()>0){
							MessageDialog.openError(null,"Error","Remova os fluxos de saída");
							return false;
						}
					}
				}	
				return true;
			}

		};
		variant.createControl(getAttributesParent(), "Is Variant");

	}
	
	private void createWidgetsVariantType(EObject be) {
		EStructuralFeature feature = getFeature(be, "variantType");
		variantEditor = new VariantTypeObjectEditor(this, be, feature){
			@Override
			public boolean setValue(Object result) {
				boolean ret = super.setValue(result);
				
				return ret; 
			}
			public void notifyChanged(Notification notification) {
				updateName();
				super.notifyChanged(notification);
			}
			@Override
			public void setVisible(boolean visible) {
				if ((!visible) && !(variantEditor.getValue().equals(""))){
					setValue(null);
					updateName();
				}
				else if  ((visible) && (variantEditor.getValue().equals(""))){
					setValue("##variant");
				}
				super.setVisible(visible);
			}
		};
		variantEditor.createControl(getAttributesParent(), "Variant Type");
	}
	
	private void redrawParent() {
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
		/*TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				if (businessObject instanceof Activity){
					Activity activity = (Activity)businessObject;
					activity.setName(getName(activity.getName()));
				}
				else if (businessObject instanceof DataObject){
					DataObject data = (DataObject)businessObject;
					data.setName(getName(data.getName()));
				}
				else if (businessObject instanceof DataInput){
					DataInput data = (DataInput)businessObject;
					data.setName(getName(data.getName()));
				}
				else if (businessObject instanceof DataOutput){
					DataOutput data = (DataOutput)businessObject;
					data.setName(getName(data.getName()));
				}
				else if (businessObject instanceof DataStore){
					DataStore data = (DataStore)businessObject;
					data.setName(getName(data.getName()));
				}
				
			}
		});*/
	}
	public String getName(String name){
		String str;
		
		int indice = name.indexOf("<<")-1;
		str=null;
		if (indice > 0)
			str = name.substring(0, indice);
		else
			str=name;
		
		if (!(varPointEditor.getValue()==""))
			str = str +" "+ varPointEditor.getValue().toString();
		
		if (!(variantEditor.getValue()==""))
			str = str + " "+ variantEditor.getValue().toString();
		
		
		if (!(typeEditor.getValue()==null))
			str = str+" type={"+typeEditor.getValue().toString()+"}";
		if (!(sequentialEditor.getValue().equals("")))
			str = str+" seq={"+sequentialEditor.getValue().toString()+"}";
		
		return str;
		
	}
}