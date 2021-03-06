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


import org.eclipse.bpmn2.Activity;
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

public class InstantiationPropertySection extends DefaultPropertySection implements ITabbedPropertyConstants {

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new InstantiationDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new InstantiationDetailComposite(parent,style);
	}
	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
		IFile file = editor.getModelFile();
//		if (file.getParent().getName().equals("Instantiating")){
		if (BPMN2Editor.getActiveEditor().getBpmnDiagram().getPhase().equals("EPN")){
			if (super.appliesTo(part, selection)) {
				EObject object = getBusinessObjectForSelection(selection);
	//			Activity element = null;
	//			if (object instanceof Activity){
	//				element = (Activity)object;
	//				if (element.isVariant() && element.isCheck())
						return object!=null;
	//			}
				
			}
		}
		return false;
	}
	@Override
	protected EObject getBusinessObjectForSelection(ISelection selection) {
		EObject be = super.getBusinessObjectForSelection(selection);
		if (be!=null) {
			EStructuralFeature feature = be.eClass().getEStructuralFeature("instantiation"); //$NON-NLS-1$
			if (feature!=null && isModelObjectEnabled(be, feature))
				return be;
		}
		return null;
	}
}
