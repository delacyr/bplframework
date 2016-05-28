package org.eclipse.bpmn2.modeler.ui.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.dialogs.ListDialog;

public class Instantiate extends AbstractHandler implements IHandler {

	List<String> types = new ArrayList<String>();
	
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
		
		ValidateDiagram(bo);
		
		editor.doSave(null);
		
		if (!types.isEmpty()){
			ListDialog dialog = new ListDialog(null);
			dialog.setTitle(Messages.InstantiateCommand_Title); 
			dialog.setMessage(Messages.InstantiateCommand_Varpoint_Not_Resolved); 
			dialog.setInput(types.toArray()); 
			dialog.setContentProvider(new ArrayContentProvider());
			dialog.setLabelProvider(new LabelProvider());
			dialog.open();
		}
		else{
			generateModel(bo);
			
		}
		types.clear();
			
//		editor.doSaveAs();

		return null;
	}

	private void generateModel(Object bo) {
		// TODO Auto-generated method stub
		List<SequenceFlow> sequenceFlow = null;
		
		if (bo instanceof StartEvent){
			System.out.println("GM - Start Event");
			//getting outgoing elements
			sequenceFlow = ((StartEvent) bo).getOutgoing();
			//for each outgoing element
			for (SequenceFlow a: sequenceFlow){
				//if it is activity
				if (a.getTargetRef() instanceof Activity){
					Activity activity = (Activity)a.getTargetRef();
//					System.out.println("Activity");
					//if it is varpoint
					if (activity.isVarPoint()){
//						if (checkVarpoints(activity))
							sweepVarpoints(activity);
							generateModel((Object)activity);
					}
				}
			}
		}
		
		if (bo instanceof Activity){
			System.out.println("GM - Activity");
			
//			if (checkVarpoints((Activity)bo)){
			//getting outgoing elements
				sequenceFlow = ((Activity) bo).getOutgoing();
				//for each outgoing element
				for (SequenceFlow a: sequenceFlow){
					//if it is activity
					if (a.getTargetRef() instanceof Activity){
						Activity activity = (Activity)a.getTargetRef();
						//if it is varpoint
						if (activity.isVarPoint()){
//							if (checkVarpoints(activity)){
								sweepVarpoints(activity);
//								System.out.println(activity.getFeatureType());
								generateModel((Object)activity);
//							}
						}
					}
					if (a.getTargetRef() instanceof EndEvent){
						System.out.println("GM - End Event ;)");
					}
				}
//			}
		}
	}

	private boolean sweepVarpoints(final Activity activity) {
		// TODO Auto-generated method stub
		Activity variant = null;
		String s = activity.getFeatureType();
		if (s != null){
			if ((activity.getFeatureType().equals("##mandatory"))){ //mandatory or none
				if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
					if (numberOfCheckedVariants(activity) == 1){
						if (sweepVariants(activity)){ //exclui variantes não selecionadas
							variant = getCheckedVariant(activity); //pega a variant selecionada
							deleteVariant(variant);
							copyDataVariant(activity,variant);
							updateContext(activity);
							return true;
						}
					}
					else{
						System.out.println("not yet");
					}
				}
				if (activity.getVarPointType().equals("##XOR")){ //uma variante
					if (activity.isSolved()){ //se o varpoint isSolved, true
						if (sweepVariants(activity)){ //exclui variantes não selecionadas
							variant = getCheckedVariant(activity); //pega a variant selecionada
							deleteVariant(variant);
							copyDataVariant(activity,variant);
							updateContext(activity);
							return true;
						}
					}
					return false;
				}
			}
			//if is Optional, must have selected variants
			if (activity.getFeatureType().equals("##optional")){
				if (activity.getVarPointType().equals("##OR")){
 
					
				}
				if (activity.getVarPointType().equals("##XOR")){
					if (sweepVariants(activity))
						return true;

					return false;
				}
			}
		}
		else{ //varpoint ##none
			if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
 
			}
			if (activity.getVarPointType().equals("##XOR")){ //uma variante
				//check feature type
//				System.out.println(activity.getFeatureType()+"##XOR");
				
				if (activity.isSolved()){ //se o varpoint isSolved, true
					if (sweepVariants(activity)){ //exclui variantes não selecionadas
						variant = getCheckedVariant(activity); //pega a variant selecionada
						deleteVariant(variant);
						copyDataVariant(activity,variant);
						updateContext(activity);
						return true;
					}
				}
					return false;
			}
		}
		return false;
	}

	
	private int numberOfCheckedVariants(Activity varpoint) {
		// TODO Auto-generated method stub
		List<SequenceFlow> incoming = varpoint.getIncoming();
		int count = 0;
		for (int i=0;i<incoming.size();i++){
			SequenceFlow b = incoming.get(i);
			if (b.getSourceRef() instanceof Activity){
				Activity activity = (Activity)b.getSourceRef();
				if (activity.isVariant() && activity.isCheck()){
					count++;
				}
			}
		}
		return count;
	}

	private void updateContext(Activity activity) {
		// TODO Auto-generated method stub
		Diagram diagram = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram();
		final IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();					
		FlowNode fn = (FlowNode)activity;
		final ContainerShape instantiatedTask = getContainerShape(fn,diagram);
		
		TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
					UpdateContext updateContext = new UpdateContext(instantiatedTask);
					IUpdateFeature updateFeature = fp.getUpdateFeature(updateContext);
					if ( updateFeature.updateNeeded(updateContext).toBoolean() )
						updateFeature.update(updateContext);
			}
		});
	}

	private void copyDataVariant(final Activity activity, final Activity variant) {
		// TODO Auto-generated method stub
		TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// TODO Auto-generated method stub
//				Activity variant = getCheckedVariant(activity);
//				deleteVariant(variant);
				activity.setVarPoint(false);
				variant.setVariant(false);
				String str = null;
				if (variant.getInstanceName() != null){
					String name = variant.getInstanceName();
					int indice = name.indexOf("<<");
					if (indice > 0){
						str = name.substring(0, indice);
						activity.setName(str);
					}else{
						activity.setName(name);
					}
				}else{
					String name = variant.getName();
					int indice = name.indexOf("<<");
					if (indice > 0){
						str = name.substring(0, indice);
						activity.setName(str);
					}else{
						activity.setName(name);
					}
				}
					
			}

		});
	}

	private Activity getCheckedVariant(Activity varpoint) {
		// TODO Auto-generated method stub
		List<SequenceFlow> incoming = varpoint.getIncoming();
		for (int i=0;i<incoming.size();i++){
			SequenceFlow b = incoming.get(i);
//			System.out.println(b.getName()+" <- sequenceFlow.");
			if (b.getSourceRef() instanceof Activity){
				Activity activity = (Activity)b.getSourceRef();
				if (activity.isVariant() && activity.isCheck()){
					return activity;
				}
			}
		}
		return null;
	}

	private boolean sweepVariants(Activity varpoint) {
		// deleting all unchecked variants

		while (hasUncheckedVariants(varpoint)){

			List<SequenceFlow> incoming = varpoint.getIncoming();
			for (int i=0;i<incoming.size();i++){
				SequenceFlow b = incoming.get(i);
//				System.out.println(b.getName()+" <- sequenceFlow.");
				if (b.getSourceRef() instanceof Activity){
					Activity activity = (Activity)b.getSourceRef();
					if (activity.isVariant() && !activity.isCheck()){
//						System.out.println(activity.getName()+" to delete.");
						deleteVariant(activity);
					}
				}
			}
		}
		return true;	
	}

	private boolean hasUncheckedVariants(Activity varpoint) {
		// verifying if has any unchecked variant
		
		List<SequenceFlow> incoming = varpoint.getIncoming();
		for (SequenceFlow b : incoming){
//			System.out.println(b.getName()+" <- sequenceFlow.");
			if (b.getSourceRef() instanceof Activity){
				Activity activity = (Activity)b.getSourceRef();
				if (activity.isVariant() && !activity.isCheck()){
					return true;
				}
			}
		}
		return false;
	}

	protected void deleteVariant(Activity activity) {
		Diagram diagram = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram();
		final IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();					
		FlowNode fn = (FlowNode)activity;
		final ContainerShape variant = getContainerShape(fn,diagram);
										
		TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// TODO Auto-generated method stub
				DeleteContext deleteContext = new DeleteContext(variant);
				IDeleteFeature deleteFeature = fp.getDeleteFeature(deleteContext);
				if (deleteFeature.canDelete(deleteContext))
					deleteFeature.delete(deleteContext);
			}

		});
	}

	private ContainerShape getContainerShape(FlowNode fn, Diagram diagram) {
		// TODO Auto-generated method stub
		for (Object o : Graphiti.getPeService().getLinkedPictogramElements(new EObject[] {fn}, diagram)) {
			if (o instanceof ContainerShape && !FeatureSupport.isLabelShape((ContainerShape)o)) {
				// this is the FlowNode shape
				return (ContainerShape)o;
			}
		}
		return null;
	}

	private void ValidateDiagram(Object bo) {
		// TODO Auto-generated method stub
		List<SequenceFlow> sequenceFlow = null;
		
		if (bo instanceof StartEvent){
			System.out.println("Start Event");
			//getting outgoing elements
			sequenceFlow = ((StartEvent) bo).getOutgoing();
			//for each outgoing element
			for (SequenceFlow a: sequenceFlow){
				//if it is activity
				if (a.getTargetRef() instanceof Activity){
					Activity activity = (Activity)a.getTargetRef();
//					System.out.println("Activity");
					//if it is varpoint
					if (activity.isVarPoint()){
//						if (checkVarpoints(activity))
							checkVarpoints(activity);
							ValidateDiagram((Object)activity);
					}
				}
			}
		}
		
		if (bo instanceof Activity){
			System.out.println("Activity");
			
//			if (checkVarpoints((Activity)bo)){
			//getting outgoing elements
				sequenceFlow = ((Activity) bo).getOutgoing();
				//for each outgoing element
				for (SequenceFlow a: sequenceFlow){
					//if it is activity
					if (a.getTargetRef() instanceof Activity){
						Activity activity = (Activity)a.getTargetRef();
						//if it is varpoint
						if (activity.isVarPoint()){
//							if (checkVarpoints(activity)){
								checkVarpoints(activity);
//								System.out.println(activity.getFeatureType());
								ValidateDiagram((Object)activity);
//							}
						}
					}
					if (a.getTargetRef() instanceof EndEvent){
						System.out.println("End Event ;)");
					}
				}
//			}
		}
	}

	protected boolean checkVarpoints(Activity activity) {
//		System.out.println(activity.getFeatureType());
		//if is Mandatory, must have selected variants
		String s = activity.getFeatureType();
		if (s != null){
			if ((activity.getFeatureType().equals("##mandatory"))){ //mandatory or none
				if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
					//check feature type
//					System.out.println(activity.getFeatureType()+"##OR");
					//maybe has variants
//					if (checkVariants(activity)){ //buscar por ao menos uma variante selecionada
//						return true;
//					}
					if (numberOfCheckedVariants(activity) == 0){ //varpoint não resolvida
						types.add(activity.getName());
						return false;
					}
					if (numberOfCheckedVariants(activity) > 1){
						if (checkVariants(activity)){ //se variantes possuem sequencia
							return true;
						}
						else{
							types.add(activity.getName());
							return false;
						}
					}
//					types.add(activity.getName());
//					System.out.println("VarPoint não resolvida!");
					return true;
				}
				if (activity.getVarPointType().equals("##XOR")){ //uma variante
					//check feature type
//					System.out.println(activity.getFeatureType()+"##XOR");
					
					if (activity.isSolved()) //se o varpoint isSolved, true
						return true;
					types.add(activity.getName());
//					System.out.println("VarPoint não resolvida!");
					return false;
				}
			}
			//if is Optional, must have selected variants
			if (activity.getFeatureType().equals("##optional")){
				if (activity.getVarPointType().equals("##OR")){
					//check feature type
//					System.out.println(activity.getFeatureType()+"##OR");
					//maybe has variants
	//				checkVariants(activity);
					if (numberOfCheckedVariants(activity) > 1){
						if (checkVariants(activity)){ //se variantes possuem sequencia
							return true;
						}
						else{
							types.add(activity.getName());
							return false;
						}
					}
					return true;
					
				}
				if (activity.getVarPointType().equals("##XOR")){
					//check feature type
//					System.out.println(activity.getFeatureType()+"##XOR");
	//				checkVariants(activity);
					return true;
				}
			}
		}
		else{ //varpoint ##none
			if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
				//check feature type
//				System.out.println(activity.getFeatureType()+"##OR");
				//maybe has variants
				if (numberOfCheckedVariants(activity) == 0){ //varpoint não resolvida
					types.add(activity.getName());
					return false;
				}
				if (numberOfCheckedVariants(activity) > 1){
					if (checkVariants(activity)){ //se variantes possuem sequencia
						return true;
					}
					else{
						types.add(activity.getName());
						return false;
					}
				}
//				System.out.println("VarPoint não resolvida!");
				return false;
				
			}
			if (activity.getVarPointType().equals("##XOR")){ //uma variante
				//check feature type
//				System.out.println(activity.getFeatureType()+"##XOR");
				
				if (activity.isSolved()) //se o varpoint isSolved, true
					return true;
				types.add(activity.getName());
//				System.out.println("VarPoint não resolvida!");
				return false;
			}
		}
		return false;
	}

	private boolean checkVariants(Activity varpoint) {
		//getting variants
		List<SequenceFlow> incoming = varpoint.getIncoming();
		int cont = 0;
		//for each variant
		for (SequenceFlow b: incoming){
			if (b.getSourceRef() instanceof Activity){
				Activity activity = (Activity)b.getSourceRef();
				if (activity.isVariant() && activity.isCheck() && (activity.getSeq()!=0)){
					cont++;
				}
			}
		}
		if (numberOfCheckedVariants(varpoint) == cont)
			return true;
		return false;	
	}

}
