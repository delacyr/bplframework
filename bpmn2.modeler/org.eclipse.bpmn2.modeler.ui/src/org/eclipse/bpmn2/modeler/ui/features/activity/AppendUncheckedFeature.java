package org.eclipse.bpmn2.modeler.ui.features.activity;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.util.IColorConstant;

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
//					if (AbstractActivityFeatureContainer.check == false)
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
			Object bo = getBusinessObjectForPictogramElement(pe);
			Activity activity = (Activity)bo;
			activity.setCheck(true);		
			}
		
		
//		AbstractActivityFeatureContainer.check = true;

		PictogramElement pe[] = context.getPictogramElements();
		GraphicsAlgorithm ga = pe[0].getGraphicsAlgorithm();
		ga.setBackground(manageColor(pe[0],IColorConstant.GREEN));
		ga.setForeground(manageColor(pe[0],IColorConstant.GREEN));
		Color background = ga.getBackground();
		
		
//		System.out.println("Cor: ");
//		System.out.print(background);
//		Color newBg = manageColor(background.getGreen(), background.getBlue(), background.getRed());
//		ga.setBackground(newBg);

	}
	
	private static Color manageColor(PictogramElement element, IColorConstant colorConstant) {
		IPeService peService = Graphiti.getPeService();
		Diagram diagram = peService.getDiagramForPictogramElement(element);
		return Graphiti.getGaService().manageColor(diagram, colorConstant);
	}

}
