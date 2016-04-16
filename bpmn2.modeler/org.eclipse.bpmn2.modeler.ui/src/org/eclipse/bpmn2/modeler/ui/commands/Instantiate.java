package org.eclipse.bpmn2.modeler.ui.commands;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.VrProcess;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class Instantiate extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Info", "Info for you");
		
		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
		
		BPMNDiagram bpmnDiagram = editor.getBpmnDiagram();		
		DiagramElement element = bpmnDiagram.getRootElement();
		List<EObject> elements = element.eContents();
		BPMNShape bpmnShape = (BPMNShape)elements.get(0);

		Diagram diagram = editor.getDiagramTypeProvider().getDiagram();
		PictogramElement pe = BusinessObjectUtil.getPictogramElementFromDiagram(diagram, bpmnShape);
		Object bo = BusinessObjectUtil.getBusinessObjectForPictogramElement(pe);
		
//		ValidateDiagram(bo);
//		editor.doSaveAs();

		return null;
	}

	private void ValidateDiagram(Object bo) {
		// TODO Auto-generated method stub
		if (bo instanceof StartEvent){
			System.out.println("Start Event");
			//getting outgoing elements
			List<SequenceFlow> sequenceFlow = ((StartEvent) bo).getOutgoing();
			//for each outgoing element
			for (SequenceFlow a: sequenceFlow){
				//if it is activity
				if (a.getTargetRef() instanceof Activity){
					Activity activity = (Activity)a.getTargetRef();
					//if it is varpoint
					if (activity.isVarPoint()){
						//if it is OR
						if (activity.getVarPointType().equals("##OR")){
							//check feature type
							System.out.println(activity.getFeatureType()+"##OR");
							//maybe has variants
							checkVariants();
//							List<SequenceFlow> incoming = activity.getIncoming();
//							//for each variant
//							for (SequenceFlow b: incoming){
//								if (b.getSourceRef() instanceof Activity){
//									Activity activityB = (Activity)b.getSourceRef();
//									if (activityB.isCheck()){
//										
//									}
//										
//								}
//							}
						}
						if (activity.getVarPointType().equals("##XOR")){
							//check feature type
							System.out.println(activity.getFeatureType()+"##XOR");
							checkVariants();

						}
					}
					
				}
			}
		}
			
	}

	private void checkVariants() {
		// TODO Auto-generated method stub
		
	}

}
