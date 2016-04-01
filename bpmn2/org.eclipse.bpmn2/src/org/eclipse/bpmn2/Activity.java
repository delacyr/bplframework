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
 */
package org.eclipse.bpmn2;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Activity#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getBoundaryEventRefs <em>Boundary Event Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getDataInputAssociations <em>Data Input Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getDataOutputAssociations <em>Data Output Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getResources <em>Resources</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getLoopCharacteristics <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getCompletionQuantity <em>Completion Quantity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getDefault <em>Default</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#isIsForCompensation <em>Is For Compensation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getStartQuantity <em>Start Quantity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getVrSpecification <em>Vr Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#isVarPoint <em>Var Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getVarPointType <em>Var Point Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#isVariant <em>Variant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getFeatureType <em>Feature Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#getFeatureId <em>Feature Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Activity#isCheck <em>Check</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getActivity()
 * @model extendedMetaData="name='tActivity' kind='elementOnly' abstract='true'"
 * @generated
 */
public interface Activity extends FlowNode {
    /**
     * Returns the value of the '<em><b>Io Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Io Specification</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Io Specification</em>' containment reference.
     * @see #setIoSpecification(InputOutputSpecification)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_IoSpecification()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='ioSpecification' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    InputOutputSpecification getIoSpecification();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getIoSpecification <em>Io Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Io Specification</em>' containment reference.
     * @see #getIoSpecification()
     * @generated
     */
    void setIoSpecification(InputOutputSpecification value);

