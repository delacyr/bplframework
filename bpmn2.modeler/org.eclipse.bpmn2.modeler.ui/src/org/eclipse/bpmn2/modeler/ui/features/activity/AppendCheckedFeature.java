package org.eclipse.bpmn2.modeler.ui.features.activity;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class AppendCheckedFeature extends AbstractCustomFeature{

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

			}
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

			}
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



							case "##XOR":
//								se varpoint está resolvida
//								desmarcar variante e varpoint
								if (target.isSolved()){
									activity.setCheck(false);
									target.setSolved(false);
								}
								break;

							default:
								break;
							} ;

			}
			//Para cada sequenceflow de saída verificar se o target é uma activity do tipo varpoint
			//Se do tipo varpoint
				//getIncoming()

			//Para cada sequenceflow de entrada verificar se o source é uma activity do tipo variant
			//se do tipo variant
		}
	}
}
