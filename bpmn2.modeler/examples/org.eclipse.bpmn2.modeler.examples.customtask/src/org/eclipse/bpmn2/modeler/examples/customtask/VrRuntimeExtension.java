/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013 Red Hat, Inc.
 * All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.examples.customtask;

import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.ui.DefaultBpmn2RuntimeExtension;
import org.eclipse.bpmn2.modeler.ui.wizards.FileService;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.ui.IEditorInput;
import org.xml.sax.InputSource;

public class VrRuntimeExtension extends DefaultBpmn2RuntimeExtension {
	
	public VrRuntimeExtension() {
	}

	@Override
	public boolean isContentForRuntime(IEditorInput input) {
		InputSource source = new InputSource(
				FileService.getInputContents(input));
		RootElementParser parser = new RootElementParser(
				"http://org.eclipse.bpmn2.modeler.examples.customtask");
		parser.parse(source);
		return parser.getResult();
	}

	@Override
	public String getTargetNamespace(Bpmn2DiagramType diagramType) {
		return "http://org.eclipse.bpmn2.modeler.examples.customtask";
	}
}