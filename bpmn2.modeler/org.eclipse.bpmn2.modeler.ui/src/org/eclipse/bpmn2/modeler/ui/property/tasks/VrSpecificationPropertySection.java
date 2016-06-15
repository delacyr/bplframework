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


import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.VrProcess;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultPropertySection;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

public class VrSpecificationPropertySection extends DefaultPropertySection implements ITabbedPropertyConstants {

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new VrSpecificationDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new VrSpecificationDetailComposite(parent,style);
	}
	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
//		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
//		BPMNDiagram bpmnDiagram = editor.getBpmnDiagram();
//		BPMNPlane plane = bpmnDiagram.getPlane();
//		BaseElement be = plane.getBpmnElement();
//		VrProcess vrProcess = null;
//		if (be instanceof VrProcess){
//			vrProcess = (VrProcess)be;
//			String phase = vrProcess.getPhase();
//			if (!phase.equals("instantiation"))
		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
		IFile file = editor.getModelFile();
		if (file.getParent().getName().equals("BusinessProcessModelTemplate")){
				if (super.appliesTo(part, selection)) {
					EObject object = getBusinessObjectForSelection(selection);
					return object!=null;
				}
		}
//		}
		
		return false;
	}
	@Override
	protected EObject getBusinessObjectForSelection(ISelection selection) {
		EObject be = super.getBusinessObjectForSelection(selection);
		if (be!=null) {
			EStructuralFeature feature = be.eClass().getEStructuralFeature("vrSpecification"); //$NON-NLS-1$
			if (feature!=null && isModelObjectEnabled(be, feature))
				return be;
		}
		return null;
	}
}
