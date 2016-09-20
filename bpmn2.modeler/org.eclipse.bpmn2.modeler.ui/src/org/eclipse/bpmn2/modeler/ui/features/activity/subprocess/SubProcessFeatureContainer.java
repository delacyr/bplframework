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
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.features.activity.subprocess;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubChoreography;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.Transaction;
import org.eclipse.bpmn2.modeler.core.features.MultiUpdateFeature;
import org.eclipse.bpmn2.modeler.core.features.activity.AbstractCreateExpandableFlowNodeFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class SubProcessFeatureContainer extends AbstractExpandableActivityFeatureContainer {

	public static final String TRIGGERED_BY_EVENT = "triggered-by-event-key"; //$NON-NLS-1$
	public static final String IS_EXPANDED = "is-expanded-key"; //$NON-NLS-1$
	public static IColorConstant DEFAULT_COLOR = new ColorConstant(212, 231, 248);
	
	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof SubProcess &&
				 !(o instanceof AdHocSubProcess ||  o instanceof Transaction);
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateSubProcessFeature(fp);
	}
//	BPL2.0
	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddExpandableActivityFeature<SubProcess>(fp){
			@Override
			protected void decorateShape(IAddContext context, ContainerShape containerShape, SubProcess businessObject) {
				Shape textShape = peService.createShape(containerShape, false);
				MultiText text = gaService.createDefaultMultiText(getDiagram(), textShape, businessObject.getName());
				gaService.setLocationAndSize(text, 0, 0, context.getWidth(), context.getHeight());
				StyleUtil.applyStyle(text, businessObject);
				text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
				text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
//				text.setFont(gaService.manageFont(getDiagram(), GaServiceImpl.DEFAULT_FONT, 8, false, true));
				link(textShape, businessObject);
				
				Shape shape = containerShape.getChildren().get(0);
				BaseElement baseElement = BusinessObjectUtil.getFirstBaseElement(containerShape);
				Activity variant = (Activity)baseElement;
//				if (variant!=null && variant.isVariant() && variant.isCheck()) {
				if (variant!=null && variant.isCheck()) {	
//					if (variant.isVariant() && variant.getSeq() == 0){
						boolean pass = false;
						List<SequenceFlow> outgoing = variant.getOutgoing();
						for (SequenceFlow sf: outgoing){
							if (sf.getTargetRef() instanceof Activity){
								pass = true;
								if (((Activity)sf.getTargetRef()).isVarPoint()){
									if (((Activity)sf.getTargetRef()).getVarPointType().equals("##OR")){
										if (variant.isVariant() && variant.getSeq() == 0 && (numberOfCheckedVariantsWoSeq((Activity)sf.getTargetRef()) > 1)){
											ShapeStyle ss = new ShapeStyle();
											ss.setDefaultColors(IColorConstant.YELLOW);
											StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
										}else{
											ShapeStyle ss = new ShapeStyle();
											ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
											StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
										}
									}
									else{
										ShapeStyle ss = new ShapeStyle();
										ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
										StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
									}
								}else{
									ShapeStyle ss = new ShapeStyle();
									ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
									StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
								}
							}
						}
						
						if (!pass){
							ShapeStyle ss = new ShapeStyle();
							ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
							StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
						}
//					}
				
				}
				/*Feature exclusiva da instanciação*/
				BPMN2Editor editor = BPMN2Editor.getActiveEditor();
				IFile file = editor.getModelFile();
				if (file.getParent().getName().equals("Instantiating") || file.getParent().getName().equals("Instantiated") || file.getParent().getParent().getName().equals("Instantiating") || file.getParent().getParent().getName().equals("Instantiated")){
//				if (BPMN2Editor.getActiveEditor().getBpmnDiagram().getPhase().equals("EPN")){
					if (!variant.isVarPoint() && !variant.isVariant()){
						ShapeStyle ss = new ShapeStyle();
						ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
						StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
					}
				}else{
					ShapeStyle ss = new ShapeStyle();
					ss.setDefaultColors(DEFAULT_COLOR);
					StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
				}
			}
			
			private int numberOfCheckedVariantsWoSeq(Activity varpoint) {
				// TODO Auto-generated method stub
				List<SequenceFlow> incoming = varpoint.getIncoming();
				int count = 0;
				for (int i=0;i<incoming.size();i++){
					SequenceFlow b = incoming.get(i);
					if (b.getSourceRef() instanceof Activity){
						Activity activity = (Activity)b.getSourceRef();
						if (activity.isVariant() && activity.isCheck() && activity.getSeq() == 0){
							count++;
						}
					}
				}
				return count;
			}
		};
	}

	@Override
	public IUpdateFeature getUpdateFeature(IFeatureProvider fp) {
		IUpdateFeature updateFeature = super.getUpdateFeature(fp);
		MultiUpdateFeature multiUpdate;
		if (updateFeature instanceof MultiUpdateFeature)
			multiUpdate = (MultiUpdateFeature)updateFeature;
		else
			multiUpdate = new MultiUpdateFeature(fp);
		UpdateExpandableActivityFeature ueaf = new UpdateExpandableActivityFeature(fp);
		multiUpdate.addUpdateFeature(ueaf);
		return multiUpdate;
	}

	public static class CreateSubProcessFeature extends AbstractCreateExpandableFlowNodeFeature<SubProcess> {

		public CreateSubProcessFeature(IFeatureProvider fp) {
			super(fp, Messages.SubProcessFeatureContainer_Name, Messages.SubProcessFeatureContainer_Description);
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_SUB_PROCESS;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature#getFlowElementClass()
		 */
		@Override
		public EClass getBusinessObjectClass() {
			return Bpmn2Package.eINSTANCE.getSubProcess();
		}
	}
}