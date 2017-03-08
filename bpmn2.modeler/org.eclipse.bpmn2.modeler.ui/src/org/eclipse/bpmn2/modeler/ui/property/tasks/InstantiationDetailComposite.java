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

package org.eclipse.bpmn2.modeler.ui.property.tasks;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.BooleanObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextAndButtonObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ErrorUtils;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.editors.GatewayObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.SequenceObjectEditor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;



/**
 * This class renders the property sheet tab for I/O Parameters defined in
 * Activities, CallableElements and ThrowEvents.
 * 
 * TODO: handle ThrowEvent parameters
 */
public class InstantiationDetailComposite extends AbstractDetailComposite {

	protected ObjectEditor showInstanceName;
	protected TextObjectEditor instanceElementName;
	protected GatewayObjectEditor gateway;
	protected SequenceObjectEditor seqList;
	protected TextObjectEditor condition;

	public InstantiationDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public InstantiationDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider == null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] { "instanceName",
													"showInstanceName",
													"gateway", //BPMN* //$NON-NLS-1$
												 "seq",
												"condition" //BPMN*
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
		showInstanceName = null;
		instanceElementName = null;
		gateway = null;
		seqList = null;
		condition = null;
	}

	public void createBindings(EObject be) {
		
		createWidgetsInstanceName(be);
		createWidgetsShowInstanceName(be);		
		createWidgetsseqList(be);
		createWidgetsGateway(be);	
		createWidgetsCondition(be);
		
//		seqList.setVisible((Boolean)isOrVarpoint());
//		gateway.setVisible((Boolean)hasSameSequence());
////		condition.setVisible((Boolean)isORorXOR());
//		condition.setVisible((Boolean)hasSameSequence());
	}

	private Boolean isORorXOR() {
		// TODO Auto-generated method stub
		Activity variant = null;
		if (businessObject instanceof Activity){
			variant = (Activity)businessObject;
			if ((variant.getGateway().equals("##XOR")) || (variant.getGateway().equals("##OR"))){
				if (gateway.isVisible())
					return true;
			}
		}
		return false;
	}

	private Boolean isOrVarpoint() {
		// TODO Auto-generated method stub
		Activity variant = null;
		Activity varpoint = null;
//		Activity sibling = null;
		if (businessObject instanceof Activity){
			variant = (Activity)businessObject;
			List<SequenceFlow> sequenceFlowOut = variant.getOutgoing();
			SequenceFlow a = sequenceFlowOut.get(0);
			if (a.getTargetRef() instanceof Activity){
				varpoint = (Activity)a.getTargetRef();
				if (varpoint.isVarPoint() && varpoint.getVarPointType().equals("##OR")){
//					List<SequenceFlow> sequenceFlowIn = varpoint.getIncoming();
//					for (SequenceFlow b: sequenceFlowIn){
//						if (b.getSourceRef() instanceof Activity){
//							sibling = (Activity)b.getSourceRef();
//							if (sibling.isVariant() && sibling.isCheck() && !sibling.getId().equals(variant.getId())){
//								EStructuralFeature sequence = businessObject.eClass().getEStructuralFeature("seq");
//								int seq = (int)businessObject.eGet(sequence);
//								if (sibling.getSeq() == seq){
//									System.out.println(sibling.getName()+" has Same Sequence "+variant.getName());
									return true;
//								}
//								System.out.println(sibling.getName()+"Diff Sequence"+variant.getName());
//							}
							
//						}
//					}
				}
			}
		}
		return false;
	}

	private void createWidgetsShowInstanceName(EObject be) {
		// TODO Auto-generated method stub
		final EStructuralFeature showInstanceN = getFeature(be, "showInstanceName");
		showInstanceName = new BooleanObjectEditor(this, be, showInstanceN){
			
			@Override
			protected Control createControl(Composite composite, String label, int style) {

				// create a separate label to the LEFT of the checkbox, otherwise the grid layout will
				// be off by one column for all other widgets that are created after this one.
				createLabel(composite, label);
				
				button = getToolkit().createButton(composite, "Show/Hide Instance Name", SWT.TOGGLE); //$NON-NLS-1$
				button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false, 2, 1));
				button.setSelection(getValue());
				button.addSelectionListener( new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (!isWidgetUpdating) {
							boolean checked = button.getSelection();
							setValue(new Boolean(checked));
							button.setSelection(getValue());
							if (checked == true){
								if (!instanceElementName.getValue().toString().equals("")){
									changeElementName(); //specsName <- Name
								}
								else{
									MessageDialog.openWarning(null, "Warning", Messages.InstantiationDetailComposite_Required_Instance_Name);
									button.setSelection(false);
								}
							}
							else
								changeElementName();
						}
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
					}
				});
				
				return button;
			}
		};
		showInstanceName.createControl(getAttributesParent(), "");
	}

	private void createWidgetsInstanceName(EObject be) {
		// TODO Auto-generated method stub
		EStructuralFeature instanceN = getFeature(be, "instanceName");
		instanceElementName = new TextAndButtonObjectEditor(this, be, instanceN ){

			@Override
			protected void buttonClicked(int buttonId) {
				// TODO Auto-generated method stub
				Activity variant = null;
				if (businessObject instanceof Activity){
					variant = (Activity)businessObject;
					//if (variant.isVariant() && variant.isCheck()){
					if (variant.isSolved() || variant.isCheck()){
						InputDialog dialog = new InputDialog(null, Messages.InstantiationDetailComposite_Instance_Name_Title, Messages.InstantiationDetailComposite_Instance_Name_Description, getText(), null);
							if (dialog.open() == Window.OK){
								setValue(dialog.getValue());
							}
					}
					else MessageDialog.openWarning(null, "Warning", Messages.InstantiationDetailComposite_Unchecked_Variant);
				}
			}
			
//			@Override
//			protected boolean setValue(final Object result) {
//				Activity variant = null;
//				if (businessObject instanceof Activity){
//					variant = (Activity)businessObject;
//					if (variant.isVariant() && variant.isCheck())
//						if (super.setValue(result)) {
//							updateText();
//							return true;
//						}
//					MessageDialog.openWarning(null, "Warning", "Unchecked variant! You must check this variant before continue.");
//					return false;
//				}
//				// revert the change on error
//				text.setText(getText());
//				return false;
//			}
			
		};
		instanceElementName.createControl(getAttributesParent(), "Instance Name");
	}
	
	private void createWidgetsseqList(EObject be) {
		
		EStructuralFeature sequence = getFeature(be, "seq");
		seqList = new SequenceObjectEditor(this, be, sequence){
			
			@Override
			protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature) {
				Hashtable<String, Object> choices = new Hashtable<String, Object>();
				
				Activity variant = null;
				Activity varpoint = null;
				Activity sibling = null;
				if (businessObject instanceof Activity){
					variant = (Activity)businessObject;
					List<SequenceFlow> sequenceFlowOut = variant.getOutgoing();
					SequenceFlow a = sequenceFlowOut.get(0);
					if (a.getTargetRef() instanceof Activity){
						varpoint = (Activity)a.getTargetRef();
						if (varpoint.isVarPoint()){
							List<SequenceFlow> sequenceFlowIn = varpoint.getIncoming();
							Integer size = new Integer(0);
							for (SequenceFlow b: sequenceFlowIn){
								if (b.getSourceRef() instanceof Activity){
									sibling = (Activity)b.getSourceRef();
									if (sibling.isVariant())
										size++;
								}
							}
							Integer i = 0;
							while (size != 0){
								i++;
								choices.put(i.toString(), i);
								size--;
							}
						}
					}
					
				}
				return choices;
			}
			
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);		
				redrawParent();
			}
			
			private ContainerShape getContainerShape(FlowNode fn, Diagram diagram) {
				// TODO Auto-generated method stub
				for (Object o : Graphiti.getPeService().getLinkedPictogramElements(new EObject[] {fn}, diagram)) {
					if (o instanceof ContainerShape && !FeatureSupport.isLabelShape((ContainerShape)o)) {
						// this is the FlowNode shape
						return (ContainerShape)o;
					}
				}
				return null;
			}

			@Override
			public boolean setValue(final Object result) {
				
				if (businessObject instanceof Activity){
					Activity variant = null;
					variant = (Activity)businessObject;
					if (variant.isVariant() && variant.isCheck()){
						
						Diagram diagram = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram();
						ContainerShape containerShape = getContainerShape((FlowNode)businessObject, diagram);
						final Shape shape = containerShape.getChildren().get(0);
						final BaseElement baseElement = BusinessObjectUtil.getFirstBaseElement(containerShape);
						final ShapeStyle ss = new ShapeStyle();
						ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
//						TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
//						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
//							@Override
//							protected void doExecute() {
//								StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
//							}
//						});
						
						TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								Activity variant2 = null;
								variant2 = (Activity)businessObject;
//								variant2.setSeq((int)result);
//								BPL2.0 22/09
								variant2.setSeq(((Integer)result).intValue());
//								EStructuralFeature sequence = businessObject.eClass().getEStructuralFeature("seq");
//								sequence.eSet(sequence, (int)result);
								StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
								
							}
						});
						
						return super.setValue(result);
					}
					MessageDialog.openWarning(null, "Warning", Messages.InstantiationDetailComposite_Unchecked_Variant);
					return false;
				}
				return false;
			}
		};
		seqList.createControl(getAttributesParent(), "Sequence");
	}

	private void createWidgetsGateway(EObject be) {
		EStructuralFeature feature = getFeature(be, "gateway");
		gateway = new GatewayObjectEditor(this, be, feature){
//			@Override
//			public void setVisible(boolean visible) {
////				if (!visible)
////					setValue(null);
//				super.setVisible(visible);
//			}
			
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);

					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							Activity variant = null;
							Activity varpoint = null;
							Activity sibling = null;
							if (businessObject instanceof Activity){
								variant = (Activity)businessObject;
								List<SequenceFlow> sequenceFlowOut = variant.getOutgoing();
								SequenceFlow a = sequenceFlowOut.get(0);
								if (a.getTargetRef() instanceof Activity){
									varpoint = (Activity)a.getTargetRef();
									if (varpoint.isVarPoint()){
										List<SequenceFlow> sequenceFlowIn = varpoint.getIncoming();
										for (SequenceFlow b: sequenceFlowIn){
											if (b.getSourceRef() instanceof Activity){
												sibling = (Activity)b.getSourceRef();
												if (sibling.isVariant() && !sibling.getId().equals(variant.getId())){
													EStructuralFeature sequence = businessObject.eClass().getEStructuralFeature("seq");
//													int seq = (int)businessObject.eGet(sequence);
//													BPL2.0 22/09
													int seq = ((Integer)businessObject.eGet(sequence)).intValue();
													if (sibling.getSeq() == seq){
														sibling.setGateway(businessObject.eGet(feature).toString());
													}
												}
												
											}
										}
									}
								}
							}		
							redrawParent();
						}
					});	
			}
			
			@Override
			public boolean setValue(Object result) {
				
//				if (result != null)
				if (MessageDialog.openConfirm(null, Messages.InstantiationDetailComposite_Instance_Name_Title, Messages.InstantiationDetailComposite_Gateway_Change)){
					if (ModelUtil.isStringWrapper(result)) {
						result = ModelUtil.getStringWrapperValue(result);
					}
					return super.setValue(result);
				}
				return false;
			}
		};
		gateway.createControl(getAttributesParent(), "Gateway");
	}
	
	private void createWidgetsCondition(EObject be) {
		// TODO Auto-generated method stub
		EStructuralFeature cond = getFeature(be, "condition");
		condition = new TextObjectEditor(this, be, cond);
		condition.createControl(getAttributesParent(), "Condition");
	}
	
	private boolean hasSameSequence(){
		Activity variant = null;
		Activity varpoint = null;
		Activity sibling = null;
		if (businessObject instanceof Activity){
			variant = (Activity)businessObject;
			List<SequenceFlow> sequenceFlowOut = variant.getOutgoing();
			SequenceFlow a = sequenceFlowOut.get(0);
			if (a.getTargetRef() instanceof Activity){
				varpoint = (Activity)a.getTargetRef();
				if (varpoint.isVarPoint() && varpoint.getVarPointType().equals("##OR")){
					List<SequenceFlow> sequenceFlowIn = varpoint.getIncoming();
					for (SequenceFlow b: sequenceFlowIn){
						if (b.getSourceRef() instanceof Activity){
							sibling = (Activity)b.getSourceRef();
							if (sibling.isVariant() && sibling.isCheck() && !sibling.getId().equals(variant.getId())){
								EStructuralFeature sequence = businessObject.eClass().getEStructuralFeature("seq");
//								int seq = (int)businessObject.eGet(sequence);
//								BPL2.0 22/09
								int seq = ((Integer)businessObject.eGet(sequence)).intValue();
								if (sibling.getSeq() == seq){
//									System.out.println(sibling.getName()+" has Same Sequence "+variant.getName());
									return true;
								}
//								System.out.println(sibling.getName()+"Diff Sequence"+variant.getName());
							}
							
						}
					}
				}
			}
		}
		return false;
	}

	private void redrawParent() {
//		changeElementName();
		updateName();
//		seqList.setVisible((Boolean)isOrVarpoint());
//		gateway.setVisible((Boolean)hasSameSequence());
////		condition.setVisible((Boolean)isORorXOR());
//		condition.setVisible((Boolean)hasSameSequence());
//		if (!(Boolean)variant.getValue()){
//			featureIdEditor.setVisible((Boolean)varPoint.getValue());
//			buttonComposite.setVisible((Boolean)varPoint.getValue());
//			label.setVisible((Boolean)varPoint.getValue());
//		}
//		if (!(Boolean)varPoint.getValue()){
//			featureIdEditor.setVisible((Boolean)variant.getValue());
//			buttonComposite.setVisible((Boolean)variant.getValue());
//			label.setVisible((Boolean)variant.getValue());
//		}
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
				String str,str2,specName;
				int seq;
				
				EStructuralFeature name = businessObject.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
				EStructuralFeature specsName = businessObject.eClass().getEStructuralFeature("specsName");
				EStructuralFeature sequence = businessObject.eClass().getEStructuralFeature("seq");
				EStructuralFeature showInstanceName = businessObject.eClass().getEStructuralFeature("showInstanceName");
//				seq = (int)businessObject.eGet(sequence);
//				BPL2.0 22/09
				seq = ((Integer)businessObject.eGet(sequence)).intValue();
				specName = (String)businessObject.eGet(specsName);
				if ((Boolean)businessObject.eGet(showInstanceName) == false){
					if (name!=null && seq>0){
						str = businessObject.eGet(name).toString();
						str2 = getName((String)businessObject.eGet(name), businessObject.eGet(sequence).toString());
						if ((str!=null) && (str2!=null) && !str.equals(str2)){
							businessObject.eSet(name, str2);
						}	
					}
				}
					else{
						if (specName!=null){
							str2 = getName((String)businessObject.eGet(specsName), businessObject.eGet(sequence).toString());
							if ((str2!=null) && !specName.equals(str2))
								businessObject.eSet(specsName, str2);
						}	
					}		
			}
		});
	}
	
	public void changeElementName(){
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				String str,specName,instName;
				
				EStructuralFeature name = businessObject.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
				EStructuralFeature instanceName = businessObject.eClass().getEStructuralFeature("instanceName");
				EStructuralFeature specsName = businessObject.eClass().getEStructuralFeature("specsName");

					str = (String)businessObject.eGet(name);
					instName = (String)businessObject.eGet(instanceName);
					specName = (String)businessObject.eGet(specsName);
					
					if ((Boolean)showInstanceName.getValue() == true){ //Mostrar instanceName
							businessObject.eSet(specsName, str); //specsName <- Name
							businessObject.eSet(name, instName); //Name <- instanceName
					}
					else{
						if (specName!=null)
							businessObject.eSet(name, specName);
					}
					
			}
		});
	}
	
	public String getName(String name, String sequence){
		String str = null;
		
		int indice = name.indexOf(", seq = ");
		if (indice > 0)
			str = name.substring(0, indice);
		else{
			int indice2 = name.indexOf("}");
			if (indice2 > 0)
				str= name.substring(0,indice2);
			else{
//				MessageDialog.openWarning(null, "Warning", "This variant do not have a defined feature ID.");
				return null;
			}
		}
		if (sequence != null){
			str = str + ", seq = " + sequence + "}";
		}
		return str;
		
	}
}