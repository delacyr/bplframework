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
package org.eclipse.bpmn2.impl;

import java.util.Collection;
import java.util.List;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.DataState;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.VariabilitySpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Object Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getItemSubjectRef <em>Item Subject Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getVrSpecification <em>Vr Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#isVarPoint <em>Var Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getVarPointType <em>Var Point Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#isVariant <em>Variant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getVariantType <em>Variant Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getVariabilityType <em>Variability Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getFeatureId <em>Feature Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getDataInputAssociations <em>Data Input Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getDataOutputAssociations <em>Data Output Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getSequential <em>Sequential</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataObjectReferenceImpl#getDataObjectRef <em>Data Object Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataObjectReferenceImpl extends FlowElementImpl implements DataObjectReference {
    /**
     * The cached value of the '{@link #getDataState() <em>Data State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataState()
     * @generated
     * @ordered
     */
    protected DataState dataState;

    /**
     * The cached value of the '{@link #getItemSubjectRef() <em>Item Subject Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getItemSubjectRef()
     * @generated
     * @ordered
     */
    protected ItemDefinition itemSubjectRef;

    /**
     * The cached value of the '{@link #getVrSpecification() <em>Vr Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVrSpecification()
     * @generated
     * @ordered
     */
    protected VariabilitySpecification vrSpecification;

    /**
     * The default value of the '{@link #isVarPoint() <em>Var Point</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isVarPoint()
     * @generated
     * @ordered
     */
    protected static final boolean VAR_POINT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isVarPoint() <em>Var Point</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isVarPoint()
     * @generated
     * @ordered
     */
    protected boolean varPoint = VAR_POINT_EDEFAULT;

    /**
     * The default value of the '{@link #getVarPointType() <em>Var Point Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVarPointType()
     * @generated
     * @ordered
     */
    protected static final String VAR_POINT_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVarPointType() <em>Var Point Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVarPointType()
     * @generated
     * @ordered
     */
    protected String varPointType = VAR_POINT_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #isVariant() <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isVariant()
     * @generated
     * @ordered
     */
    protected static final boolean VARIANT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isVariant() <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isVariant()
     * @generated
     * @ordered
     */
    protected boolean variant = VARIANT_EDEFAULT;

    /**
     * The default value of the '{@link #getVariantType() <em>Variant Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariantType()
     * @generated
     * @ordered
     */
    protected static final String VARIANT_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVariantType() <em>Variant Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariantType()
     * @generated
     * @ordered
     */
    protected String variantType = VARIANT_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getVariabilityType() <em>Variability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariabilityType()
     * @generated
     * @ordered
     */
    protected static final String VARIABILITY_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVariabilityType() <em>Variability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariabilityType()
     * @generated
     * @ordered
     */
    protected String variabilityType = VARIABILITY_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getFeatureId() <em>Feature Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatureId()
     * @generated
     * @ordered
     */
    protected static final String FEATURE_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFeatureId() <em>Feature Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatureId()
     * @generated
     * @ordered
     */
    protected String featureId = FEATURE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getIoSpecification() <em>Io Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIoSpecification()
     * @generated
     * @ordered
     */
    protected InputOutputSpecification ioSpecification;

    /**
     * The cached value of the '{@link #getDataInputAssociations() <em>Data Input Associations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataInputAssociations()
     * @generated
     * @ordered
     */
    protected EList<DataInputAssociation> dataInputAssociations;

    /**
     * The cached value of the '{@link #getDataOutputAssociations() <em>Data Output Associations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataOutputAssociations()
     * @generated
     * @ordered
     */
    protected EList<DataOutputAssociation> dataOutputAssociations;

    /**
     * The default value of the '{@link #getSequential() <em>Sequential</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSequential()
     * @generated
     * @ordered
     */
    protected static final String SEQUENTIAL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSequential() <em>Sequential</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSequential()
     * @generated
     * @ordered
     */
    protected String sequential = SEQUENTIAL_EDEFAULT;

    /**
     * The cached value of the '{@link #getDataObjectRef() <em>Data Object Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataObjectRef()
     * @generated
     * @ordered
     */
    protected DataObject dataObjectRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataObjectReferenceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.DATA_OBJECT_REFERENCE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataState getDataState() {
        return dataState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataState(DataState newDataState, NotificationChain msgs) {
        DataState oldDataState = dataState;
        dataState = newDataState;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE, oldDataState, newDataState);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataState(DataState newDataState) {
        if (newDataState != dataState) {
            NotificationChain msgs = null;
            if (dataState != null)
                msgs = ((InternalEObject) dataState).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE, null, msgs);
            if (newDataState != null)
                msgs = ((InternalEObject) newDataState).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE, null, msgs);
            msgs = basicSetDataState(newDataState, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE, newDataState, newDataState));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ItemDefinition getItemSubjectRef() {
        if (itemSubjectRef != null && itemSubjectRef.eIsProxy()) {
            InternalEObject oldItemSubjectRef = (InternalEObject) itemSubjectRef;
            itemSubjectRef = (ItemDefinition) eResolveProxy(oldItemSubjectRef);
            if (itemSubjectRef != oldItemSubjectRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.DATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF,
                            oldItemSubjectRef, itemSubjectRef));
            }
        }
        return itemSubjectRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ItemDefinition basicGetItemSubjectRef() {
        return itemSubjectRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setItemSubjectRef(ItemDefinition newItemSubjectRef) {
        ItemDefinition oldItemSubjectRef = itemSubjectRef;
        itemSubjectRef = newItemSubjectRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF, oldItemSubjectRef,
                    itemSubjectRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariabilitySpecification getVrSpecification() {
        return vrSpecification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetVrSpecification(VariabilitySpecification newVrSpecification,
            NotificationChain msgs) {
        VariabilitySpecification oldVrSpecification = vrSpecification;
        vrSpecification = newVrSpecification;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION, oldVrSpecification,
                    newVrSpecification);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVrSpecification(VariabilitySpecification newVrSpecification) {
        if (newVrSpecification != vrSpecification) {
            NotificationChain msgs = null;
            if (vrSpecification != null)
                msgs = ((InternalEObject) vrSpecification).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE
                                - Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION, null, msgs);
            if (newVrSpecification != null)
                msgs = ((InternalEObject) newVrSpecification).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE
                                - Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION, null, msgs);
            msgs = basicSetVrSpecification(newVrSpecification, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION, newVrSpecification,
                    newVrSpecification));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isVarPoint() {
        return varPoint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVarPoint(boolean newVarPoint) {
        boolean oldVarPoint = varPoint;
        varPoint = newVarPoint;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT, oldVarPoint, varPoint));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVarPointType() {
        return varPointType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVarPointType(String newVarPointType) {
        String oldVarPointType = varPointType;
        varPointType = newVarPointType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT_TYPE, oldVarPointType,
                    varPointType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isVariant() {
        return variant;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVariant(boolean newVariant) {
        boolean oldVariant = variant;
        variant = newVariant;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT, oldVariant, variant));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVariantType() {
        return variantType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVariantType(String newVariantType) {
        String oldVariantType = variantType;
        variantType = newVariantType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT_TYPE, oldVariantType, variantType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVariabilityType() {
        return variabilityType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVariabilityType(String newVariabilityType) {
        String oldVariabilityType = variabilityType;
        variabilityType = newVariabilityType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__VARIABILITY_TYPE, oldVariabilityType,
                    variabilityType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFeatureId() {
        return featureId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeatureId(String newFeatureId) {
        String oldFeatureId = featureId;
        featureId = newFeatureId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__FEATURE_ID, oldFeatureId, featureId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputOutputSpecification getIoSpecification() {
        return ioSpecification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIoSpecification(InputOutputSpecification newIoSpecification,
            NotificationChain msgs) {
        InputOutputSpecification oldIoSpecification = ioSpecification;
        ioSpecification = newIoSpecification;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION, oldIoSpecification,
                    newIoSpecification);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIoSpecification(InputOutputSpecification newIoSpecification) {
        if (newIoSpecification != ioSpecification) {
            NotificationChain msgs = null;
            if (ioSpecification != null)
                msgs = ((InternalEObject) ioSpecification).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE
                                - Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION, null, msgs);
            if (newIoSpecification != null)
                msgs = ((InternalEObject) newIoSpecification).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE
                                - Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION, null, msgs);
            msgs = basicSetIoSpecification(newIoSpecification, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION, newIoSpecification,
                    newIoSpecification));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<DataInputAssociation> getDataInputAssociations() {
        if (dataInputAssociations == null) {
            dataInputAssociations = new EObjectContainmentEList<DataInputAssociation>(
                    DataInputAssociation.class, this,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_INPUT_ASSOCIATIONS);
        }
        return dataInputAssociations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<DataOutputAssociation> getDataOutputAssociations() {
        if (dataOutputAssociations == null) {
            dataOutputAssociations = new EObjectContainmentEList<DataOutputAssociation>(
                    DataOutputAssociation.class, this,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OUTPUT_ASSOCIATIONS);
        }
        return dataOutputAssociations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSequential() {
        return sequential;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSequential(String newSequential) {
        String oldSequential = sequential;
        sequential = newSequential;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__SEQUENTIAL, oldSequential, sequential));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataObject getDataObjectRef() {
        return dataObjectRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataObjectRef(DataObject newDataObjectRef) {
        DataObject oldDataObjectRef = dataObjectRef;
        dataObjectRef = newDataObjectRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OBJECT_REF, oldDataObjectRef,
                    dataObjectRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE:
            return basicSetDataState(null, msgs);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION:
            return basicSetVrSpecification(null, msgs);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION:
            return basicSetIoSpecification(null, msgs);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_INPUT_ASSOCIATIONS:
            return ((InternalEList<?>) getDataInputAssociations()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OUTPUT_ASSOCIATIONS:
            return ((InternalEList<?>) getDataOutputAssociations()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE:
            return getDataState();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF:
            if (resolve)
                return getItemSubjectRef();
            return basicGetItemSubjectRef();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION:
            return getVrSpecification();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT:
            return isVarPoint();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT_TYPE:
            return getVarPointType();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT:
            return isVariant();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT_TYPE:
            return getVariantType();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIABILITY_TYPE:
            return getVariabilityType();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__FEATURE_ID:
            return getFeatureId();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__TYPE:
            return getType();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION:
            return getIoSpecification();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_INPUT_ASSOCIATIONS:
            return getDataInputAssociations();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OUTPUT_ASSOCIATIONS:
            return getDataOutputAssociations();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__SEQUENTIAL:
            return getSequential();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OBJECT_REF:
            return getDataObjectRef();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE:
            setDataState((DataState) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF:
            setItemSubjectRef((ItemDefinition) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION:
            setVrSpecification((VariabilitySpecification) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT:
            setVarPoint((Boolean) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT_TYPE:
            setVarPointType((String) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT:
            setVariant((Boolean) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT_TYPE:
            setVariantType((String) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIABILITY_TYPE:
            setVariabilityType((String) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__FEATURE_ID:
            setFeatureId((String) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__TYPE:
            setType((String) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION:
            setIoSpecification((InputOutputSpecification) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_INPUT_ASSOCIATIONS:
            getDataInputAssociations().clear();
            getDataInputAssociations()
                    .addAll((Collection<? extends DataInputAssociation>) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OUTPUT_ASSOCIATIONS:
            getDataOutputAssociations().clear();
            getDataOutputAssociations().addAll(
                    (Collection<? extends DataOutputAssociation>) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__SEQUENTIAL:
            setSequential((String) newValue);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OBJECT_REF:
            setDataObjectRef((DataObject) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE:
            setDataState((DataState) null);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF:
            setItemSubjectRef((ItemDefinition) null);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION:
            setVrSpecification((VariabilitySpecification) null);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT:
            setVarPoint(VAR_POINT_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT_TYPE:
            setVarPointType(VAR_POINT_TYPE_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT:
            setVariant(VARIANT_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT_TYPE:
            setVariantType(VARIANT_TYPE_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIABILITY_TYPE:
            setVariabilityType(VARIABILITY_TYPE_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__FEATURE_ID:
            setFeatureId(FEATURE_ID_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__TYPE:
            setType(TYPE_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION:
            setIoSpecification((InputOutputSpecification) null);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_INPUT_ASSOCIATIONS:
            getDataInputAssociations().clear();
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OUTPUT_ASSOCIATIONS:
            getDataOutputAssociations().clear();
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__SEQUENTIAL:
            setSequential(SEQUENTIAL_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OBJECT_REF:
            setDataObjectRef((DataObject) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE:
            return dataState != null;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF:
            return itemSubjectRef != null;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION:
            return vrSpecification != null;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT:
            return varPoint != VAR_POINT_EDEFAULT;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT_TYPE:
            return VAR_POINT_TYPE_EDEFAULT == null ? varPointType != null
                    : !VAR_POINT_TYPE_EDEFAULT.equals(varPointType);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT:
            return variant != VARIANT_EDEFAULT;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT_TYPE:
            return VARIANT_TYPE_EDEFAULT == null ? variantType != null : !VARIANT_TYPE_EDEFAULT
                    .equals(variantType);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIABILITY_TYPE:
            return VARIABILITY_TYPE_EDEFAULT == null ? variabilityType != null
                    : !VARIABILITY_TYPE_EDEFAULT.equals(variabilityType);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__FEATURE_ID:
            return FEATURE_ID_EDEFAULT == null ? featureId != null : !FEATURE_ID_EDEFAULT
                    .equals(featureId);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__TYPE:
            return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION:
            return ioSpecification != null;
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_INPUT_ASSOCIATIONS:
            return dataInputAssociations != null && !dataInputAssociations.isEmpty();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OUTPUT_ASSOCIATIONS:
            return dataOutputAssociations != null && !dataOutputAssociations.isEmpty();
        case Bpmn2Package.DATA_OBJECT_REFERENCE__SEQUENTIAL:
            return SEQUENTIAL_EDEFAULT == null ? sequential != null : !SEQUENTIAL_EDEFAULT
                    .equals(sequential);
        case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OBJECT_REF:
            return dataObjectRef != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == ItemAwareElement.class) {
            switch (derivedFeatureID) {
            case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__VR_SPECIFICATION;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__VAR_POINT;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT_TYPE:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__VAR_POINT_TYPE;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__VARIANT;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT_TYPE:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__VARIANT_TYPE;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__VARIABILITY_TYPE:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__VARIABILITY_TYPE;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__FEATURE_ID:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__FEATURE_ID;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__TYPE:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__TYPE;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__IO_SPECIFICATION;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_INPUT_ASSOCIATIONS:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_INPUT_ASSOCIATIONS;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OUTPUT_ASSOCIATIONS:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_OUTPUT_ASSOCIATIONS;
            case Bpmn2Package.DATA_OBJECT_REFERENCE__SEQUENTIAL:
                return Bpmn2Package.ITEM_AWARE_ELEMENT__SEQUENTIAL;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == ItemAwareElement.class) {
            switch (baseFeatureID) {
            case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_STATE;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__VR_SPECIFICATION:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__VR_SPECIFICATION;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__VAR_POINT:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__VAR_POINT_TYPE:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__VAR_POINT_TYPE;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__VARIANT:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__VARIANT_TYPE:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__VARIANT_TYPE;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__VARIABILITY_TYPE:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__VARIABILITY_TYPE;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__FEATURE_ID:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__FEATURE_ID;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__TYPE:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__TYPE;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__IO_SPECIFICATION:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__IO_SPECIFICATION;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_INPUT_ASSOCIATIONS:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_INPUT_ASSOCIATIONS;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_OUTPUT_ASSOCIATIONS:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__DATA_OUTPUT_ASSOCIATIONS;
            case Bpmn2Package.ITEM_AWARE_ELEMENT__SEQUENTIAL:
                return Bpmn2Package.DATA_OBJECT_REFERENCE__SEQUENTIAL;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (varPoint: ");
        result.append(varPoint);
        result.append(", varPointType: ");
        result.append(varPointType);
        result.append(", variant: ");
        result.append(variant);
        result.append(", variantType: ");
        result.append(variantType);
        result.append(", variabilityType: ");
        result.append(variabilityType);
        result.append(", featureId: ");
        result.append(featureId);
        result.append(", type: ");
        result.append(type);
        result.append(", sequential: ");
        result.append(sequential);
        result.append(')');
        return result.toString();
    }

} //DataObjectReferenceImpl
