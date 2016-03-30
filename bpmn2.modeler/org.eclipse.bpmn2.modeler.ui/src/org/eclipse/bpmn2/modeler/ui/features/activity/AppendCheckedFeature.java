package org.eclipse.bpmn2.modeler.ui.features.activity;

import org.eclipse.bpmn2.Activity;
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
					if (AbstractActivityFeatureContainer.check == false)
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
		AbstractActivityFeatureContainer.check = false;

	}
}
