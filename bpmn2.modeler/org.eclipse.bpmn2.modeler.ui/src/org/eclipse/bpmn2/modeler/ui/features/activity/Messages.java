package org.eclipse.bpmn2.modeler.ui.features.activity;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.bpmn2.modeler.ui.features.activity.messages"; //$NON-NLS-1$
	public static String AppendActivityFeature_Description;
	public static String AppendActivityFeature_Name;
	public static String MorphActivityFeature_Description;
	public static String MorphActivityFeature_Name;
	public static String AppendCheckedFeature_Description;
	public static String AppendCheckedFeature_Name;
	public static String AppendUncheckedFeature_Name;
	public static String AppendUncheckedFeature_Description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