    /**
     * Returns the value of the '<em><b>Boundary Event Refs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.BoundaryEvent}.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.BoundaryEvent#getAttachedToRef <em>Attached To Ref</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Boundary Event Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Boundary Event Refs</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_BoundaryEventRefs()
     * @see org.eclipse.bpmn2.BoundaryEvent#getAttachedToRef
     * @model opposite="attachedToRef" resolveProxies="false" transient="true" derived="true" ordered="false"
     * @generated
     */
    List<BoundaryEvent> getBoundaryEventRefs();

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Property}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_Properties()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='property' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Property> getProperties();

    /**
     * Returns the value of the '<em><b>Data Input Associations</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.DataInputAssociation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Input Associations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Input Associations</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_DataInputAssociations()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataInputAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataInputAssociation> getDataInputAssociations();

    /**
     * Returns the value of the '<em><b>Data Output Associations</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.DataOutputAssociation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Output Associations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Output Associations</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_DataOutputAssociations()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataOutputAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataOutputAssociation> getDataOutputAssociations();

    /**
     * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ResourceRole}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resources</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_Resources()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='resourceRole' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' group='http://www.omg.org/spec/BPMN/20100524/MODEL#resourceRole'"
     * @generated
     */
    List<ResourceRole> getResources();

    /**
     * Returns the value of the '<em><b>Loop Characteristics</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Loop Characteristics</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Characteristics</em>' containment reference.
     * @see #setLoopCharacteristics(LoopCharacteristics)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_LoopCharacteristics()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='loopCharacteristics' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' group='http://www.omg.org/spec/BPMN/20100524/MODEL#loopCharacteristics'"
     * @generated
     */
    LoopCharacteristics getLoopCharacteristics();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getLoopCharacteristics <em>Loop Characteristics</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Characteristics</em>' containment reference.
     * @see #getLoopCharacteristics()
     * @generated
     */
    void setLoopCharacteristics(LoopCharacteristics value);

    /**
     * Returns the value of the '<em><b>Completion Quantity</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Completion Quantity</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Completion Quantity</em>' attribute.
     * @see #setCompletionQuantity(int)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_CompletionQuantity()
     * @model default="1" ordered="false"
     *        extendedMetaData="kind='attribute' name='completionQuantity'"
     * @generated
     */
    int getCompletionQuantity();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getCompletionQuantity <em>Completion Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Completion Quantity</em>' attribute.
     * @see #getCompletionQuantity()
     * @generated
     */
    void setCompletionQuantity(int value);

    /**
     * Returns the value of the '<em><b>Default</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default</em>' reference.
     * @see #setDefault(SequenceFlow)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_Default()
     * @model resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='default'"
     * @generated
     */
    SequenceFlow getDefault();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getDefault <em>Default</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default</em>' reference.
     * @see #getDefault()
     * @generated
     */
    void setDefault(SequenceFlow value);

    /**
     * Returns the value of the '<em><b>Is For Compensation</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is For Compensation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is For Compensation</em>' attribute.
     * @see #setIsForCompensation(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_IsForCompensation()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='isForCompensation'"
     * @generated
     */
    boolean isIsForCompensation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#isIsForCompensation <em>Is For Compensation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is For Compensation</em>' attribute.
     * @see #isIsForCompensation()
     * @generated
     */
    void setIsForCompensation(boolean value);

    /**
     * Returns the value of the '<em><b>Start Quantity</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Quantity</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Start Quantity</em>' attribute.
     * @see #setStartQuantity(int)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_StartQuantity()
     * @model default="1" ordered="false"
     *        extendedMetaData="kind='attribute' name='startQuantity'"
     * @generated
     */
    int getStartQuantity();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getStartQuantity <em>Start Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Quantity</em>' attribute.
     * @see #getStartQuantity()
     * @generated
     */
    void setStartQuantity(int value);

    /**
     * Returns the value of the '<em><b>Vr Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Vr Specification</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vr Specification</em>' containment reference.
     * @see #setVrSpecification(VariabilitySpecification)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_VrSpecification()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='vrSpecification' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    VariabilitySpecification getVrSpecification();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getVrSpecification <em>Vr Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vr Specification</em>' containment reference.
     * @see #getVrSpecification()
     * @generated
     */
    void setVrSpecification(VariabilitySpecification value);

    /**
     * Returns the value of the '<em><b>Var Point</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var Point</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Var Point</em>' attribute.
     * @see #setVarPoint(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_VarPoint()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='varPoint'"
     * @generated
     */
    boolean isVarPoint();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#isVarPoint <em>Var Point</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Var Point</em>' attribute.
     * @see #isVarPoint()
     * @generated
     */
    void setVarPoint(boolean value);

    /**
     * Returns the value of the '<em><b>Var Point Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var Point Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Var Point Type</em>' attribute.
     * @see #setVarPointType(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_VarPointType()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='varPointType'"
     * @generated
     */
    String getVarPointType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getVarPointType <em>Var Point Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Var Point Type</em>' attribute.
     * @see #getVarPointType()
     * @generated
     */
    void setVarPointType(String value);

    /**
     * Returns the value of the '<em><b>Variant</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variant</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variant</em>' attribute.
     * @see #setVariant(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_Variant()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='variant'"
     * @generated
     */
    boolean isVariant();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#isVariant <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variant</em>' attribute.
     * @see #isVariant()
     * @generated
     */
    void setVariant(boolean value);

    /**
     * Returns the value of the '<em><b>Feature Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Type</em>' attribute.
     * @see #setFeatureType(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_FeatureType()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='featureType'"
     * @generated
     */
    String getFeatureType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getFeatureType <em>Feature Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Type</em>' attribute.
     * @see #getFeatureType()
     * @generated
     */
    void setFeatureType(String value);

    /**
     * Returns the value of the '<em><b>Feature Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Id</em>' attribute.
     * @see #setFeatureId(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_FeatureId()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='featureId'"
     * @generated
     */
    String getFeatureId();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#getFeatureId <em>Feature Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Id</em>' attribute.
     * @see #getFeatureId()
     * @generated
     */
    void setFeatureId(String value);

    /**
     * Returns the value of the '<em><b>Check</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Check</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Check</em>' attribute.
     * @see #setCheck(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getActivity_Check()
     * @model default="false" ordered="false"
     * @generated
     */
    boolean isCheck();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Activity#isCheck <em>Check</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Check</em>' attribute.
     * @see #isCheck()
     * @generated
     */
    void setCheck(boolean value);

} // Activity
