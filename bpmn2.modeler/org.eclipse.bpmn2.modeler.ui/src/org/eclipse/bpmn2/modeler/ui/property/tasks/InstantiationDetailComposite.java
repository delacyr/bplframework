/******************************************************************************* 
 * Copyright (c) 2011, 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Marcelo Figueiredo Terenciani
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.property.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.BooleanObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.property.editors.FeatureIdObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.GatewayObjectEditor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.xml.XmlFeatureModelReader;



/**
 * This class renders the property sheet tab for I/O Parameters defined in
 * Activities, CallableElements and ThrowEvents.
 * 
 * TODO: handle ThrowEvent parameters
 */
public class InstantiationDetailComposite extends AbstractDetailComposite {

	protected GatewayObjectEditor gateway;
	protected Text seq;
//	protected ObjectEditor varPoint;
//	protected ObjectEditor variant;
//	protected FeatureIdObjectEditor featureIdEditor; 
//	protected Button noneButton;
//	protected Button mandatoryButton;
//	protected Button optionalButton;
//	public FeatureModel featureModel;
//	Composite buttonComposite;
	Label label;

	public InstantiationDetailComposite(Composite parent, int style) {
		
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public InstantiationDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider == null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] { "gateway", //BPMN* //$NON-NLS-1$
												 "seq" //BPMN*
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
//		redrawParent();
	}
	@Override
	public void cleanBindings() {
		super.cleanBindings();
		gateway = null;
		seq = null;
	}

	public void createBindings(EObject be) {

		
		createWidgetsSequence(be);
		createWidgetsGateway(be);
		
//		if (pass){
//			gateway.setVisible(false);
//		}
		
	}
	
	private void createWidgetsSequence(EObject be) {
//		EStructuralFeature feature = getFeature(be, "seq");
//		gateway = new GatewayObjectEditor(this, be, feature){
//			@Override
//			public void setVisible(boolean visible) {
//				if (!visible)
//					setValue(null);
//				super.setVisible(visible);
//			}
//		};
//		seq.createControl(getAttributesParent(), "Sequence");
	}
	

	private void createWidgetsGateway(EObject be) {
		EStructuralFeature feature = getFeature(be, "gateway");
		gateway = new GatewayObjectEditor(this, be, feature){
			@Override
			public void setVisible(boolean visible) {
				if (!visible)
					setValue(null);
				super.setVisible(visible);
			}
		};
		gateway.createControl(getAttributesParent(), "Gateway");
	}
	
	

//	private void redrawParent() {
//		updateName();
//		gateway.setVisible((Boolean)varPoint.getValue());
//		if (!(Boolean)variant.getValue()){
//			featureIdEditor.setVisible((Boolean)varPoint.getValue());
//			buttonComposite.setVisible((Boolean)varPoint.getValue());
//			label.setVisible((Boolean)varPoint.getValue());
//		}
//		if (!(Boolean)varPoint.getValue()){
//			featureIdEditor.setVisible((Boolean)variant.getValue());
//			buttonComposite.setVisible((Boolean)variant.getValue());
//			label.setVisible((Boolean)variant.getValue());
//		}
//		// this DetailComposite should be sitting in a SashForm created
//		// by a ListComposite. layout this thing first
//		getAttributesParent().layout();
//		layout();
//		// and then search for the DetailComposite that contains the list
//		Composite parent = getParent();
//		while (parent != null) {
//			parent = parent.getParent();
//			if (parent instanceof AbstractDetailComposite) {
//				parent.layout();
//				parent.getParent().layout();
//				break;
//			}
//		}
//	}
	
//	public void updateName(){
//		
//		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
//		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
//			@Override
//			protected void doExecute() {
//				String str;
//				
//				EStructuralFeature name = businessObject.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
//				EStructuralFeature feature = businessObject.eClass().getEStructuralFeature("featureType");
//				EStructuralFeature featureId = businessObject.eClass().getEStructuralFeature("featureId");
//				if (feature!=null) {
//					str = (String)businessObject.eGet(feature);
//					str = getName((String)businessObject.eGet(name), (String)businessObject.eGet(feature), (String)businessObject.eGet(featureId));
//					if (!str.equals((String)businessObject.eGet(name)))
//						businessObject.eSet(name, str);
//				}
//				
//				
//			}
//		});
//	}
//	public String getName(String name, String featureType, String featureId){
//		String str;
//		boolean aux=false;
//		
//		int indice = name.indexOf("<<")-1;
//		str=null;
//		if (indice > 0)
//			str = name.substring(0, indice);
//		else
//			str=name;
//		
//		if ((Boolean)varPoint.getValue()){
//			str=str+" <<varpoint>>";
//			aux = true;
//		}
//		else if ((Boolean)variant.getValue()){
//			str=str+" <<variant>>";
//			aux = true;
//		}
//		if (featureType != null){
//			if (featureType.equals("##mandatory"))
//				str=str + " <<mandatory>>";
//			if (featureType.equals("##optional"))
//				str=str + " <<optional>>";
//		}
//		if (aux && featureId != null){
//			str = str + " {feature = "+featureId+"}";
//		}
//		return str;
//		
//	}
}