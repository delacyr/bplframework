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
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.features.activity.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.dialogs.MessageDialog;

public class AppendCheckedFeature extends AbstractCustomFeature{

	public static IColorConstant DEFAULT_COLOR = new ColorConstant(212, 231, 248);
	
	public AppendCheckedFeature(IFeatureProvider fp) {
		super(fp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return Messages.AppendCheckedFeature_Name;
	}

	@Override
	public String getDescription() {
		return Messages.AppendCheckedFeature_Description;
	}

	@Override
	public String getImageId() {
		return ImageProvider.IMG_16_CHECKED;
	}

	@Override
	public boolean isAvailable(IContext context) {
		return true;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		
		/*Feature exclusiva da instanciação*/
		if (BPMN2Editor.getActiveEditor().getBpmnDiagram().getPhase().equals("EPN")){
//		IFile file = editor.getModelFile();
//		if (file.getParent().getName().equals("Instantiating")){

			PictogramElement[] pes = context.getPictogramElements();
			if (pes != null && pes.length == 1) {
				PictogramElement pe = pes[0];
				Object bo = getBusinessObjectForPictogramElement(pe);
				if (bo instanceof Activity) {
					Activity activity = (Activity)bo;
					if (activity.isVariant()){
						if (!activity.isCheck())
							return false;
						else
							return true;
					}
					if (activity.isVarPoint()){
						if (!hasVariant(bo) && activity.isCheck()){
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
						if (!hasVariant(bo) && element.isCheck()) //se nao tem variantes e está selecionada
							return true;
						else
							return false;
					}
					else{
						if (element.isVariant() && element.isCheck()) //se é variante e está selecionada
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
			
//			List<DataInputAssociation> DIA = element.getDataInputAssociations();
//			for (DataInputAssociation d: DIA){
//				if (d.getTargetRef().isVariant()){
//					return true;
//				}
//			}
//
//			List<DataOutputAssociation> DOA = element.getDataOutputAssociations();
//			for (DataOutputAssociation d: DOA){
//				if (d.getTargetRef().isVariant()){
//					return true;
//				}
//			}
		}
		
		return false;
	}
	
	@Override
	public void execute(ICustomContext context) {
		// TODO Auto-generated method stub
		//Variant Deselect
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
			if (variant!=null && !variant.isCheck()) {
			ShapeStyle ss = new ShapeStyle();
			ss.setDefaultColors(DEFAULT_COLOR);
			StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
			}
		}
		if (bo instanceof ItemAwareElement){
			ItemAwareElement element = (ItemAwareElement)bo;
			if (element!=null && !element.isCheck()){
				ShapeStyle ss = new ShapeStyle();
				ss.setDefaultColors(DEFAULT_COLOR);
				StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), baseElement, ss);
			}
		}
	}
	
	public void checkVariabilityType(PictogramElement pe){
		Object bo = getBusinessObjectForPictogramElement(pe);

		if (bo instanceof Activity){
			Activity activity = (Activity)bo;
			Activity target = null;
			
			if (activity.isVarPoint()){
				activity.setCheck(false);
				activity.setSolved(false);
			}else{
				List<SequenceFlow> sequenceflow = activity.getOutgoing();
				for (SequenceFlow a : sequenceflow){
					if (a.getTargetRef() instanceof Activity)
						target = (Activity)a.getTargetRef();
					if (target.isVarPoint()){
						if (target.getVarPointType().equals("##OR")) {
								activity.setCheck(false);
						}
						if (target.getVarPointType().equals("##XOR")) {
	//						se varpoint está resolvida
	//						desmarcar variante e varpoint
							if (target.isSolved()){
								activity.setCheck(false);
								target.setSolved(false);
							}
						}
					}			
				}
			}
			//Para cada sequenceflow de saída verificar se o target é uma activity do tipo varpoint
			//Se do tipo varpoint
				//getIncoming()

			//Para cada sequenceflow de entrada verificar se o source é uma activity do tipo variant
			//se do tipo variant
		}
		if (bo instanceof ItemAwareElement){
			ItemAwareElement element = (ItemAwareElement)bo;
			if (element.isVarPoint()){
				element.setCheck(false);
				element.setSolved(false);
			}else{
				List<DataOutputAssociation> DOA = element.getDataOutputAssociations();
				for (DataOutputAssociation d: DOA){
					if (d.getTargetRef().getVarPointType().equals("##XOR")){
						element.setCheck(false);
						d.getTargetRef().setSolved(false);
					}
					if (d.getTargetRef().getVarPointType().equals("##OR")){
						element.setCheck(false);
					}
					break;
				}
			}
		}
	}
}
