/**
 * @author delacyr
 *
 */
package org.eclipse.bpmn2.modeler.ui.commands;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class SuperContainer {
	
	ContainerShape containerShape;
	int sequence;
	private String gateway;
	private String condition;
	private FlowNode fn;
	


	public SuperContainer(ContainerShape containerShape, FlowNode fn,
			int sequence, String gateway, String condition) {
		super();
		this.containerShape = containerShape;
		this.fn = fn;
		this.sequence = sequence;
		this.gateway = gateway;
		this.condition = condition;
	}



	public FlowNode getObject() {
		return fn;
	}


	public void setObject(FlowNode fn) {
		this.fn = fn;
	}


	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public ContainerShape getContainerShape() {
		return containerShape;
	}
	public void setContainerShape(ContainerShape containerShape) {
		this.containerShape = containerShape;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public void setContainerShapeSequence(ContainerShape containerShape, int sequence) {
		this.sequence = sequence;
		this.containerShape = containerShape;
	}
	
	public void setAll(ContainerShape containerShape, int sequence, String gateway, String condition) {
		// TODO Auto-generated method stub
		this.containerShape = containerShape;
		this.sequence = sequence;
		this.gateway = gateway;
		this.condition = condition;
	}

}
