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
 * A representation of the model object '<em><b>Item Aware Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getItemSubjectRef <em>Item Subject Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getVrSpecification <em>Vr Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#isVarPoint <em>Var Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getVarPointType <em>Var Point Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#isVariant <em>Variant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getFeatureType <em>Feature Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getFeatureId <em>Feature Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getDataInputAssociations <em>Data Input Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#getDataOutputAssociations <em>Data Output Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#isCheck <em>Check</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ItemAwareElement#isSolved <em>Solved</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement()
 * @model
 * @generated
 */
public interface ItemAwareElement extends BaseElement {
    /**
     * Returns the value of the '<em><b>Data State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data State</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data State</em>' containment reference.
     * @see #setDataState(DataState)
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_DataState()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataState' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataState getDataState();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getDataState <em>Data State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data State</em>' containment reference.
     * @see #getDataState()
     * @generated
     */
    void setDataState(DataState value);

    /**
     * Returns the value of the '<em><b>Item Subject Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Item Subject Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Item Subject Ref</em>' reference.
     * @see #setItemSubjectRef(ItemDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_ItemSubjectRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='itemSubjectRef'"
     * @generated
     */
    ItemDefinition getItemSubjectRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getItemSubjectRef <em>Item Subject Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Item Subject Ref</em>' reference.
     * @see #getItemSubjectRef()
     * @generated
     */
    void setItemSubjectRef(ItemDefinition value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_VrSpecification()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='vrSpecification' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    VariabilitySpecification getVrSpecification();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getVrSpecification <em>Vr Specification</em>}' containment reference.
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_VarPoint()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='varPoint'"
     * @generated
     */
    boolean isVarPoint();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#isVarPoint <em>Var Point</em>}' attribute.
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_VarPointType()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='varPointType'"
     * @generated
     */
    String getVarPointType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getVarPointType <em>Var Point Type</em>}' attribute.
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_Variant()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='variant'"
     * @generated
     */
    boolean isVariant();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#isVariant <em>Variant</em>}' attribute.
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_FeatureType()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='featureType'"
     * @generated
     */
    String getFeatureType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getFeatureType <em>Feature Type</em>}' attribute.
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_FeatureId()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='featureId'"
     * @generated
     */
    String getFeatureId();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getFeatureId <em>Feature Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Id</em>' attribute.
     * @see #getFeatureId()
     * @generated
     */
    void setFeatureId(String value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_IoSpecification()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='ioSpecification' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    InputOutputSpecification getIoSpecification();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#getIoSpecification <em>Io Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Io Specification</em>' containment reference.
     * @see #getIoSpecification()
     * @generated
     */
    void setIoSpecification(InputOutputSpecification value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_DataInputAssociations()
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_DataOutputAssociations()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='dataOutputAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<DataOutputAssociation> getDataOutputAssociations();

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_Check()
     * @model default="false" ordered="false"
     * @generated
     */
    boolean isCheck();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#isCheck <em>Check</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Check</em>' attribute.
     * @see #isCheck()
     * @generated
     */
    void setCheck(boolean value);

    /**
     * Returns the value of the '<em><b>Solved</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Solved</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Solved</em>' attribute.
     * @see #setSolved(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getItemAwareElement_Solved()
     * @model default="false" ordered="false"
     * @generated
     */
    boolean isSolved();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ItemAwareElement#isSolved <em>Solved</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Solved</em>' attribute.
     * @see #isSolved()
     * @generated
     */
    void setSolved(boolean value);

} // ItemAwareElement
