/*******************************************************************************
 * Copyright (c) 2011, 2012 Red Hat, Inc. 
 * All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property.tasks;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.ui.property.editors.VarPointTypeObjectEditor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

public class VrCharacteristicsDetailComposite extends DefaultDetailComposite {
	protected VarPointTypeObjectEditor varPointEditor = null;
	protected VarPointTypeObjectEditor variantEditor;
	public VrCharacteristicsDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public VrCharacteristicsDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	
	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"varPoint", //VrTask //$NON-NLS-1$
						"varPointType", //VrTask //$NON-NLS-1$
						"variant", //VrTask //$NON-NLS-1$
						"variantType", //VrTask //$NON-NLS-1$
						"sequencial", //VrTask //$NON-NLS-1$
						"featureId", //VrTask //$NON-NLS-1$
				};
				
				@Override
				public String[] getProperties() {
					return properties; 
				}
			};
		}
		return propertiesProvider;
	}
	public void createBindings(EObject be) {
		//super.createBindings(be);
		//createWidgets(be);

		bindAttribute(null, be,"varPoint", "Is VarPoint");
		bindAttribute(null, be,"varPointType", "VarPoint Type");
		bindAttribute(null, be,"variant", "Is Variant");
		bindAttribute(null, be,"variantType", "Variant Type");
		bindAttribute(null, be,"sequencial", "Seq.");
		bindAttribute(null, be,"featureId", "Feature ID");
		
	}
	protected void bindAttribute(Composite parent, EObject object, EAttribute attribute, String label) {
		if ("varPointType".equals(attribute.getName())) { //$NON-NLS-1$
			varPointEditor = new VarPointTypeObjectEditor(this,object,attribute);
			varPointEditor.createControl(getAttributesParent(),"VarPoint Type");
		}
		else if ("variantType".equals(attribute.getName())) {
			variantEditor = new VarPointTypeObjectEditor(this,object,attribute);
			variantEditor.createControl(getAttributesParent(),"Variant Type");
		}else
			super.bindAttribute(parent, object, attribute, label);
	}
}
