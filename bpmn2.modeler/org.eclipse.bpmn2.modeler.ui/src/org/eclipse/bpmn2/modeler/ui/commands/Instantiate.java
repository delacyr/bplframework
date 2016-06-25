package org.eclipse.bpmn2.modeler.ui.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.Tuple;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.dialogs.ListDialog;

public class Instantiate extends AbstractHandler implements IHandler {

	List<String> types = new ArrayList<String>();
	protected Bpmn2Preferences preferences;
	SequenceFlow sequenceFlowConnection = null;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	
		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
		BPMNDiagram bpmnDiagram = editor.getBpmnDiagram();		
		DiagramElement element = bpmnDiagram.getRootElement();
		List<EObject> elements = element.eContents();
//		Percorrer a lista de elementos e começar pelo StartEventImpl
		BPMNShape bpmnShape = (BPMNShape)elements.get(0);

		Diagram diagram = editor.getDiagramTypeProvider().getDiagram();
		PictogramElement pe = BusinessObjectUtil.getPictogramElementFromDiagram(diagram, bpmnShape);
		Object bo = BusinessObjectUtil.getBusinessObjectForPictogramElement(pe);
		
		ValidateDiagram(bo);
		
//		editor.doSave(null);
		
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
//			/*Copia o modelo configurado para a pasta Instantiated*/
//			IProgressMonitor progressMonitor = new NullProgressMonitor();
//			IProject project = editor.getProject();
//			IFolder instantiatedFolder = project.getFolder("Instantiated");
//			try {
//				instantiatedFolder.create(true, true, progressMonitor);
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
////				e.printStackTrace();
//			}
//	
//			IFile instantiated_file = editor.getModelFile();
//			IPath path_instantiatedFolder = project.getFolder("Instantiated").getFullPath();
//			path_instantiatedFolder = path_instantiatedFolder.append(instantiated_file.getName());
//			
//			try {
//				instantiated_file.copy(path_instantiatedFolder, true, progressMonitor);
//					
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			/*Instanciação do TMPN*/
			generateModel(bo);
			
//			boolean pass = false;
//			if (!pass){
//				editor.doSave(progressMonitor);
//				pass = true;
//			}
//			
//			if (pass){
//				/*Copia o modelo instanciado para a pasta de PDOs*/
//				IPath path_BPDFolder = project.getFolder("BusinessProcessDiagram").getFullPath();
//				path_BPDFolder = path_BPDFolder.append(instantiated_file.getName());
//				try {
//					instantiated_file.copy(path_BPDFolder, true, progressMonitor);
//						
//				} catch (CoreException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				/*Exclui o modelo temporário da pasta Instantiating*/
//				try {
//					instantiated_file.delete(true, progressMonitor);
//				} catch (CoreException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}	
//				
//				org.eclipse.emf.common.util.URI modelURI;
//				modelURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(path_BPDFolder.toString(), true);
//				BPMN2Editor.openEditor(modelURI);
//			}
			
		}
		types.clear();

		return null;
	}
	
	private void moveShape(ContainerShape srcShape, ContainerShape dstShape) {
		// TODO Auto-generated method stub
		IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();
		ILayoutService layoutService = Graphiti.getLayoutService();
//		boolean horz = preferences.isHorizontalDefault();
		boolean horz = true;

		ILocation loc = layoutService.getLocationRelativeToDiagram(srcShape);
		int x = loc.getX();
		int y = loc.getY();
		int xOffset = 0;
		int yOffset = 0;
		GraphicsAlgorithm ga = srcShape.getGraphicsAlgorithm();
		int width = ga.getWidth();
		int height = ga.getHeight();
		
		FlowElement dstObject = null;
		
		ContainerShape containerShape = srcShape.getContainer();
		if (containerShape!=BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram()) {
			// we are adding a new shape to a container (e.g a SubProcess)
			// so we need to adjust the location to be relative to the
			// container instead of the diagram
			loc = layoutService.getLocationRelativeToDiagram(containerShape);
			xOffset = loc.getX();
			yOffset = loc.getY();
		}
		
		BaseElement srcObject = BusinessObjectUtil.getFirstElementOfType(srcShape, BaseElement.class);
		if (srcObject instanceof Lane) {
			((Lane)srcObject).getFlowNodeRefs().add((FlowNode)dstObject);
		}
		
		// move the new shape so that it does not collide with an existing shape
		final MoveShapeContext moveContext = new MoveShapeContext(dstShape);//new AreaContext(), newObject);
		final DefaultMoveShapeFeature moveFeature = (DefaultMoveShapeFeature)fp.getMoveShapeFeature(moveContext);
		IDimension size = GraphicsUtil.calculateSize(dstShape);
		int wOffset = 50;
		int hOffset = 50;
		int w = size.getWidth();
		int h = size.getHeight();
		if (horz) {
			x += width + wOffset + w/2;
			y += height/2 - h/2;
			boolean done = false;
			while (!done) {
				done = true;
				List<Shape> shapes = getFlowElementChildren(containerShape);
				for (Shape s : shapes) {
					if (GraphicsUtil.intersects(s, x-w/2, y-h/2, w, h)) {
						y += 100;
						done = false;
						break;
					}
				}
			}
		}
		else {
			x += width/2 - w/2;
			y += height + hOffset + h/2;
			boolean done = false;
			while (!done) {
				done = true;
				List<Shape> shapes = getFlowElementChildren(containerShape);
				for (Shape s : shapes) {
					if (GraphicsUtil.intersects(s, x-w/2, y-h/2, w, h)) {
						x += 100;
						done = false;
						break;
					}
				}
			}
		}
		moveContext.setX(x - xOffset);
		moveContext.setY(y - yOffset);
		moveContext.setSourceContainer( srcShape.getContainer() );
		moveContext.setTargetContainer( srcShape.getContainer() );
		
		if (moveFeature.canMoveShape(moveContext)){
//			moveFeature.moveShape(moveContext);
		
		
			TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					// TODO Auto-generated method stub
					moveFeature.moveShape(moveContext);
				}
	
			});
		
		}
	}

	protected List<Shape> getFlowElementChildren(ContainerShape containerShape) {
			List<Shape> children = new ArrayList<Shape>();
			for (Shape s : containerShape.getChildren()) {
				FlowElement bo = BusinessObjectUtil.getFirstElementOfType(s, FlowElement.class);
				if (s instanceof ContainerShape && bo!=null) {
					children.add(s);
				}
			}
			return children;
	}

	protected void disqualifyTask(final Activity task) {
		TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// TODO Auto-generated method stub
				task.setVariant(false);
				task.setCheck(false);
				task.setVarPoint(false);
				
				String str = null;
				if (task.getInstanceName() != null){
					String name = task.getInstanceName();
					int indice = name.indexOf("<<");
					if (indice > 0){
						str = name.substring(0, indice);
						task.setName(str);
					}else{
						task.setName(name);
					}
				}else{
					String name = task.getName();
					int indice = name.indexOf("<<");
					if (indice > 0){
						str = name.substring(0, indice);
						task.setName(str);
					}else{
						task.setName(name);
					}
				}
			}

		});
	}
	
	protected Connection createNewConnection(ContainerShape oldShape, ContainerShape newShape) {
		Tuple<FixPointAnchor, FixPointAnchor> anchors = AnchorUtil.getSourceAndTargetBoundaryAnchors(oldShape, newShape, null);

		CreateConnectionContext ccc = new CreateConnectionContext();
		ccc.setSourcePictogramElement(oldShape);
		ccc.setTargetPictogramElement(newShape);
		ccc.setSourceAnchor(anchors.getFirst());
		ccc.setTargetAnchor(anchors.getSecond());

		final FlowNode oldObject = BusinessObjectUtil.getFirstElementOfType(oldShape, FlowNode.class);
		final FlowNode newObject = BusinessObjectUtil.getFirstElementOfType(newShape, FlowNode.class);
		
		// create a new SequenceFlow to connect the old and new FlowNodes

				
				final FlowElementsContainer container = (FlowElementsContainer) oldObject.eContainer();
				sequenceFlowConnection = Bpmn2ModelerFactory.create(oldObject.eResource(), SequenceFlow.class);
				
				
				TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						// TODO Auto-generated method stub
						
						container.getFlowElements().add(sequenceFlowConnection);
						sequenceFlowConnection.setSourceRef(oldObject);
						sequenceFlowConnection.setTargetRef(newObject);
						sequenceFlowConnection.setName(null);
					}

		});
		

		AddConnectionContext acc = new AddConnectionContext(ccc.getSourceAnchor(), ccc.getTargetAnchor());
		acc.setNewObject(sequenceFlowConnection);
		IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();
		Connection connection = (Connection)fp.addIfPossible(acc);
		return connection;
	}
	
	protected ContainerShape createNewShape(ContainerShape oldShape, final ICreateFeature createFeature, final CreateContext createContext) {
		ILayoutService layoutService = Graphiti.getLayoutService();
		IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();
		boolean horz = preferences.isHorizontalDefault();

		ILocation loc = layoutService.getLocationRelativeToDiagram(oldShape);
		int x = loc.getX();
		int y = loc.getY();
		int xOffset = 0;
		int yOffset = 0;
		GraphicsAlgorithm ga = oldShape.getGraphicsAlgorithm();
		int width = ga.getWidth();
		int height = ga.getHeight();
		
		final FlowElement newObject;
		final ContainerShape newShape;
		createContext.setX(0);
		createContext.setY(0);
	
		Object[] created = createFeature.create(createContext);
		newObject = (FlowElement) created[0];
		newShape = (ContainerShape) created[1];		
		
		ContainerShape containerShape = oldShape.getContainer();
		if (containerShape!=BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram()) {
			// we are adding a new shape to a container (e.g a SubProcess)
			// so we need to adjust the location to be relative to the
			// container instead of the diagram
			loc = layoutService.getLocationRelativeToDiagram(containerShape);
			xOffset = loc.getX();
			yOffset = loc.getY();
		}
		
		BaseElement oldObject = BusinessObjectUtil.getFirstElementOfType(oldShape, BaseElement.class);
		if (oldObject instanceof Lane) {
			((Lane)oldObject).getFlowNodeRefs().add((FlowNode)newObject);
		}
		
		// move the new shape so that it does not collide with an existing shape
		final MoveShapeContext moveContext = new MoveShapeContext(newShape);//new AreaContext(), newObject);
		final DefaultMoveShapeFeature moveFeature = (DefaultMoveShapeFeature)fp.getMoveShapeFeature(moveContext);
		IDimension size = GraphicsUtil.calculateSize(newShape);
		int wOffset = 50;
		int hOffset = 50;
		int w = size.getWidth();
		int h = size.getHeight();
		if (horz) {
			x += width + wOffset + w/2;
			y += height/2 - h/2;
			boolean done = false;
			while (!done) {
				done = true;
				List<Shape> shapes = getFlowElementChildren(containerShape);
				for (Shape s : shapes) {
					if (GraphicsUtil.intersects(s, x-w/2, y-h/2, w, h)) {
						y += 100;
						done = false;
						break;
					}
				}
			}
		}
		else {
			x += width/2 - w/2;
			y += height + hOffset + h/2;
			boolean done = false;
			while (!done) {
				done = true;
				List<Shape> shapes = getFlowElementChildren(containerShape);
				for (Shape s : shapes) {
					if (GraphicsUtil.intersects(s, x-w/2, y-h/2, w, h)) {
						x += 100;
						done = false;
						break;
					}
				}
			}
		}
		moveContext.setX(x - xOffset);
		moveContext.setY(y - yOffset);
		moveContext.setSourceContainer( oldShape.getContainer() );
		moveContext.setTargetContainer( oldShape.getContainer() );
		
		if (moveFeature.canMoveShape(moveContext)){
			TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					moveFeature.moveShape(moveContext);
				}
			});
		}
		
		return newShape;
	}

	private void generateModel(Object bo) {
		// TODO Auto-generated method stub
		List<SequenceFlow> sequenceFlow = null;
		Object next = null;
		
		if (bo instanceof FlowNode){
			//getting outgoing elements
			sequenceFlow = ((FlowNode) bo).getOutgoing();
			//for each outgoing element
			for (SequenceFlow a: sequenceFlow){
				//if it is activity
				if (a.getTargetRef() instanceof Activity){
					Activity activity = (Activity)a.getTargetRef();
					//if it is varpoint
					if (activity.isVarPoint()){
						next = sweepVarpoints(activity);
							if (next!=null)
								generateModel(next);
							else
								generateModel((Object)activity);
					}
				}
			}
		}
	}

	private Object sweepVarpoints(final Activity activity) {
		// TODO Auto-generated method stub
		Activity variant = null;
		Object next = null;
		String s = activity.getFeatureType();
		if (s != null){
			if ((activity.getFeatureType().equals("##mandatory"))){ //mandatory or none
				if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
					if (numberOfCheckedVariants(activity) == 1){ //somente uma variante selecionada
						if (sweepVariants(activity)){ //exclui variantes não selecionadas
							variant = getCheckedVariant(activity); //pega a variant selecionada
							deleteNode(variant);
							copyDataVariant(activity,variant);
							updateContext(activity);
//							return null;
						}
					}
					else{ //duas ou mais variantes selecionadas		
						sweepVariants(activity);
						List<SequenceFlow> sequenceFlow = null;
						SuperContainer superShape = null;
						ArrayList<List<SuperContainer>> group = new ArrayList<List<SuperContainer>>();
						while(group.size() < 100) group.add(new ArrayList<SuperContainer>());
						ContainerShape srcShape = null;
						ContainerShape dstShape = null;
						
						//getting incoming elements
						sequenceFlow = activity.getIncoming(); //pega fluxos entrando
						FlowNode fn = null;
						for (SequenceFlow sFlow : sequenceFlow) {
							fn = (FlowNode)sFlow.getSourceRef(); //pega o source do primeiro fluxo entrado
							if (fn instanceof Activity){
								Activity a = (Activity)fn;
								if (!a.isVarPoint() && !a.isVariant()) break;
							}
							else break;
						}
						BPMN2Editor editor = BPMN2Editor.getActiveEditor();
						Diagram diagram = editor.getDiagramTypeProvider().getDiagram();
						srcShape = getContainerShape(fn,diagram);
						
//						Armazenando o shape e um valor recognizable as first shape
						superShape = new SuperContainer(srcShape,0,"none");
						List<SuperContainer> temp = new ArrayList<SuperContainer>(group.get(0));
						temp.add(superShape);
						group.set(0, new ArrayList<SuperContainer>(temp));
						
						List<SequenceFlow> incoming = activity.getIncoming();
						Activity task = null;
						Integer seq=0;
						for (int i=0;i<incoming.size();i++){
							SequenceFlow b = incoming.get(i);
							if (b.getSourceRef() instanceof Activity){
								task = (Activity)b.getSourceRef();
								if (task.isVariant() && task.isCheck()){
									FlowNode fn2 = (FlowNode)task;
									dstShape = getContainerShape(fn2,diagram);
									
//									Armazenando o shape, um valor da sequencia e o gateway
									superShape = new SuperContainer(dstShape,task.getSeq(),task.getGateway());
									temp = new ArrayList<SuperContainer>(group.get(task.getSeq()));
									temp.add(superShape);
									group.set(task.getSeq(), new ArrayList<SuperContainer>(temp));

									if (task.getSeq() > seq && task.getSeq()<100){
										seq = task.getSeq();
										next = task;
									}
									
									disqualifyTask(task); //jogar cpyData dentro dessa funcao
//									copyDataVariant(task,task);
								}
							}
						}
						
//						Limpando indices vazios
						int max = group.size();
						for (int i=max-1; i>=0;i--){
							List<SuperContainer> mem = group.get(i);
							if (mem.size() == 0)
								group.remove(i);
						}
						
						List<SequenceFlow> outgoing = activity.getOutgoing();
						for (int i=0;i<outgoing.size();i++){
							SequenceFlow b = outgoing.get(i);
							FlowNode fn2 = (FlowNode)b.getTargetRef();
							dstShape = getContainerShape(fn2,diagram);
							
//							Armazenando o shape e um valor recognizable as last shape
							superShape = new SuperContainer(dstShape,99,"none");
							temp = new ArrayList<SuperContainer>();
							temp.add(superShape);
							group.add(new ArrayList<SuperContainer>(temp));
						}


								
//						preferences = Bpmn2Preferences.getInstance((EObject)activity);
						deleteNode(activity);
		
						srcShape = group.get(0).get(0).getContainerShape();
						group.remove(0);
						for (int i=0;i<group.size();i++){
							List<SuperContainer> shapes = group.get(i);
							ContainerShape shape = null;
							if (shapes.size() == 1){
								if (i != group.size()-1){
	//								Somente 1 variante com essa sequencia
									dstShape = shapes.get(0).getContainerShape();
									moveShape(srcShape, dstShape);
									createNewConnection(srcShape, dstShape);						
									srcShape = dstShape;
								}
								else{
//									Última instância quando ligamos o último gateway ao próximo dstShape
									dstShape = shapes.get(0).getContainerShape();
									moveShape(srcShape, dstShape);
									createNewConnection(srcShape, dstShape);
								}
							}
//							Lista com mais de 1 variante com mesma sequência
							if (shapes.size() > 1){
								ShapeEditor gateway = createGateway(srcShape, shapes.get(0).getGateway());										
								createNewConnection(srcShape, gateway.getShape());
								
								for (int j=0;j<shapes.size();j++){
									shape = shapes.get(j).getContainerShape();
									createNewConnection(gateway.getShape(), shape);
								}
								
								ShapeEditor gateway2 = createGateway(shape, shapes.get(0).getGateway());
								
								for (int j=0;j<shapes.size();j++){
									shape = shapes.get(j).getContainerShape();
									createNewConnection(shape, gateway2.getShape());
								}			
								
								srcShape = gateway2.getShape();
								next = gateway2.getObject();
							}
						}
											
//						srcShape = cShape.get(0);
//						cShape.remove(0);
//						for (ContainerShape cs: cShape) {
//							dstShape = cs;
//							moveShape(srcShape, dstShape);
//							createNewConnection(srcShape, dstShape);						
//							srcShape = dstShape;
//						}
							
						return next;
					}
				}
				if (activity.getVarPointType().equals("##XOR")){ //uma variante
					if (activity.isSolved()){ //se o varpoint isSolved, true
						if (sweepVariants(activity)){ //exclui variantes não selecionadas
							variant = getCheckedVariant(activity); //pega a variant selecionada
							deleteNode(variant);
							copyDataVariant(activity,variant);
							updateContext(activity);
//							return true;
						}
					}
//					return false;
				}
			}
			//if is Optional, must have selected variants
			if (activity.getFeatureType().equals("##optional")){
				if (activity.getVarPointType().equals("##OR")){
 
					
				}
				if (activity.getVarPointType().equals("##XOR")){
					if (sweepVariants(activity));
//						return true;

//					return false;
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
						deleteNode(variant);
						copyDataVariant(activity,variant);
						updateContext(activity);
//						return true;
					}
				}
//					return false;
			}
		}
		return next;
	}

	protected ShapeEditor createGateway(ContainerShape srcShape, String type) {
		final ShapeEditor gateway = new ShapeEditor();
		final ICreateFeature cf;
		final CreateContext cc = gateway.getICreateContext();
		final ContainerShape srcShape0 = srcShape;
		
		switch (type) {
		case "##AND":
			cf = gateway.getICreateFeature(1);
			break;
		case "##OR":
			cf = gateway.getICreateFeature(2);
			break;
		case "##XOR":
			cf = gateway.getICreateFeature(3);
			break;
		default:
			cf = gateway.getICreateFeature(1);
			break;
		}
		
		TransactionalEditingDomain editingDomain1 = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain1.getCommandStack().execute(new RecordingCommand(editingDomain1) {
			@Override
			protected void doExecute() {
				// TODO Auto-generated method stub
				gateway.createNewShape(srcShape0, cf, cc);
			}
		});
		return gateway;
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
						deleteNode(activity);
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

	protected void deleteNode(Activity activity) {
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
				}
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