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
package org.eclipse.bpmn2.modeler.ui.features.event;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.modeler.core.features.MultiUpdateFeature;
import org.eclipse.bpmn2.modeler.core.features.event.AbstractCreateEventFeature;
import org.eclipse.bpmn2.modeler.core.features.event.AbstractUpdateEventFeature;
import org.eclipse.bpmn2.modeler.core.features.event.AddEventFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class IntermediateCatchEventFeatureContainer extends AbstractEventFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof IntermediateCatchEvent;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateIntermediateCatchEventFeature(fp);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IFeatureProvider fp) {
		MultiUpdateFeature multiUpdate = new MultiUpdateFeature(fp);
		multiUpdate.addUpdateFeature(super.getUpdateFeature(fp));
		multiUpdate.addUpdateFeature(new UpdateIntermediateCatchEventFeature(fp));
		return multiUpdate;
	}
	
	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddIntermediateCatchEventFeature(fp);
	}

	public class AddIntermediateCatchEventFeature extends AddEventFeature<IntermediateCatchEvent> {
		public AddIntermediateCatchEventFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		protected void decorateShape(IAddContext context, ContainerShape containerShape, IntermediateCatchEvent businessObject) {
			Ellipse e = (Ellipse)getGraphicsAlgorithm(containerShape);
			Ellipse circle = GraphicsUtil.createIntermediateEventCircle(e);
			circle.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			IPeService peService = Graphiti.getPeService();
			peService.setPropertyValue(containerShape,
					UpdateIntermediateCatchEventFeature.INTERMEDIATE_CATCH_EVENT_MARKER,
					AbstractUpdateEventFeature.getEventDefinitionsValue((IntermediateCatchEvent)businessObject));
		}
	}

	public static class CreateIntermediateCatchEventFeature extends AbstractCreateEventFeature<IntermediateCatchEvent> {

		public CreateIntermediateCatchEventFeature(IFeatureProvider fp) {
			super(fp, Messages.IntermediateCatchEventFeatureContainer_0, Messages.IntermediateCatchEventFeatureContainer_1+Messages.IntermediateCatchEventFeatureContainer_2);
		}

		@Override
		public String getStencilImageId() {
			return ImageProvider.IMG_16_INTERMEDIATE_CATCH_EVENT;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature#getFlowElementClass()
		 */
		@Override
		public EClass getBusinessObjectClass() {
			return Bpmn2Package.eINSTANCE.getIntermediateCatchEvent();
		}
	}
	
	public static class UpdateIntermediateCatchEventFeature extends AbstractUpdateEventFeature {

		public static String INTERMEDIATE_CATCH_EVENT_MARKER = "marker.intermediate.catch.event"; //$NON-NLS-1$

		/**
		 * @param fp
		 */
		public UpdateIntermediateCatchEventFeature(IFeatureProvider fp) {
			super(fp);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.activity.AbstractUpdateMarkerFeature#getPropertyKey()
		 */
		@Override
		protected String getPropertyKey() {
			return INTERMEDIATE_CATCH_EVENT_MARKER;
		}
	}
}