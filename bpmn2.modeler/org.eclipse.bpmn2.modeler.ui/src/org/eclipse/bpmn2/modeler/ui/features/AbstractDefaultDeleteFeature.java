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
package org.eclipse.bpmn2.modeler.ui.features;

import org.eclipse.bpmn2.modeler.core.features.DefaultDeleteBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class AbstractDefaultDeleteFeature extends DefaultDeleteBPMNShapeFeature {
	public AbstractDefaultDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void delete(IDeleteContext context) {
		deletePeEnvironment(context.getPictogramElement());
		super.delete(context);
	}
	
	@Override
	public boolean canDelete(IDeleteContext context) {
		if (BPMN2Editor.getActiveEditor().getBpmnDiagram().getPhase().equals("EPN"))
			return false;
		// don't delete the Diagram!
		if (context.getPictogramElement() instanceof Diagram)
			return false;
		return true;
	}
}
