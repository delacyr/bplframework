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
package org.eclipse.bpmn2.modeler.ui.features.data;

import java.io.IOException;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.features.data.AbstractCreateDataInputOutputFeature;
import org.eclipse.bpmn2.modeler.core.features.data.AddDataFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.util.IColorConstant;

public class DataOutputFeatureContainer extends AbstractDataFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof DataOutput;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateDataOutputFeature(fp);
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddDataOutputFeature(fp);
	}

	public class AddDataOutputFeature extends AddDataFeature<DataOutput> {
		public AddDataOutputFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		protected boolean isSupportCollectionMarkers() {
			return false;
		}

		@Override
		protected void decorateShape(IAddContext context, ContainerShape containerShape, DataOutput businessObject) {
			Polygon p = (Polygon)getGraphicsAlgorithm(containerShape);
			Polygon arrow = GraphicsUtil.createDataArrow(p);
			arrow.setFilled(true);
			arrow.setBackground(manageColor(StyleUtil.CLASS_FOREGROUND));
			arrow.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			
//			BPL2.0
			Shape shape = containerShape.getChildren().get(0);
			BaseElement baseElement = BusinessObjectUtil.getFirstBaseElement(containerShape);
			ItemAwareElement variant = (ItemAwareElement)baseElement;
//			if (variant!=null && variant.isVariant() && variant.isCheck()) {
			if (variant!=null && variant.isCheck()) {	
//				if (variant.isVariant() && variant.getSeq() == 0){
					
//					List<SequenceFlow> outgoing = variant.getOutgoing();
//					for (SequenceFlow sf: outgoing){
//						if (sf.getTargetRef() instanceof Activity)
//							if (((Activity)sf.getTargetRef()).isVarPoint())
//								if (((Activity)sf.getTargetRef()).getVarPointType().equals("##OR")){
//									if (variant.isVariant() && variant.getSeq() == 0){
//										ShapeStyle ss = new ShapeStyle();
//										ss.setDefaultColors(IColorConstant.YELLOW);
//										StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
//									}else{
//										ShapeStyle ss = new ShapeStyle();
//										ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
//										StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
//									}
//								}
//								else{
									ShapeStyle ss = new ShapeStyle();
									ss.setDefaultColors(IColorConstant.LIGHT_ORANGE);
									StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
//								}
//					}
//				}
			
			}
			/*Feature exclusiva da instanciação*/
			BPMN2Editor editor = BPMN2Editor.getActiveEditor();
			IFile file = editor.getModelFile();
			if (file.getParent().getName().equals("Instantiating") || file.getParent().getName().equals("Instantiated") || file.getParent().getParent().getName().equals("Instantiating") || file.getParent().getParent().getName().equals("Instantiated")){
//			if (BPMN2Editor.getActiveEditor().getBpmnDiagram().getPhase().equals("EPN")){
				if (!variant.isVarPoint() && !variant.isVariant()){
					ShapeStyle ss = new ShapeStyle();
					ss.setDefaultColors(IColorConstant.LIGHT_ORANGE);
					StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
				}
			}
		}

		@Override
		public String getName(DataOutput t) {
			return t.getName();
		}
	}

	public static class CreateDataOutputFeature extends AbstractCreateDataInputOutputFeature<DataOutput> {

		public CreateDataOutputFeature(IFeatureProvider fp) {
			super(fp, Messages.DataOutputFeatureContainer_Name, Messages.DataOutputFeatureContainer_Description);
		}

		@Override
		public String getStencilImageId() {
			return ImageProvider.IMG_16_DATA_OUTPUT;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature#getBusinessObjectClass()
		 */
		@Override
		public EClass getBusinessObjectClass() {
			return Bpmn2Package.eINSTANCE.getDataOutput();
		}
	}
}