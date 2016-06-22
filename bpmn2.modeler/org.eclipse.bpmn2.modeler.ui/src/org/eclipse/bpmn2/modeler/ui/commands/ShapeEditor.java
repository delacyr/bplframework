package org.eclipse.bpmn2.modeler.ui.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILayoutService;

public class ShapeEditor {
	ContainerShape shape;
	
	public void createNewShape(ContainerShape oldShape, ICreateFeature createFeature, CreateContext createContext){
		ILayoutService layoutService = Graphiti.getLayoutService();
		IFeatureProvider fp = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getFeatureProvider();
//		boolean horz = preferences.isHorizontalDefault();
		boolean horz = true;

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
			moveFeature.moveShape(moveContext);
		}
		
		shape = newShape;
	}
	
	public ContainerShape getNewShape(){
		return shape;
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
}


