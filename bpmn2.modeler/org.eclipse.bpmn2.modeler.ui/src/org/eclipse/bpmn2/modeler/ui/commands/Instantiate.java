/**
 * @author delacyr
 *
 */
package org.eclipse.bpmn2.modeler.ui.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.OutputSet;
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
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.dialogs.ListDialog;
//import org.eclipse.bpmn2.modeler.ui.features.flow.Messages;

public class Instantiate extends AbstractHandler implements IHandler {

	List<String> types = new ArrayList<String>();
	protected Bpmn2Preferences preferences;
	SequenceFlow sequenceFlowConnection = null;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	
		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
		BPMNDiagram bpmnDiagram = editor.getBpmnDiagram();		
		DiagramElement element = bpmnDiagram.getRootElement();
		final List<EObject> elements = element.eContents();
		final List<Object> startEvents = new ArrayList<Object>();
		final List<Object> lanes = new ArrayList<Object>();
		final Diagram diagram = editor.getDiagramTypeProvider().getDiagram();
//		Percorrer a lista de elementos e começar pelo StartEventImpl
		for (EObject ob: elements){
			if (ob instanceof BPMNShape){
				PictogramElement pe = BusinessObjectUtil.getPictogramElementFromDiagram(diagram, (BPMNShape)ob);
				Object bo = BusinessObjectUtil.getBusinessObjectForPictogramElement(pe);
				if (bo instanceof StartEvent){
					startEvents.add(bo);
					ValidateDiagram(bo);
				}
				if (bo instanceof Lane){
					lanes.add(bo);
				}
			}
		}

		
//		ValidateDiagram(bo);
		
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
//			/*Copia o modelo configurado para a pasta Instantiated*/
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			IProject project = editor.getProject();
			IFile instantiated_file = editor.getModelFile();
			
