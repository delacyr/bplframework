/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 *
 */
package org.eclipse.bpmn2.di;

import java.util.List;

import org.eclipse.dd.di.Diagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNDiagram#getPlane <em>Plane</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNDiagram#getLabelStyle <em>Label Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNDiagram#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNDiagram#getPhase <em>Phase</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNDiagram#getFeatureModel <em>Feature Model</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNDiagram#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNDiagram()
 * @model extendedMetaData="name='BPMNDiagram' kind='elementOnly'"
 * @generated
 */
public interface BPMNDiagram extends Diagram {
    /**
     * Returns the value of the '<em><b>Plane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Plane</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Plane</em>' containment reference.
     * @see #setPlane(BPMNPlane)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNDiagram_Plane()
     * @model containment="true" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='BPMNPlane' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    BPMNPlane getPlane();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNDiagram#getPlane <em>Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Plane</em>' containment reference.
     * @see #getPlane()
     * @generated
     */
    void setPlane(BPMNPlane value);

    /**
     * Returns the value of the '<em><b>Label Style</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.di.BPMNLabelStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Style</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Style</em>' containment reference list.
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNDiagram_LabelStyle()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='BPMNLabelStyle' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    List<BPMNLabelStyle> getLabelStyle();

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(long)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNDiagram_Version()
     * @model
     * @generated
     */
    long getVersion();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNDiagram#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(long value);

    /**
     * Returns the value of the '<em><b>Phase</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Phase</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Phase</em>' attribute.
     * @see #setPhase(String)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNDiagram_Phase()
     * @model default=""
     * @generated
     */
    String getPhase();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNDiagram#getPhase <em>Phase</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Phase</em>' attribute.
     * @see #getPhase()
     * @generated
     */
    void setPhase(String value);

    /**
     * Returns the value of the '<em><b>Feature Model</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature Model</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Model</em>' attribute.
     * @see #setFeatureModel(String)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNDiagram_FeatureModel()
     * @model
     * @generated
     */
    String getFeatureModel();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNDiagram#getFeatureModel <em>Feature Model</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Model</em>' attribute.
     * @see #getFeatureModel()
     * @generated
     */
    void setFeatureModel(String value);

    /**
     * Returns the value of the '<em><b>Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Location</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Location</em>' attribute.
     * @see #setLocation(String)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNDiagram_Location()
     * @model
     * @generated
     */
    String getLocation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNDiagram#getLocation <em>Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Location</em>' attribute.
     * @see #getLocation()
     * @generated
     */
    void setLocation(String value);

} // BPMNDiagram
