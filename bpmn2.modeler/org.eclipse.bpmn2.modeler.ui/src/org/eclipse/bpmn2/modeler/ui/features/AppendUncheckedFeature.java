/**
 * @author delacyr
 *
 */
package org.eclipse.bpmn2.modeler.ui.features;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.features.activity.Messages;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class AppendUncheckedFeature extends AbstractCustomFeature{

	public AppendUncheckedFeature(IFeatureProvider fp) {
		super(fp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return Messages.AppendUncheckedFeature_Name;
	}

	@Override
	public String getDescription() {
		return Messages.AppendUncheckedFeature_Description;
	}

	@Override
	public String getImageId() {
			return ImageProvider.IMG_16_UNCHECKED;
	}

	@Override
	public boolean isAvailable(IContext context) {
		return true;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		
		/*Feature exclusiva da instanciação*/
		if (BPMN2Editor.getActiveEditor().getBpmnDiagram().getPhase().equals("EPN")){
//			IFile file = editor.getModelFile();
//			if (file.getParent().getName().equals("Instantiating")){
			
			if (pes != null && pes.length == 1) {
				PictogramElement pe = pes[0];
				Object bo = getBusinessObjectForPictogramElement(pe);
				if (bo instanceof Activity) {
					Activity activity = (Activity)bo;
					if (activity.isVariant()){
						if (!activity.isCheck())
							return true;
						else
							return false;
					}
					if (activity.isVarPoint()){
						if (!hasVariant(bo) && !activity.isCheck()){
							return true;
						}
						else{
							return false;
						}
					}
				}
				if (bo instanceof ItemAwareElement){
						ItemAwareElement element = (ItemAwareElement)bo;
						if (element.isVarPoint()){
							if (!hasVariant(bo) && !element.isCheck()) //se nao tem variantes e está selecionada
								return true;
							else
								return false;
						}
						else{
							if (element.isVariant() && !element.isCheck()) //se é variante e está selecionada
								return true;
							else
								return false;
						}
				}
			}
		}
			return false;
	}

		public boolean hasVariant(Object bo) {
			
			if (bo instanceof Activity){
				List<SequenceFlow> sf = ((Activity)bo).getIncoming();
				for (SequenceFlow sequenceFlow: sf){
					if (sequenceFlow.getSourceRef() instanceof Activity){
						if (((Activity)sequenceFlow.getSourceRef()).isVariant())
							return true;							
					}
				}
			}
			if (bo instanceof ItemAwareElement){
				
				ItemAwareElement element = (ItemAwareElement)bo;
				
				EObject object = element.eContainer();
				
				if (object instanceof InputOutputSpecification){
					InputOutputSpecification IOS = (InputOutputSpecification)object;
					List<DataInput> dataInputs = IOS.getDataInputs();
					for (DataInput di: dataInputs){
						List<DataOutputAssociation> doas = di.getDataOutputAssociations();
						for (DataOutputAssociation DOA: doas){
							if (DOA.getTargetRef() == element){
								return true;
							}
						}
					}
				}
				
				if (object instanceof InputOutputSpecification){
					InputOutputSpecification IOS = (InputOutputSpecification)object;
					List<DataOutput> dataOutputs = IOS.getDataOutputs();
					for (DataOutput dos: dataOutputs){
						List<DataOutputAssociation> doas = dos.getDataOutputAssociations();
						for (DataOutputAssociation DOA: doas){
							if (DOA.getTargetRef() == element){
								return true;
							}
						}
					}
				}
				
//				List<DataInputAssociation> DIA = element.getDataInputAssociations();
//				for (DataInputAssociation d: DIA){
//					if (d.getSourceRef().get(0).isVariant()){
//						return true;
//					}
//				}
//				List<DataOutputAssociation> DOA = element.getDataOutputAssociations();
//				for (DataOutputAssociation d: DOA){
//					if (d.getTargetRef().isVariant()){
//						return true;
//					}
//				}
			}
			
			return false;
		}

	@Override
	public void execute(ICustomContext context) {
		// TODO Auto-generated method stub
		//Variant selected
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			PictogramElement pe = pes[0];
			checkVariabilityType(pe);
			setFillColor((ContainerShape)pe,pe);
		}
	}
	
	private void setFillColor(ContainerShape containerShape, PictogramElement pe) {
		Shape shape = containerShape.getChildren().get(0);
		Object bo = getBusinessObjectForPictogramElement(pe);
//		Activity variant = (Activity)getBusinessObjectForPictogramElement(pe);
		BaseElement baseElement = BusinessObjectUtil.getFirstBaseElement(containerShape);
		if (bo instanceof Activity){
			Activity variant = (Activity)bo;
			Activity parent = null;
			if (variant!=null && variant.isCheck()) {
				
				if (variant.isVariant() && variant.getSeq() == 0){	
					List<SequenceFlow> outgoing = variant.getOutgoing();
					for (SequenceFlow sf: outgoing){
						if (sf.getTargetRef() instanceof Activity)
							if (((Activity)sf.getTargetRef()).isVarPoint() && ((Activity)sf.getTargetRef()).getVarPointType().equals("##OR")){
								parent = (Activity)sf.getTargetRef();
								if (numberOfCheckedVariantsWoSeq(parent) > 1){ //se marcar variante e tiver outra marcada
//									if (parent.getVarPointType().equals("##OR")){
										setAllWoSeqYellow(parent);
										ShapeStyle ss = new ShapeStyle();
										ss.setDefaultColors(IColorConstant.YELLOW);
										StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
//									}
								}else{
									ShapeStyle ss = new ShapeStyle();
									ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
									StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
								}
							}else{
								ShapeStyle ss = new ShapeStyle();
								ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
								StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
							}
					}
				}else{			
					ShapeStyle ss = new ShapeStyle();
					ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
					StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
				}
				
	//			UpdateContext updateContext = new UpdateContext(shape);
	//			IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(updateContext);
	//			if ( updateFeature.updateNeeded(updateContext).toBoolean() )
	//				updateFeature.update(updateContext);
			}
		}
		if (bo instanceof ItemAwareElement){
			ItemAwareElement element = (ItemAwareElement)bo;
			if (element!=null && element.isCheck()){
				ShapeStyle ss = new ShapeStyle();
				ss.setDefaultColors(IColorConstant.LIGHT_ORANGE);
				StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
			}
		}
	}
	
	private void setAllWoSeqYellow(Activity parent) {
		// TODO Auto-generated method stub
		Diagram diagram = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagram();
		
		List<SequenceFlow> SF = parent.getIncoming();
		for (SequenceFlow sFlow: SF){
			if (sFlow.getSourceRef() instanceof Activity){
				Activity activity = (Activity)sFlow.getSourceRef();
				if (activity.isCheck() && activity.getSeq()==0){
					ContainerShape containerShape = getContainerShape((FlowNode)activity, diagram);
					Shape shape = containerShape.getChildren().get(0);
					BaseElement baseElement = BusinessObjectUtil.getFirstBaseElement(containerShape);
					ShapeStyle ss = new ShapeStyle();
					ss.setDefaultColors(IColorConstant.YELLOW);
					StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
				}
			}
		}
		
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
	
	private int numberOfCheckedVariantsWoSeq(Activity varpoint) {
		// TODO Auto-generated method stub
		List<SequenceFlow> incoming = varpoint.getIncoming();
		int count = 0;
		for (int i=0;i<incoming.size();i++){
			SequenceFlow b = incoming.get(i);
			if (b.getSourceRef() instanceof Activity){
				Activity activity = (Activity)b.getSourceRef();
				if (activity.isVariant() && activity.isCheck() && activity.getSeq() == 0){
					count++;
				}
			}
		}
		return count;
	}

	public void checkVariabilityType(PictogramElement pe){
		Object bo = getBusinessObjectForPictogramElement(pe);

		if (bo instanceof Activity){
			Activity activity = (Activity)bo;
			Activity target = null;
			
			if (activity.isVarPoint()){
				activity.setCheck(true);
				activity.setSolved(true);
			}else{
				List<SequenceFlow> sequenceflow = activity.getOutgoing();
				for (SequenceFlow a : sequenceflow){
					if (a.getTargetRef() instanceof Activity)
						target = (Activity)a.getTargetRef();
						if (target.isVarPoint()){
							if (target.getVarPointType().equals("##OR")) {
	//								permite selecionar a variante, ao selecionar a variante
	//								percorrer outras variantes
	//								verificar se estão selecionadas
	//								se não tiver nenhuma selecionadas, selecione a variante
	//								se tiver alguma selecionada, selecione a variante e defina a prioridade
	//								verificar para cada variante se existe prioridade igual
	//								se existir prioridade igual
	//								definir gateway
									activity.setCheck(true);
							}
							if (target.getVarPointType().equals("##XOR")) {
	//								se varpoint está resolvida
	//								dialogo "Uma variant desse varpoint já foi selecionada";
									if (target.isSolved()){
										MessageDialog.openWarning(null, "Warning", "A variant was already selected!");
									}
	//								se varpoint não está resolvida
	//								permite selecionar a variante;
	//								varpoint está resolvida
									else{
										activity.setCheck(true);
										target.setSolved(true);
									}
							}
						}
				//Para cada sequenceflow de saída verificar se o target é uma activity do tipo varpoint
				//Se do tipo varpoint
					//getIncoming()
	
				//Para cada sequenceflow de entrada verificar se o source é uma activity do tipo variant
				//se do tipo variant
				}
			}
		}
		if (bo instanceof ItemAwareElement){
			ItemAwareElement element = (ItemAwareElement)bo;
			if (element.isVarPoint()){
				element.setCheck(true);
				element.setSolved(true);
			}else{
				List<DataOutputAssociation> DOA = element.getDataOutputAssociations();
				for (DataOutputAssociation d: DOA){
					if (((ItemAwareElement)d.getTargetRef()).getVarPointType().equals("##XOR")){
						if (((ItemAwareElement)d.getTargetRef()).isSolved()){
							MessageDialog.openWarning(null, "Warning", "A variant was already selected!");
						}
						else{
							element.setCheck(true);
							((ItemAwareElement)d.getTargetRef()).setSolved(true);
						}
					}
					if (((ItemAwareElement)d.getTargetRef()).getVarPointType().equals("##OR")){
						element.setCheck(true);
					}
					break;
				}
			}
		}
	}
}