			IFolder instantiatedFolder = project.getFolder("Instantiated");
			try {
				instantiatedFolder.create(true, true, progressMonitor);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (!instantiated_file.getParent().getName().equals("Instantiating")){ //se estiver na raiz
				
				IFolder instantiatedSubFolder = project.getFolder("Instantiated").getFolder(instantiated_file.getParent().getName());
				try {
					instantiatedSubFolder.create(true, true, progressMonitor);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				IPath path_instantiatedFolder = project.getFolder("Instantiated").getFolder(instantiated_file.getParent().getName()).getFullPath();
				path_instantiatedFolder = path_instantiatedFolder.append(instantiated_file.getName());
				
				try {
					instantiated_file.copy(path_instantiatedFolder, true, progressMonitor);
						
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				IFolder instantiatedSubFolder = project.getFolder("Instantiated");
				try {
					instantiatedSubFolder.create(true, true, progressMonitor);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				IPath path_instantiatedFolder = project.getFolder("Instantiated").getFullPath();
				path_instantiatedFolder = path_instantiatedFolder.append(instantiated_file.getName());
				
				try {
					instantiated_file.copy(path_instantiatedFolder, true, progressMonitor);
						
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			/*Instanciação do TMPN*/
			for (int i=0; i<startEvents.size();i++)
				generateModel(startEvents.get(i));
			
			alignShapes(startEvents.get(0));
			
//			if (!lanes.isEmpty()){
//				for (int i=0; i<lanes.size(); i++){
//					resizeLanes((EObject)lanes.get(i), elements, diagram);
//				}
//			}
				
			
//			ProgressMonitorDialog dialog = new ProgressMonitorDialog(null); 
//			try {
//				dialog.run(true, false, new IRunnableWithProgress(){
//				    public void run(IProgressMonitor monitor) {
//				        monitor.beginTask("Please wait a moment. Instantiating...", IProgressMonitor.UNKNOWN); 
//				        // execute the task ...
//				        for (int i=0; i<startEvents.size();i++)
//							generateModel(startEvents.get(i));
//						
//						alignShapes(startEvents.get(0));
//						
//						if (!lanes.isEmpty()){
//							for (int i=0; i<lanes.size(); i++){
//								resizeLanes((EObject)lanes.get(i), elements, diagram);
//							}
//						}
//				        
//				        monitor.done();
//				    }
//				});
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			boolean pass = false;
			if (!pass){
				editor.doSave(progressMonitor);
				pass = true;
			}
			
			if (pass){
				/*Copia o modelo instanciado para a pasta de PDOs*/
				IFolder BPDFolder = project.getFolder("BusinessProcessDiagram").getFolder(instantiated_file.getParent().getName());
				try {
					BPDFolder.create(true, true, progressMonitor);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				IPath path_BPDFolder = project.getFolder("BusinessProcessDiagram").getFolder(instantiated_file.getParent().getName()).getFullPath();
				path_BPDFolder = path_BPDFolder.append(instantiated_file.getName());
				try {
					instantiated_file.copy(path_BPDFolder, true, progressMonitor);
						
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*Exclui o modelo temporário da pasta Instantiating*/
				try {
					instantiated_file.delete(true, progressMonitor);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				org.eclipse.emf.common.util.URI modelURI;
				modelURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(path_BPDFolder.toString(), true);
				BPMN2Editor.openEditor(modelURI);
			}
			
		}
		types.clear();

		return null;
	}

	protected void resizeLanes(EObject lane, List<EObject> elements, Diagram diagram) {
		//			FlowNode lane = (FlowNode)lanes.get(0);
		//			EObject eob = null;
					int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
					ILayoutService layoutService = Graphiti.getLayoutService();
					ContainerShape elementShape = null;
					ContainerShape laneShape = getContainerShapefromEObject(lane, diagram);
				
					
					for (EObject ob: elements){
						if (ob instanceof BPMNShape){					
		
							elementShape = getContainerShapefromEObject(ob, diagram);
							
							if (laneShape == elementShape.getContainer()){
			//					Instruções de localização do shape
								ILocation loc = layoutService.getLocationRelativeToDiagram(elementShape);
								
								int x = loc.getX();
								int y = loc.getY();
								
								maxX = (x > maxX) ? x : maxX;
								maxY = (y > maxY) ? y : maxY;
								
								minX = (x < minX) ? x : minX;
								minY = (y < minY) ? y : minY;	
							}
						}
					}
					 //caso contrário só tem 1 elemento e os extremos serao os mesmos
					if (maxY != minY && maxX != minX){
						
					
	//					ContainerShape laneShape = elementShape.getContainer();
						if (laneShape!=BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram()) {
							ILocation loc = layoutService.getLocationRelativeToDiagram(laneShape);
							int x = loc.getX();
							int y = loc.getY();
							
			//				GraphicsAlgorithm ga = laneShape.getGraphicsAlgorithm();
			//				int width = ga.getWidth();
			//				int height = ga.getHeight();
									
							final ResizeShapeContext resizeContext = new ResizeShapeContext(laneShape);
							resizeContext.setLocation(x, (maxY-minY)/2);
							resizeContext.setHeight(maxY-minY+150);
							resizeContext.setWidth(maxX-minX+150);
							
							
								TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
								editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
									
									@Override
									protected void doExecute() {
										IResizeShapeFeature resizeShapeFeature = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider().getResizeShapeFeature(resizeContext);
										resizeShapeFeature.resizeShape(resizeContext);
										
									}
								});
							
							
						}
					}
	}
	
	private void alignShapes(Object bo) {
		// TODO Auto-generated method stub
		List<SequenceFlow> sequenceFlow = null;
		
		ContainerShape srcShape, dstShape;
		FlowNode objectSrcShape = null, objectDstShape = null;
		Diagram diagram = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram();
		
		if (bo instanceof FlowNode){
			
			objectSrcShape = (FlowNode)bo;
			srcShape = getContainerShape(objectSrcShape, diagram);
			
//			recuperar os objetos de dados para reposicioná-los
			if (objectSrcShape instanceof Activity){
				Activity src = (Activity)objectSrcShape;
				src.getDataInputAssociations();
				src.getDataOutputAssociations();
			}
			
			sequenceFlow = ((FlowNode) bo).getOutgoing();
			List<ContainerShape> dstShapes = new ArrayList<ContainerShape>();
				//for each outgoing element
			for (SequenceFlow a: sequenceFlow){
				objectDstShape = (FlowNode)a.getTargetRef();
				dstShape = getContainerShape(objectDstShape, diagram);
				dstShapes.add(dstShape);
			}
			moveShape(srcShape, objectSrcShape, dstShapes, objectDstShape);
			
			alignShapes(objectDstShape);
		}
	}

	private void moveShape(ContainerShape srcShape, FlowNode objectSrcShape,
			List<ContainerShape> dstShapes, FlowNode objectDstShape) {
		// TODO Auto-generated method stub
		IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();
		ILayoutService layoutService = Graphiti.getLayoutService();
		Diagram diagram = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram();
//		Instruções de localização do shape Source
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
		
//		Realiza o cálculo de reposicionamento
		int gatewayQty = dstShapes.size();
		int gatewayOffset = y;
		int count = 1, count2 = 1, count3 = 1;
		boolean pass = true;
		for (ContainerShape dstShape: dstShapes){
			final MoveShapeContext moveContext = new MoveShapeContext(dstShape);//new AreaContext(), newObject);
			final DefaultMoveShapeFeature moveFeature = (DefaultMoveShapeFeature)fp.getMoveShapeFeature(moveContext);
			IDimension size = GraphicsUtil.calculateSize(dstShape);
			int wOffset = 50;
			int w = size.getWidth();
			int h = size.getHeight();

			x += width + wOffset + w/2;
			if (gatewayQty > 1){
				
				if (gatewayQty % 2 != 0) //se nro de variantes é impar
					y += ((height/2 - (h*(gatewayQty*count))/2)) + gatewayOffset;
				else{ //é par
					if (pass == true){
						y += ( - (gatewayOffset/gatewayQty)*count2);
						pass = false;
						count2++;
					}else{
						y += ( + (gatewayOffset/gatewayQty)*count3);
						pass = true;
						count3++;
					}
				}
				if (count > 1){
					x -= width + wOffset + w/2;
				}
				
				count++;
			}
			else{
				List<SequenceFlow> sequenceFlow = objectDstShape.getIncoming();
				if (sequenceFlow.size() > 1){ //gateway final, possui 2 ou mais variantes entrando
					y = 0;
					for (SequenceFlow a: sequenceFlow){
						ContainerShape shape = getContainerShape(a.getSourceRef(), diagram);
						ILocation locY = layoutService.getLocationRelativeToDiagram(shape);
						y += locY.getY();
						
					}
					y = y/sequenceFlow.size();
				}
				else
					y += (height/2 - h/2);
			}

			moveContext.setX(x - xOffset);
			moveContext.setY(y - yOffset);
			moveContext.setSourceContainer( srcShape.getContainer() );
			moveContext.setTargetContainer( srcShape.getContainer() );
			
			if (moveFeature.canMoveShape(moveContext)){
				TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						// TODO Auto-generated method stub
						moveFeature.moveShape(moveContext);
					}
				});
			}
			y = gatewayOffset;
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
	
	protected void createNewConnection(ContainerShape oldShape, ContainerShape newShape, final String condition) {
		Tuple<FixPointAnchor, FixPointAnchor> anchors = AnchorUtil.getSourceAndTargetBoundaryAnchors(oldShape, newShape, null);

		final CreateConnectionContext ccc = new CreateConnectionContext();
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
						sequenceFlowConnection.setName(condition);
						
						AddConnectionContext acc = new AddConnectionContext(ccc.getSourceAnchor(), ccc.getTargetAnchor());
						acc.setNewObject(sequenceFlowConnection);
						IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();
						@SuppressWarnings("unused")
						Connection connection = (Connection)fp.addIfPossible(acc);
					}

		});
		

		
//		return connection;
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
//							else
//								generateModel((Object)activity);
					}
					SweepDataObjects(activity);
					generateModel((Object)activity);
				}else{ 
					if (!(a.getTargetRef() instanceof EndEvent)){
						generateModel((Object)a.getTargetRef());
					}
				}
			}
		}
	}

	private Object sweepVarpoints(final Activity activity) {
		
		Activity node = null;
		Object next = null;
		String s = activity.getFeatureType();
		if (s != null){
			if ((activity.getFeatureType().equals("##mandatory"))){ //mandatory or none
				
				SweepDataObjects(activity);
				
				if (!hasVariant(activity) && activity.isSolved()){ //quando varpoint não tem variantes
					copyDataVariant(activity,activity);
					return next;
				}
				if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
					if (numberOfCheckedVariants(activity) == 1){ //somente uma variante selecionada
//						if (sweepVariants(activity)){ //exclui variantes não selecionadas
//							node = getCheckedVariant(activity); //pega a variant selecionada	
//							deleteNode(node);
//							copyDataVariant(activity,node);
//							updateContext(activity);
//						}
						return generateSingleVariant(activity);
					}
					else{ //duas ou mais variantes selecionadas		
						return generateOrVarpoint(activity, next);					
//						return next;
					}
				}
				if (activity.getVarPointType().equals("##XOR")){ //uma variante
					if (activity.isSolved()){ //se o varpoint isSolved, true
//						if (sweepVariants(activity)){ //exclui variantes não selecionadas
//							node = getCheckedVariant(activity); //pega a variant selecionada
//							deleteNode(node);
//							copyDataVariant(activity,node);
//							updateContext(activity);
//						}
						return generateSingleVariant(activity);
					}
				}
			}
			//if is Optional, must have selected variants
			if (activity.getFeatureType().equals("##optional")){
				
				SweepDataObjects(activity);
				
				if (!hasVariant(activity) && activity.isSolved()){ //quando varpoint não tem variantes
					copyDataVariant(activity,activity);
					return next;
				}
				
				int count = numberOfCheckedVariants(activity);
				if (count > 0){ //ao menos uma variante selecionada
					if (activity.getVarPointType().equals("##OR")){
						if (numberOfCheckedVariants(activity) == 1){ //somente uma variante selecionada
//							if (sweepVariants(activity)){ //exclui variantes não selecionadas
//								node = getCheckedVariant(activity); //pega a variant selecionada
//								deleteNode(node);
//								copyDataVariant(activity,node);
//								updateContext(activity);
//							}
							return generateSingleVariant(activity);
						}
						else{ //duas ou mais variantes selecionadas		
							return generateOrVarpoint(activity, next);
								
//							return next;
						}
						
					}
					if (activity.getVarPointType().equals("##XOR")){ //uma variante
						if (activity.isSolved()){ //se o varpoint isSolved, true
//							if (sweepVariants(activity)){ //exclui variantes não selecionadas
//								node = getCheckedVariant(activity); //pega a variant selecionada
//								deleteNode(node);
//								copyDataVariant(activity,node);
//								updateContext(activity);
//							}
							return generateSingleVariant(activity);
						}
					}
				}
				else{ //nenhuma variante selecionada
					ContainerShape sourceShape,targetShape;
					FlowNode source = null,target = null;
					
					if (sweepVariants(activity)){ //exclui variantes
						List<SequenceFlow> incoming = activity.getIncoming();
						for (int i=0;i<incoming.size();i++){
							SequenceFlow b = incoming.get(i);
							if (b.getSourceRef() instanceof FlowNode){
								source = (FlowNode)b.getSourceRef();
								next = source;
							}
						}
						
						List<SequenceFlow> outgoing = activity.getOutgoing();
						for (int i=0;i<outgoing.size();i++){
							SequenceFlow b = outgoing.get(i);
							if (b.getTargetRef() instanceof FlowNode){
								target = (FlowNode)b.getTargetRef();
							}
						}
						
						deleteObject(activity);
						sourceShape = getContainerShape(source,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
						targetShape = getContainerShape(target,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
						createNewConnection(sourceShape, targetShape, "");
						
					}
				}
			}
		}
		else{ //varpoint ##none
			
			SweepDataObjects(activity);
			
			if (!hasVariant(activity) && activity.isSolved()){ //quando varpoint não tem variantes
				copyDataVariant(activity,activity);
				return next;
			}
			if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
				if (numberOfCheckedVariants(activity) == 1){ //somente uma variante selecionada
//					if (sweepVariants(activity)){ //exclui variantes não selecionadas
//						node = getCheckedVariant(activity); //pega a variant selecionada
//						deleteNode(node);
//						copyDataVariant(activity,node);
//						updateContext(activity);
//					}
					
					return generateSingleVariant(activity);
					
				}
				else{ //duas ou mais variantes selecionadas		
					return generateOrVarpoint(activity, next);
						
//					return next;
				}
			}
			if (activity.getVarPointType().equals("##XOR")){ //uma variante
				//check feature type
				if (activity.isSolved()){ //se o varpoint isSolved, true
					return generateSingleVariant(activity);
				}
			}
		}
		return next;
	}

	private void SweepDataObjects(final Activity activity) {
		// TODO Auto-generated method stub
		List<DataInputAssociation> dataInputAssociations = activity.getDataInputAssociations();
		List<DataOutputAssociation> dataOutputAssociations = activity.getDataOutputAssociations();
		
//		for (DataInputAssociation dia: dataInput){
		if (!dataInputAssociations.isEmpty()){
			DataInputAssociation dia = null;
			List<ItemAwareElement> dataInputs = new ArrayList<ItemAwareElement>();
			int tam = dataInputAssociations.size();
			for (int i=0; i<tam; i++){
				dia = dataInputAssociations.get(i);
				if (dia.getSourceRef() != null && !dia.getSourceRef().isEmpty()){
					
					if (((ItemAwareElement)dia.getSourceRef().get(0)).isVarPoint()){ //if is varpoint
						
						dataInputs.addAll(hasDataVariants(dia.getSourceRef().get(0))); //catch them all variants
						
						if (!dataInputs.isEmpty()){ //if has variants
							deleteObject(dia.getSourceRef().get(0)); //Delete DataObject Varpoint
							tam = dataInputAssociations.size();
							i--;
						}else{
							if (((ItemAwareElement)dia.getSourceRef().get(0)).isCheck()){ //is checked
								disqualifyDO((ItemAwareElement)dia.getSourceRef().get(0));
							}else{												
								deleteObject(dia.getSourceRef().get(0)); //delete non checked varpoint
								tam = dataInputAssociations.size();
								i--;
							}
						}
					}else{ //data object comunalidade
						if (activity.isVarPoint() && !activity.isSolved()){
							deleteObject(dia.getSourceRef().get(0));
							tam = dataInputAssociations.size();
							i--;
						}
					}
				}
			}

		//	lets create dataAssociations between task and itemawareelements
			if (dataInputs!=null && !dataInputs.isEmpty()){
		
				for (final ItemAwareElement di: dataInputs){
		//			DataAssociation dataAssoc = null;
					BaseElement source = di;
					BaseElement target = activity;
					InputOutputSpecification ioSpec = null;
					InputSet inputSet = null;
					
					disqualifyDO(di); //di isn't a variant anymore
					
					DataInputAssociation dataInputAssoc = null;
	//				if (target instanceof Activity) {
					
					// if the target is an Activity, create an ioSpecification if it doesn't have one yet
					Activity activity_target = (Activity) target;
					ioSpec = activity_target.getIoSpecification();
					if (ioSpec==null) {
						ioSpec = (InputOutputSpecification) Bpmn2ModelerFactory.createFeature(activity_target, "ioSpecification"); //$NON-NLS-1$
					}
					if (ioSpec.getInputSets().size()==0) {
						inputSet = Bpmn2ModelerFactory.create(InputSet.class);
						ioSpec.getInputSets().add(inputSet);
					}
					else {
						// add to first input set we find
						// TODO: support input set selection if there are more than one
						inputSet = ioSpec.getInputSets().get(0);
					}
					
		//					dataInputAssoc = CONNECTION
					dataInputAssoc = selectInput(source, target, ioSpec.getDataInputs(), activity_target.getDataInputAssociations(), inputSet);
					
					
					final IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();
					ContainerShape sourceCS = getContainerShapefromEObject(di,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
					ContainerShape targetCS = getContainerShapefromEObject(activity,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
		
					Tuple<FixPointAnchor, FixPointAnchor> anchors = AnchorUtil.getSourceAndTargetBoundaryAnchors(sourceCS, targetCS, null);
		
					final CreateConnectionContext context = new CreateConnectionContext();
					context.setSourcePictogramElement(sourceCS);
					context.setTargetPictogramElement(targetCS);
					context.setSourceAnchor(anchors.getFirst());
					context.setTargetAnchor(anchors.getSecond());
					
					ILayoutService layoutService = Graphiti.getLayoutService();
					ILocation locSource = layoutService.getLocationRelativeToDiagram(sourceCS);
					ILocation locTarget = layoutService.getLocationRelativeToDiagram(targetCS);
					context.setSourceLocation(locSource);
					context.setTargetLocation(locTarget);
					
					final BaseElement businessObject = (BaseElement)dataInputAssoc;
					
					if (businessObject!=null){
						
						TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								Connection connection = null;
								AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
								addContext.setNewObject(businessObject);
						
								IPeService peService = Graphiti.getPeService();
								IGaService gaService = Graphiti.getGaService();
								ILocation loc, shapeLoc;
								
								// the CreateConnectionContext contains the source and target locations - the actual
								// mouse locations where the connection was started and ended. These locations must
								// be passed to the AddConnectionContext so they can be added (as String properties)
								// to the Connection once it is created. These String properties are then decoded in
								// AnchorUtil.getSourceAndTargetBoundaryAnchors() to create Ad Hoc anchors if necessary.
								loc = context.getSourceLocation();
								if (loc==null)
									loc = peService.getLocationRelativeToDiagram(context.getSourceAnchor());
								shapeLoc = peService.getLocationRelativeToDiagram((Shape)context.getSourceAnchor().getParent());
								Point p = gaService.createPoint(
										loc.getX() - shapeLoc.getX(),
										loc.getY() - shapeLoc.getY());
								addContext.putProperty(AnchorUtil.CONNECTION_SOURCE_LOCATION, p);
								
								loc = context.getTargetLocation();
								if (loc==null)
									loc = peService.getLocationRelativeToDiagram(context.getTargetAnchor());
								shapeLoc = peService.getLocationRelativeToDiagram((Shape)context.getTargetAnchor().getParent());
								p = gaService.createPoint(
										loc.getX() - shapeLoc.getX(),
										loc.getY() - shapeLoc.getY());
								addContext.putProperty(AnchorUtil.CONNECTION_TARGET_LOCATION, p);
								addContext.putProperty(AnchorUtil.CONNECTION_CREATED, Boolean.TRUE);
						
								connection = (Connection) fp.addIfPossible(addContext);
								ModelUtil.setID(businessObject);
						
								FeatureSupport.updateConnection(fp, connection);
							}
		
						});
					}
		
				}
			}
		}
			
		if (!dataOutputAssociations.isEmpty()){
			DataOutputAssociation doa = null;
			List<ItemAwareElement> dataOutputs = new ArrayList<ItemAwareElement>();
			int tamOutput = dataOutputAssociations.size();
			for (int i=0; i<tamOutput; i++){
				doa = dataOutputAssociations.get(i);
				if (doa.getTargetRef() != null){
					
					if (((ItemAwareElement)doa.getTargetRef()).isVarPoint()){ //if is varpoint
						
						dataOutputs.addAll(hasDataVariants(doa.getTargetRef()));
						
						if (!dataOutputs.isEmpty()){
							deleteObject(doa.getTargetRef()); //Delete DataObject Varpoint
							tamOutput = dataOutputAssociations.size();
							i--;
						}else{
							if (((ItemAwareElement)doa.getTargetRef()).isCheck()){ //is checked
								disqualifyDO((ItemAwareElement)doa.getTargetRef());
							}else{												
								deleteObject(doa.getTargetRef()); //delete non checked varpoint
								tamOutput = dataOutputAssociations.size();
								i--;
							}
						}
					}else{ //data object comunalidade
						if (activity.isVarPoint() && !activity.isSolved()){
							deleteObject(doa.getTargetRef());
							tamOutput = dataOutputAssociations.size();
							i--;
						}
					}
				}
			}
				
		//	há datainput variante
			if (dataOutputs!=null && !dataOutputs.isEmpty()){
		
				for (final ItemAwareElement dOut: dataOutputs){
		//			DataAssociation dataAssoc = null;
					BaseElement source = activity;
					BaseElement target = dOut;
					InputOutputSpecification ioSpec = null;
					OutputSet outputSet = null;
					
					disqualifyDO(dOut); //di isn't a variant anymore
					
					DataOutputAssociation dataOutputAssoc = null;
	//				
					
					// if the target is an Activity, create an ioSpecification if it doesn't have one yet
					Activity activity_source = (Activity) source;
					ioSpec = activity_source.getIoSpecification();
					if (ioSpec==null) {
						ioSpec = (InputOutputSpecification) Bpmn2ModelerFactory.createFeature(activity_source, "ioSpecification"); //$NON-NLS-1$
					}
					if (ioSpec.getOutputSets().size()==0) {
						outputSet = Bpmn2ModelerFactory.create(OutputSet.class);
						ioSpec.getOutputSets().add(outputSet);
					}
					else {
						// add to first input set we find
						// TODO: support input set selection if there are more than one
						outputSet = ioSpec.getOutputSets().get(0);
					}
					
		//					dataOutputAssoc = CONNECTION
					dataOutputAssoc = selectOutput(source, target, ioSpec.getDataOutputs(), activity_source.getDataOutputAssociations(), outputSet);
					
					
					final IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();
					ContainerShape targetCS = getContainerShapefromEObject(dOut,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
					ContainerShape sourceCS = getContainerShapefromEObject(activity,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
		
					Tuple<FixPointAnchor, FixPointAnchor> anchors = AnchorUtil.getSourceAndTargetBoundaryAnchors(sourceCS, targetCS, null);
		
					final CreateConnectionContext contextOutput = new CreateConnectionContext();
					contextOutput.setSourcePictogramElement(sourceCS);
					contextOutput.setTargetPictogramElement(targetCS);
					contextOutput.setSourceAnchor(anchors.getFirst());
					contextOutput.setTargetAnchor(anchors.getSecond());
					
					ILayoutService layoutService = Graphiti.getLayoutService();
					ILocation locSource = layoutService.getLocationRelativeToDiagram(sourceCS);
					ILocation locTarget = layoutService.getLocationRelativeToDiagram(targetCS);
					contextOutput.setSourceLocation(locSource);
					contextOutput.setTargetLocation(locTarget);
					
					final BaseElement businessObject = (BaseElement)dataOutputAssoc;
					
					if (businessObject!=null){
						
						TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								Connection connection = null;
								AddConnectionContext addContext = new AddConnectionContext(contextOutput.getSourceAnchor(), contextOutput.getTargetAnchor());
								addContext.setNewObject(businessObject);
						
								IPeService peService = Graphiti.getPeService();
								IGaService gaService = Graphiti.getGaService();
								ILocation loc, shapeLoc;
								
								// the CreateConnectionContext contains the source and target locations - the actual
								// mouse locations where the connection was started and ended. These locations must
								// be passed to the AddConnectionContext so they can be added (as String properties)
								// to the Connection once it is created. These String properties are then decoded in
								// AnchorUtil.getSourceAndTargetBoundaryAnchors() to create Ad Hoc anchors if necessary.
								loc = contextOutput.getSourceLocation();
								if (loc==null)
									loc = peService.getLocationRelativeToDiagram(contextOutput.getSourceAnchor());
								shapeLoc = peService.getLocationRelativeToDiagram((Shape)contextOutput.getSourceAnchor().getParent());
								Point p = gaService.createPoint(
										loc.getX() - shapeLoc.getX(),
										loc.getY() - shapeLoc.getY());
								addContext.putProperty(AnchorUtil.CONNECTION_SOURCE_LOCATION, p);
								
								loc = contextOutput.getTargetLocation();
								if (loc==null)
									loc = peService.getLocationRelativeToDiagram(contextOutput.getTargetAnchor());
								shapeLoc = peService.getLocationRelativeToDiagram((Shape)contextOutput.getTargetAnchor().getParent());
								p = gaService.createPoint(
										loc.getX() - shapeLoc.getX(),
										loc.getY() - shapeLoc.getY());
								addContext.putProperty(AnchorUtil.CONNECTION_TARGET_LOCATION, p);
								addContext.putProperty(AnchorUtil.CONNECTION_CREATED, Boolean.TRUE);
						
								connection = (Connection) fp.addIfPossible(addContext);
								ModelUtil.setID(businessObject);
						
								FeatureSupport.updateConnection(fp, connection);
							}
		
						});
					}
		
				}
			}
		}
		
	}

	private DataOutputAssociation selectOutput(final BaseElement source,
			final BaseElement target, final List<DataOutput> dataOutputs,
			List<DataOutputAssociation> dataOutputAssociations,
			final OutputSet outputSet) {
		// TODO Auto-generated method stub
		final List<DataOutputAssociation> DOA = new ArrayList<DataOutputAssociation>();
		
		TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// TODO Auto-generated method stub
				EObject object = null;
				EStructuralFeature objectFeature = null;
				EStructuralFeature sourceFeature = null;

				object = ((Activity)source).getIoSpecification();
				objectFeature = Bpmn2Package.eINSTANCE.getInputOutputSpecification_DataOutputs();
				sourceFeature = Bpmn2Package.eINSTANCE.getActivity_DataOutputAssociations();

				DataOutput dataOutput = null;
				dataOutput = Bpmn2Factory.eINSTANCE.createDataOutput();
				
				DataOutputAssociation dataOutputAssoc = null;
				dataOutput = Bpmn2ModelerFactory.createFeature(object, objectFeature, DataOutput.class);
				dataOutputs.add(dataOutput);
				outputSet.getDataOutputRefs().add(dataOutput);
				dataOutputAssoc = (DataOutputAssociation) Bpmn2ModelerFactory.createFeature(source, sourceFeature);
				dataOutputAssoc.getSourceRef().add(dataOutput);
				
				dataOutputAssoc.setTargetRef((ItemAwareElement) target);
				
				DOA.add(dataOutputAssoc);

			}

		});
		
		return DOA.get(0);
	}

	private DataInputAssociation selectInput(final BaseElement source,
			final BaseElement target, final List<DataInput> dataInputs,
			final List<DataInputAssociation> dataInputAssociations, final InputSet inputSet) {
		// TODO Auto-generated method stub
		
		final List<DataInputAssociation> DIA = new ArrayList<DataInputAssociation>();
		
		TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// TODO Auto-generated method stub
				EObject object = null;
				EStructuralFeature objectFeature = null;
				EStructuralFeature targetFeature = null;

				object = ((Activity)target).getIoSpecification();
				objectFeature = Bpmn2Package.eINSTANCE.getInputOutputSpecification_DataInputs();
				targetFeature = Bpmn2Package.eINSTANCE.getActivity_DataInputAssociations();

				DataInput dataInput = null;
				dataInput = Bpmn2Factory.eINSTANCE.createDataInput();
				
				DataInputAssociation dataInputAssoc = null;
				dataInput = Bpmn2ModelerFactory.createFeature(object, objectFeature, DataInput.class);
				dataInputs.add(dataInput);
				inputSet.getDataInputRefs().add(dataInput);
				dataInputAssoc = (DataInputAssociation) Bpmn2ModelerFactory.createFeature(target, targetFeature);
				dataInputAssoc.setTargetRef(dataInput);
				
				dataInputAssoc.getSourceRef().clear();
				dataInputAssoc.getSourceRef().add((ItemAwareElement) source);
				
				DIA.add(dataInputAssoc);

			}

		});
		
		return DIA.get(0);
	}

	private List<ItemAwareElement> hasDataVariants(BaseElement itemAwareElement) {
		// TODO Auto-generated method stub
		
		if (itemAwareElement instanceof DataInput){
		
			List<ItemAwareElement> dataInput = new ArrayList<ItemAwareElement>();
			EObject object = ((ItemAwareElement)itemAwareElement).eContainer();
			
			if (object instanceof InputOutputSpecification){
				InputOutputSpecification IOS = (InputOutputSpecification)object;
				List<DataInput> dataInputs = IOS.getDataInputs();
				
				int tam = dataInputs.size();
				for (int i=0; i<tam; i++){
					List<DataOutputAssociation> doas = dataInputs.get(i).getDataOutputAssociations();
					for (DataOutputAssociation DOA: doas){
						if (DOA.getTargetRef() == itemAwareElement){
							if (dataInputs.get(i).isCheck()){
								dataInput.add(dataInputs.get(i));
							}
							else{
								deleteObject(dataInputs.get(i));
								tam = dataInputs.size();
								i--;
							}
							break;
						}
					}
				}
				
				return dataInput;
			}
		}else if (itemAwareElement instanceof DataOutput){
			
			List<ItemAwareElement> dataOutput = new ArrayList<ItemAwareElement>();
			EObject object = ((ItemAwareElement)itemAwareElement).eContainer();
			
			if (object instanceof InputOutputSpecification){
				InputOutputSpecification IOS = (InputOutputSpecification)object;
				List<DataOutput> dataOutputs = IOS.getDataOutputs();
				
				int tam = dataOutputs.size();
				for (int i=0; i<tam; i++){
					List<DataOutputAssociation> doas = dataOutputs.get(i).getDataOutputAssociations();
					for (DataOutputAssociation DOA: doas){
						if (DOA.getTargetRef() == itemAwareElement){
							if (dataOutputs.get(i).isCheck()){
								dataOutput.add(dataOutputs.get(i));
							}
							else{
								deleteObject(dataOutputs.get(i));
								tam = dataOutputs.size();
								i--;
							}
							break;
						}
					}
				}
				
				return dataOutput;
			}
		}
		
		return null;
	}

	protected void disqualifyDO(final ItemAwareElement IAE) {
		TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// TODO Auto-generated method stub
				IAE.setVariant(false);
				IAE.setCheck(false);
				IAE.setVarPoint(false);
				
				String name = null;
				if (IAE instanceof DataInput){
					name = ((DataInput)IAE).getName();
				}
				if (IAE instanceof DataOutput){
					name = ((DataOutput)IAE).getName();
				}
				String str = null;
				if (name != null){
//					String name = task.getInstanceName();
					int indice = name.indexOf("<<");
					if (indice > 0){
						str = name.substring(0, indice);
//						task.setName(str);
						if (IAE instanceof DataInput){
							((DataInput)IAE).setName(str);
						}
						if (IAE instanceof DataOutput){
							((DataOutput)IAE).setName(str);
						}
					}else{
//						task.setName(name);
						if (IAE instanceof DataInput){
							((DataInput)IAE).setName(name);
						}
						if (IAE instanceof DataOutput){
							((DataOutput)IAE).setName(name);
						}
					}
				}else{
//					String name = task.getName();
					int indice = name.indexOf("<<");
					if (indice > 0){
						str = name.substring(0, indice);
//						task.setName(str);
						if (IAE instanceof DataInput){
							((DataInput)IAE).setName(str);
						}
						if (IAE instanceof DataOutput){
							((DataOutput)IAE).setName(str);
						}
					}else{
//						task.setName(name);
						if (IAE instanceof DataInput){
							((DataInput)IAE).setName(name);
						}
						if (IAE instanceof DataOutput){
							((DataOutput)IAE).setName(name);
						}
					}
				}
			}

		});
	}
	
	public Object generateSingleVariant(final Activity activity) {
		ContainerShape sourceShape,targetShape, midShape;
		FlowNode source = null,target = null, mid = null;
		if (sweepVariants(activity)){ //exclui variantes
			List<SequenceFlow> incoming = activity.getIncoming();
			for (int i=0;i<incoming.size();i++){
				SequenceFlow b = incoming.get(i);
				if (b.getSourceRef() instanceof FlowNode){
					if (b.getSourceRef() instanceof Activity){
						if (!((Activity) b.getSourceRef()).isVariant()){
							source = (FlowNode)b.getSourceRef();
						}
					}
					else
						source = (FlowNode)b.getSourceRef();
					//next = source;
				}
			}
			
			List<SequenceFlow> outgoing = activity.getOutgoing();
			for (int i=0;i<outgoing.size();i++){
				SequenceFlow b = outgoing.get(i);
				if (b.getTargetRef() instanceof FlowNode){
					target = (FlowNode)b.getTargetRef();
				}
			}
			
			mid = getCheckedVariant(activity);
			SweepDataObjects((Activity)mid);
			
			deleteObject(activity);
			copyDataVariant((Activity)mid,(Activity)mid);
			sourceShape = getContainerShape(source,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
			targetShape = getContainerShape(target,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
			midShape = getContainerShape(mid,BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram());
			createNewConnection(sourceShape, midShape, "");
			createNewConnection(midShape, targetShape, "");
		}
//		return target; 18/08
		return mid;
	}
	
	public boolean hasVariant(Activity activity) {
		List<SequenceFlow> sf = activity.getIncoming();
		for (SequenceFlow sequenceFlow: sf){
			if (sequenceFlow.getSourceRef() instanceof Activity){
				if (((Activity)sequenceFlow.getSourceRef()).isVariant())
					return true;							
			}
		}
		return false;
	}

	protected Object generateOrVarpoint(final Activity activity, Object next) {
		
		sweepVariants(activity); //limpa as variantes não selecionadas
		
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
		
//		Armazenando o shape e um valor recognizable as first shape
		superShape = new SuperContainer(srcShape,fn,0,"","");
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
//				if (task.isVariant() && task.isCheck()){
				if (task.isCheck()){
					FlowNode fn2 = (FlowNode)task;
					
					SweepDataObjects((Activity)fn2); //trata os objetos de dados
					
					dstShape = getContainerShape(fn2,diagram);
					
//					Armazenando o shape, um valor da sequencia e o gateway
					superShape = new SuperContainer(dstShape,fn2,task.getSeq(),task.getGateway(),task.getCondition());
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
		
//		Limpando indices vazios
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
			
			if (fn2 instanceof Activity){
				SweepDataObjects((Activity)fn2); //trata os objetos de dados
			}
			
			dstShape = getContainerShape(fn2,diagram);
			
//			Armazenando o shape e um valor recognizable as last shape
			superShape = new SuperContainer(dstShape,fn2,99,"","");
			temp = new ArrayList<SuperContainer>();
			temp.add(superShape);
			group.add(new ArrayList<SuperContainer>(temp));
		}
				
//			preferences = Bpmn2Preferences.getInstance((EObject)activity);
//			deleteNode(activity);


			srcShape = group.get(0).get(0).getContainerShape();
			group.remove(0);
			for (int i=0;i<group.size();i++){
				List<SuperContainer> shapes = group.get(i);
				ContainerShape shape = null;
				if (shapes.size() == 1){
					if (i != group.size()-1){
//						Somente 1 variante com essa sequencia
						dstShape = shapes.get(0).getContainerShape();
						next = shapes.get(0).getObject();
//						moveShape(srcShape, dstShape);
						createNewConnection(srcShape, dstShape, "");						
						srcShape = dstShape;
					}
					else{
//						Última instância quando ligamos o último gateway ao próximo dstShape
						dstShape = shapes.get(0).getContainerShape();
//						moveShape(srcShape, dstShape);
						createNewConnection(srcShape, dstShape, "");
					}
				}
//				Lista com mais de 1 variante com mesma sequência
				if (shapes.size() > 1){
					ShapeEditor gateway = createGateway(srcShape, shapes.get(0).getGateway());										
					createNewConnection(srcShape, gateway.getShape(),"");
					
					for (int j=0;j<shapes.size();j++){
						shape = shapes.get(j).getContainerShape();
						String condition = shapes.get(j).getCondition();
						createNewConnection(gateway.getShape(), shape, condition);
					}
					
					ShapeEditor gateway2 = createGateway(shape, shapes.get(0).getGateway());
					
					for (int j=0;j<shapes.size();j++){
						shape = shapes.get(j).getContainerShape();
						createNewConnection(shape, gateway2.getShape(),"");
					}			
					
					srcShape = gateway2.getShape();
					next = gateway2.getObject();
				}
			}
			
			deleteObject(activity);
		
		return next;
	}

	protected ShapeEditor createGateway(ContainerShape srcShape, String type) {
		final ShapeEditor gateway = new ShapeEditor();
		final ICreateFeature cf;
		final CreateContext cc = gateway.getICreateContext();
		final ContainerShape srcShape0 = srcShape;
		
//		switch (type) {
//		case "##AND":
//			cf = gateway.getICreateFeature(1);
//			break;
//		case "##OR":
//			cf = gateway.getICreateFeature(2);
//			break;
//		case "##XOR":
//			cf = gateway.getICreateFeature(3);
//			break;
//		default:
//			cf = gateway.getICreateFeature(1);
//			break;
//		}
		
		if (type.equals("##OR")){
			cf = gateway.getICreateFeature(2);
		}else{
			if (type.equals("##XOR")){
				cf = gateway.getICreateFeature(3);
			}else{
				cf = gateway.getICreateFeature(1);
			}
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
						
						SweepDataObjects(activity);
						
//						System.out.println(activity.getName()+" to delete.");
//						iterar sobre os dados de entrada e saida e ir deletando todos, deletar a activity por ultimo
						List<DataInputAssociation> dataInput = activity.getDataInputAssociations();
						List<DataOutputAssociation> dataOutput = activity.getDataOutputAssociations();
						
						if (!dataInput.isEmpty()){
							int tam = dataInput.size();
							for (int j=0; j<tam;j++){
								deleteObject(dataInput.get(j).getSourceRef().get(0));
								tam = dataInput.size();
								j--;
							}
//							for (DataInputAssociation DI: dataInput){
////								DI.getSourceRef();
//								dataInput.remove(DI);
//								deleteNode(DI.getTargetRef());
//							}
						}
						
						if (!dataOutput.isEmpty()){
							int tam = dataOutput.size();
							for (int j=0; j<tam;j++){
								deleteObject(dataOutput.get(j).getTargetRef());
								tam = dataOutput.size();
								j--;
							}
//							for (DataOutputAssociation DO: dataOutput){
////								DO.getSourceRef();
//								deleteNode(DO.getTargetRef());
//							}
						}
						
						deleteObject(activity);
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

	protected boolean deleteObject(EObject fn) { //se nao der certo, volta pro FlowNode
		Diagram diagram = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram();
		final IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();					
//		FlowNode fn = (FlowNode)activity;
		final ContainerShape variant = getContainerShapefromEObject(fn,diagram);
										
		TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// TODO Auto-generated method stub
				DeleteContext deleteContext = new DeleteContext(variant);
				IDeleteFeature deleteFeature = fp.getDeleteFeature(deleteContext);
//				if (deleteFeature.canDelete(deleteContext))
					deleteFeature.delete(deleteContext);
			}

		});
		return true;
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
	
	private ContainerShape getContainerShapefromEObject(EObject fn, Diagram diagram) {
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
		
		if (bo instanceof FlowNode){
			//getting outgoing elements
			sequenceFlow = ((FlowNode) bo).getOutgoing();
			//for each outgoing element
			for (SequenceFlow a: sequenceFlow){
				//if (a.getTargetRef() instanceof FlowNode){
					//((FlowNode)a.getTargetRef()).getOutgoing();
				//if it is activity
					if (a.getTargetRef() instanceof Activity){
						Activity activity = (Activity)a.getTargetRef();
	//					System.out.println("Activity");
						//if it is varpoint
						if (activity.isVarPoint()){
	//						if (checkVarpoints(activity))
								checkVarpoints(activity);
								ValidateDiagram((Object)activity);
						}else{
							checkDataObjects(activity);
						}
					}else if (!(a.getTargetRef() instanceof EndEvent)){
						ValidateDiagram((Object)a.getTargetRef());
					}
			}
		}
		
//		if (bo instanceof Activity){
//			//getting outgoing elements
//				sequenceFlow = ((Activity) bo).getOutgoing();
//				//for each outgoing element
//				for (SequenceFlow a: sequenceFlow){
//					//if it is activity
//					if (a.getTargetRef() instanceof Activity){
//						Activity activity = (Activity)a.getTargetRef();
//						//if it is varpoint
//						if (activity.isVarPoint()){
////							if (checkVarpoints(activity)){
//								checkVarpoints(activity);
////								System.out.println(activity.getFeatureType());
//								ValidateDiagram((Object)activity);
////							}
//						}
//					}
//				}
//		}
	}

	private boolean checkDataObjects(Activity activity) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<DataInputAssociation> dataInputAssociations = activity.getDataInputAssociations();
		List<DataOutputAssociation> dataOutputAssociations = activity.getDataOutputAssociations();
		
//		for (DataInputAssociation dia: dataInput){
		if (!dataInputAssociations.isEmpty()){
			DataInputAssociation dia = null;
			List<ItemAwareElement> dataInputs = new ArrayList<ItemAwareElement>();
			int tam = dataInputAssociations.size();
			for (int i=0; i<tam; i++){
				dia = dataInputAssociations.get(i);
				if (dia.getSourceRef() != null && !dia.getSourceRef().isEmpty()){
					
					ItemAwareElement IAE = (ItemAwareElement)dia.getSourceRef().get(0);
					
					if (IAE.isVarPoint()){ //if is varpoint
						
						if (IAE.getFeatureType()!=null){
							
							if (IAE.getFeatureType().equals("##mandatory") && !IAE.isSolved()){
								types.add(((DataInput)IAE).getName());
								return false;
							}
						}
						
//						dataInputs.addAll(hasDataVariants(dia.getSourceRef().get(0))); //catch them all variants
						
//						if (!dataInputs.isEmpty()){ //if has variants
//							deleteObject(dia.getSourceRef().get(0)); //Delete DataObject Varpoint
//							tam = dataInputAssociations.size();
//							i--;
//						}else{
//							if (((ItemAwareElement)dia.getSourceRef().get(0)).isCheck()){ //is checked
//								disqualifyDO((ItemAwareElement)dia.getSourceRef().get(0));
//							}else{												
//								deleteObject(dia.getSourceRef().get(0)); //delete non checked varpoint
//							}
//						}
					}
				}
			}
		}
		
		if (!dataOutputAssociations.isEmpty()){
			DataOutputAssociation doa = null;
			List<ItemAwareElement> dataOutputs = new ArrayList<ItemAwareElement>();
			int tamOutput = dataOutputAssociations.size();
			for (int i=0; i<tamOutput; i++){
				doa = dataOutputAssociations.get(i);
				if (doa.getTargetRef() != null){
					
					ItemAwareElement IAE = (ItemAwareElement)doa.getTargetRef();
					
					if (IAE.isVarPoint()){ //if is varpoint
						
						if (IAE.getFeatureType()!=null){
							
							if (IAE.getFeatureType().equals("##mandatory") && !IAE.isSolved()){
								types.add(((DataOutput)IAE).getName());
								return false;
							}
						}
						
//						dataOutputs.addAll(hasDataVariants(doa.getTargetRef()));
//						
//						if (!dataOutputs.isEmpty()){
//							deleteObject(doa.getTargetRef()); //Delete DataObject Varpoint
//							tamOutput = dataOutputAssociations.size();
//							i--;
//						}else{
//							if (((ItemAwareElement)doa.getTargetRef()).isCheck()){ //is checked
//								disqualifyDO((ItemAwareElement)doa.getTargetRef());
//							}else{												
//								deleteObject(doa.getTargetRef()); //delete non checked varpoint
//							}
//						}
					}
				}
			}
		}
		return true;				
	}

//	verificar variants de objetos
//	if (itemAwareElement instanceof DataInput){
//		
//		List<ItemAwareElement> dataInput = new ArrayList<ItemAwareElement>();
//		EObject object = ((ItemAwareElement)itemAwareElement).eContainer();
//		
//		if (object instanceof InputOutputSpecification){
//			InputOutputSpecification IOS = (InputOutputSpecification)object;
//			List<DataInput> dataInputs = IOS.getDataInputs();
//			
//			int tam = dataInputs.size();
//			for (int i=0; i<tam; i++){
//				List<DataOutputAssociation> doas = dataInputs.get(i).getDataOutputAssociations();
//				for (DataOutputAssociation DOA: doas){
//					if (DOA.getTargetRef() == itemAwareElement){
//						if (dataInputs.get(i).isCheck()){
//							dataInput.add(dataInputs.get(i));
//						}
//						else{
//							deleteObject(dataInputs.get(i));
//							tam = dataInputs.size();
//							i--;
//						}
//						break;
//					}
//				}
//			}
//			
//			return dataInput;
//		}
//	}else if (itemAwareElement instanceof DataOutput){
//		
//		List<ItemAwareElement> dataOutput = new ArrayList<ItemAwareElement>();
//		EObject object = ((ItemAwareElement)itemAwareElement).eContainer();
//		
//		if (object instanceof InputOutputSpecification){
//			InputOutputSpecification IOS = (InputOutputSpecification)object;
//			List<DataOutput> dataOutputs = IOS.getDataOutputs();
//			
//			int tam = dataOutputs.size();
//			for (int i=0; i<tam; i++){
//				List<DataOutputAssociation> doas = dataOutputs.get(i).getDataOutputAssociations();
//				for (DataOutputAssociation DOA: doas){
//					if (DOA.getTargetRef() == itemAwareElement){
//						if (dataOutputs.get(i).isCheck()){
//							dataOutput.add(dataOutputs.get(i));
//						}
//						else{
//							deleteObject(dataOutputs.get(i));
//							tam = dataOutputs.size();
//							i--;
//						}
//						break;
//					}
//				}
//			}
//			
//			return dataOutput;
//		}
//	}
	
	protected boolean checkVarpoints(Activity activity) {
		//if is Mandatory, must have selected variants
		String s = activity.getFeatureType();
		if (s != null){
			if ((activity.getFeatureType().equals("##mandatory"))){ //mandatory or none
				if (activity.getVarPointType() != null){
					if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
						if (hasVariant(activity)){
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
						}
						return true;
					}
					if (activity.getVarPointType().equals("##XOR")){ //uma variante
						if (activity.isSolved()) //se o varpoint isSolved, true
							return true;
						types.add(activity.getName());
	//					System.out.println("VarPoint não resolvida!");
						return false;
					}
					if (!activity.isCheck()){
						types.add(activity.getName());
						return false;
					}
				}else{ //se for null e tiver variantes
					if (hasVariant(activity)){
						types.add(activity.getName());
						return false;
					}else{ //varpoint sem variantes
						return true;
					}
				}
			}
			//if is Optional, must have selected variants
			if (activity.getFeatureType().equals("##optional")){
				if (activity.getVarPointType() != null){
					if (activity.getVarPointType().equals("##OR")){
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
						return true;
					}
				}else{ //se for null e tiver variantes
					if (hasVariant(activity)){
						types.add(activity.getName());
						return false;
					}else{ //varpoint sem variantes
						return true;
					}
				}
				return true;
			}
		}
		else{ //varpoint ##none
			if (activity.getVarPointType() != null){
					if (activity.getVarPointType().equals("##OR")){ //uma ou mais variantes
						//maybe has variants
						if (hasVariant(activity)){
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
						}else{
							if (!activity.isCheck()){
								types.add(activity.getName());
								return false;
							}
						}
					}
				
					if (activity.getVarPointType().equals("##XOR")){ //uma variante
						
						if (activity.isSolved()) //se o varpoint isSolved, true
							return true;
						types.add(activity.getName());
						return false;
					}
					if (!activity.isCheck()){
						types.add(activity.getName());
						return false;
					}
			
		}else{ //se for null e tiver variantes
			if (hasVariant(activity)){
				types.add(activity.getName());
				return false;
			}else{ //varpoint sem variantes
				return true;
			}
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
				String s = activity.getFeatureType();
				if (s != null)
					if (activity.getFeatureType().equals("##mandatory") && !activity.isCheck()){
						return false;
					}
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