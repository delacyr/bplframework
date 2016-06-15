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
package org.eclipse.bpmn2.modeler.ui.features.activity.task;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.impl.TaskImpl;
import org.eclipse.bpmn2.modeler.core.features.activity.task.AbstractCreateTaskFeature;
import org.eclipse.bpmn2.modeler.core.features.activity.task.AddTaskFeature;
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
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.util.IColorConstant;

public class TaskFeatureContainer extends AbstractTaskFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o.getClass().isAssignableFrom(TaskImpl.class);
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateTaskFeature(fp);
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddTaskFeature<Task>(fp){
			@Override
			protected void decorateShape(IAddContext context, ContainerShape containerShape, Task businessObject) {
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
				if (variant!=null && variant.isCheck()) {
					ShapeStyle ss = new ShapeStyle();
					ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
					StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
				}
				/*Feature exclusiva da instanciação*/
				BPMN2Editor editor = BPMN2Editor.getActiveEditor();
				IFile file = editor.getModelFile();
				if (file.getParent().getName().equals("Instantiating") || file.getParent().getName().equals("Instantiated")){
					if (!variant.isVarPoint() && !variant.isVariant()){
						ShapeStyle ss = new ShapeStyle();
						ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
						StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
					}
				}
			}
		};
	}

	public static class CreateTaskFeature extends AbstractCreateTaskFeature<Task> {

		public CreateTaskFeature(IFeatureProvider fp) {
			super(fp, Messages.TaskFeatureContainer_Name, Messages.TaskFeatureContainer_Description);
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_TASK;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature#getFlowElementClass()
		 */
		@Override
		public EClass getBusinessObjectClass() {
			return Bpmn2Package.eINSTANCE.getTask();
		}
	}
}