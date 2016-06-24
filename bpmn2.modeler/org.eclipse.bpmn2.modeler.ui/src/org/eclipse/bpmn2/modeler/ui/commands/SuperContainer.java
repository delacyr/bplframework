package org.eclipse.bpmn2.modeler.ui.commands;

import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class SuperContainer {
	
	ContainerShape containerShape;
	int sequence;
	private String gateway;
	

	public SuperContainer(ContainerShape containerShape, int sequence,
			String gateway) {
		super();
		this.containerShape = containerShape;
		this.sequence = sequence;
		this.gateway = gateway;
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
	public SuperContainer(ContainerShape containerShape, int sequence) {
		super();
		this.containerShape = containerShape;
		this.sequence = sequence;
	}
	public SuperContainer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setAll(ContainerShape containerShape, int sequence, String gateway) {
		// TODO Auto-generated method stub
		this.containerShape = containerShape;
		this.sequence = sequence;
		this.gateway = gateway;
	}

}
