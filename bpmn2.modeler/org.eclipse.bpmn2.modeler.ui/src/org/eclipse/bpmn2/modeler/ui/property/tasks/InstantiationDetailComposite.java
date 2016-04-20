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

import java.util.Hashtable;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.IntObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.property.editors.GatewayObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.SequenceObjectEditor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;



/**
 * This class renders the property sheet tab for I/O Parameters defined in
 * Activities, CallableElements and ThrowEvents.
 * 
 * TODO: handle ThrowEvent parameters
 */
public class InstantiationDetailComposite extends AbstractDetailComposite {

	protected GatewayObjectEditor gateway;
	protected IntObjectEditor seq;
	protected SequenceObjectEditor seqList;
//	protected ObjectEditor varPoint;
//	protected ObjectEditor variant;
//	protected FeatureIdObjectEditor featureIdEditor; 
//	protected Button noneButton;
//	protected Button mandatoryButton;
//	protected Button optionalButton;
//	public FeatureModel featureModel;
//	Composite buttonComposite;
	Label label;

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
				String[] properties = new String[] { "gateway", //BPMN* //$NON-NLS-1$
												 "seq", //BPMN*
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}
//	@Override
//	public void notifyChanged(Notification notification) {
//		super.notifyChanged(notification);
////		redrawParent();
//	}
	@Override
	public void cleanBindings() {
		super.cleanBindings();
		gateway = null;
		seq = null;
	}

	public void createBindings(EObject be) {
		
		createWidgetsseqList(be);
		createWidgetsGateway(be);		
		
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
			
			
			
		};
		seqList.createControl(getAttributesParent(), "Sequence");
		
	}

	private void createWidgetsGateway(EObject be) {
		EStructuralFeature feature = getFeature(be, "gateway");
		gateway = new GatewayObjectEditor(this, be, feature){
			@Override
			public boolean setValue(Object result) {

				if (MessageDialog.openConfirm(null, "BPL Instantiation", "This change will affect all variants with same execution sequence. Proceed?")){
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
												if (sibling.isVariant()){
													EStructuralFeature sequence = businessObject.eClass().getEStructuralFeature("seq");
													int seq = (int)businessObject.eGet(sequence);
													if (sibling.getSeq() == seq){
														sibling.setGateway(businessObject.eGet(feature).toString());
														redrawParent();
													}
												}
												
											}
										}
									}
								}
							}					
						}
					});
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
	
	

	private void redrawParent() {
		updateName();
//		gateway.setVisible((Boolean)varPoint.getValue());
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
				String str,str2;
				int seq;
				
				EStructuralFeature name = businessObject.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
				EStructuralFeature sequence = businessObject.eClass().getEStructuralFeature("seq");
				seq = (int)businessObject.eGet(sequence);
				if (name!=null && seq>0){
					str = businessObject.eGet(name).toString();
					str2 = getName((String)businessObject.eGet(name), businessObject.eGet(sequence).toString());
					if (!str.equals(str2)){
						businessObject.eSet(name, str2);
					}
						
				}
				
				
			}
		});
	}
	public String getName(String name, String sequence){
		String str = null;
//		boolean aux=false;
		
		int indice = name.indexOf(", seq = ");
		if (indice > 0)
			str = name.substring(0, indice);
		else{
			int indice2 = name.indexOf("}");
			if (indice2 > 0)
				str= name.substring(0,indice2);
			else{
				MessageDialog.openWarning(null, "Warning", "This variant do not have a defined feature ID.");
				return null;
			}
		}
			
		
//		if ((Boolean)varPoint.getValue()){
//			str=str+" <<varpoint>>";
//			aux = true;
//		}
//		else if ((Boolean)variant.getValue()){
//			str=str+" <<variant>>";
//			aux = true;
//		}
//		if (featureType != null){
//			if (featureType.equals("##mandatory"))
//				str=str + " <<mandatory>>";
//			if (featureType.equals("##optional"))
//				str=str + " <<optional>>";
//		}

		
		if (sequence != null){
			str = str + ", seq = " + sequence + "}";
		}
		return str;
		
	}
}