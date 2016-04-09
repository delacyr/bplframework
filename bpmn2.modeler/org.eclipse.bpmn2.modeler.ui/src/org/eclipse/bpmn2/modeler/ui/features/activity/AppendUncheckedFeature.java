package org.eclipse.bpmn2.modeler.ui.features.activity;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.dialogs.MessageDialog;

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
			}
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
			Activity activity = (Activity)getBusinessObjectForPictogramElement(pe);
			checkVariabilityType(pe);

//			Setting check attribute to true
//			activity.setCheck(true);
//			IReason ireason = setFillColor(pe);
//			System.out.print(ireason);
		}


	}

	private IReason setFillColor(PictogramElement pe) {
		UpdateContext updateContext = new UpdateContext(pe);

		Activity variant = (Activity)getBusinessObjectForPictogramElement(pe);
		if (variant!=null) {
			ShapeStyle ss = new ShapeStyle();
			if (variant.isCheck()) {
				ss.setDefaultColors(IColorConstant.LIGHT_GREEN);
				ss.setTextColor(IColorConstant.BLUE);
			}
			StyleUtil.applyStyle(pe.getGraphicsAlgorithm(), variant, ss);
		}
		return getFeatureProvider().updateIfPossible(updateContext);
	}


	public void checkVariabilityType(PictogramElement pe){
		Object bo = getBusinessObjectForPictogramElement(pe);

		if (bo instanceof Activity){
			Activity activity = (Activity)bo;
			Activity target = null;
			List<SequenceFlow> sequenceflow = activity.getOutgoing();
			for (SequenceFlow a : sequenceflow){
				if (a.getTargetRef() instanceof Activity)
					target = (Activity)a.getTargetRef();
					if (target.isVarPoint())
						switch (target.getVarPointType()) {
							case "AND":

							case "OR":
//								permite selecionar a variante, ao selecionar a variante
//								percorrer outras variantes
//								verificar se estão selecionadas
//								se não tiver nenhuma selecionadas, selecione a variante
//								se tiver alguma selecionada, selecione a variante e defina a prioridade
//								verificar para cada variante se existe prioridade igual
//								se existir prioridade igual
//								definir gateway



							case "##XOR":
//								se varpoint está resolvida
//								dialogo "Uma variant desse varpoint já foi selecionada";
								if (target.isSolved()){
									MessageDialog.openWarning(null, "Warning", "A variant was already selected!");
									System.out.print("A variant was already selected!\n");
								}
//								se varpoint não está resolvida
//								permite selecionar a variante;
//								varpoint está resolvida
								else{
									activity.setCheck(true);
									target.setSolved(true);
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
}
