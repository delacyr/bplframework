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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vr Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.VrSpecification#isVarPoint <em>Var Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.VrSpecification#getVarPointType <em>Var Point Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.VrSpecification#isVariant <em>Variant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.VrSpecification#getVariantType <em>Variant Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.VrSpecification#getVariabilityType <em>Variability Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.VrSpecification#getSequencial <em>Sequencial</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.VrSpecification#getFeatureId <em>Feature Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getVrSpecification()
 * @model extendedMetaData="name='tVrSpecification' kind='elementOnly'"
 * @generated
 */
public interface VrSpecification extends BaseElement {

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getVrSpecification_VarPoint()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='varPoint'"
     * @generated
     */
    boolean isVarPoint();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.VrSpecification#isVarPoint <em>Var Point</em>}' attribute.
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getVrSpecification_VarPointType()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='varPointType'"
     * @generated
     */
    String getVarPointType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.VrSpecification#getVarPointType <em>Var Point Type</em>}' attribute.
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
     * @see org.eclipse.bpmn2.Bpmn2Package#getVrSpecification_Variant()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='variant'"
     * @generated
     */
    boolean isVariant();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.VrSpecification#isVariant <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variant</em>' attribute.
     * @see #isVariant()
     * @generated
     */
    void setVariant(boolean value);

    /**
     * Returns the value of the '<em><b>Variant Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variant Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variant Type</em>' attribute.
     * @see #setVariantType(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getVrSpecification_VariantType()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='variantType'"
     * @generated
     */
    String getVariantType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.VrSpecification#getVariantType <em>Variant Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variant Type</em>' attribute.
     * @see #getVariantType()
     * @generated
     */
    void setVariantType(String value);

    /**
     * Returns the value of the '<em><b>Variability Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variability Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variability Type</em>' attribute.
     * @see #setVariabilityType(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getVrSpecification_VariabilityType()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='variabilityType'"
     * @generated
     */
    String getVariabilityType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.VrSpecification#getVariabilityType <em>Variability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variability Type</em>' attribute.
     * @see #getVariabilityType()
     * @generated
     */
    void setVariabilityType(String value);

    /**
     * Returns the value of the '<em><b>Sequencial</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sequencial</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sequencial</em>' attribute.
     * @see #setSequencial(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getVrSpecification_Sequencial()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='sequencial'"
     * @generated
     */
    String getSequencial();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.VrSpecification#getSequencial <em>Sequencial</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sequencial</em>' attribute.
     * @see #getSequencial()
     * @generated
     */
    void setSequencial(String value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getVrSpecification_FeatureId()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='featureId'"
     * @generated
     */
    String getFeatureId();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.VrSpecification#getFeatureId <em>Feature Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Id</em>' attribute.
     * @see #getFeatureId()
     * @generated
     */
    void setFeatureId(String value);
} // VrSpecification
