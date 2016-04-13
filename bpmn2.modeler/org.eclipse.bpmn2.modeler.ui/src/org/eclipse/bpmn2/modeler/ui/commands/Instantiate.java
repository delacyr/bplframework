package org.eclipse.bpmn2.modeler.ui.commands;

import java.util.List;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class Instantiate extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Info", "Info for you");
		
		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
		PictogramElement[] pes = editor.getSelectedPictogramElements();
		BPMNDiagram diagram = editor.getBpmnDiagram();
		DiagramElement element = diagram.getRootElement();

		List<EObject> elements = element.eContents();
		for (EObject o: elements){			
			
			System.out.println(o.getClass());

		}
		return null;
	}

}
