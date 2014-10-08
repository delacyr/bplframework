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

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.VariabilitySpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getBoundaryEventRefs <em>Boundary Event Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getDataInputAssociations <em>Data Input Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getDataOutputAssociations <em>Data Output Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getLoopCharacteristics <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getCompletionQuantity <em>Completion Quantity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#isIsForCompensation <em>Is For Compensation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getStartQuantity <em>Start Quantity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getVrSpecification <em>Vr Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#isVarPoint <em>Var Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getVarPointType <em>Var Point Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#isVariant <em>Variant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getVariantType <em>Variant Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getVariabilityType <em>Variability Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getSequential <em>Sequential</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getFeatureId <em>Feature Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ActivityImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityImpl extends FlowNodeImpl implements Activity {
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
     * The cached value of the '{@link #getBoundaryEventRefs() <em>Boundary Event Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBoundaryEventRefs()
     * @generated
     * @ordered
     */
    protected EList<BoundaryEvent> boundaryEventRefs;

    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    protected EList<Property> properties;

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
     * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResources()
     * @generated
     * @ordered
     */
    protected EList<ResourceRole> resources;

    /**
     * The cached value of the '{@link #getLoopCharacteristics() <em>Loop Characteristics</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopCharacteristics()
     * @generated
     * @ordered
     */
    protected LoopCharacteristics loopCharacteristics;

    /**
     * The default value of the '{@link #getCompletionQuantity() <em>Completion Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompletionQuantity()
     * @generated
     * @ordered
     */
    protected static final int COMPLETION_QUANTITY_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getCompletionQuantity() <em>Completion Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompletionQuantity()
     * @generated
     * @ordered
     */
    protected int completionQuantity = COMPLETION_QUANTITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getDefault() <em>Default</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefault()
     * @generated
     * @ordered
     */
    protected SequenceFlow default_;

    /**
     * The default value of the '{@link #isIsForCompensation() <em>Is For Compensation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsForCompensation()
     * @generated
     * @ordered
     */
    protected static final boolean IS_FOR_COMPENSATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsForCompensation() <em>Is For Compensation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsForCompensation()
     * @generated
     * @ordered
     */
    protected boolean isForCompensation = IS_FOR_COMPENSATION_EDEFAULT;

    /**
     * The default value of the '{@link #getStartQuantity() <em>Start Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartQuantity()
     * @generated
     * @ordered
     */
    protected static final int START_QUANTITY_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getStartQuantity() <em>Start Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartQuantity()
     * @generated
     * @ordered
     */
    protected int startQuantity = START_QUANTITY_EDEFAULT;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ActivityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.ACTIVITY;
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
                    Bpmn2Package.ACTIVITY__IO_SPECIFICATION, oldIoSpecification, newIoSpecification);
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
                        EOPPOSITE_FEATURE_BASE - Bpmn2Package.ACTIVITY__IO_SPECIFICATION, null,
                        msgs);
            if (newIoSpecification != null)
                msgs = ((InternalEObject) newIoSpecification).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - Bpmn2Package.ACTIVITY__IO_SPECIFICATION, null,
                        msgs);
            msgs = basicSetIoSpecification(newIoSpecification, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ACTIVITY__IO_SPECIFICATION, newIoSpecification, newIoSpecification));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<BoundaryEvent> getBoundaryEventRefs() {
        if (boundaryEventRefs == null) {
            boundaryEventRefs = new EObjectWithInverseEList<BoundaryEvent>(BoundaryEvent.class,
                    this, Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS,
                    Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF);
        }
        return boundaryEventRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<Property> getProperties() {
        if (properties == null) {
            properties = new EObjectContainmentEList<Property>(Property.class, this,
                    Bpmn2Package.ACTIVITY__PROPERTIES);
        }
        return properties;
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
                    Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS);
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
                    Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS);
        }
        return dataOutputAssociations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<ResourceRole> getResources() {
        if (resources == null) {
            resources = new EObjectContainmentEList<ResourceRole>(ResourceRole.class, this,
                    Bpmn2Package.ACTIVITY__RESOURCES);
        }
        return resources;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LoopCharacteristics getLoopCharacteristics() {
        return loopCharacteristics;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLoopCharacteristics(
            LoopCharacteristics newLoopCharacteristics, NotificationChain msgs) {
        LoopCharacteristics oldLoopCharacteristics = loopCharacteristics;
        loopCharacteristics = newLoopCharacteristics;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS, oldLoopCharacteristics,
                    newLoopCharacteristics);
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
    public void setLoopCharacteristics(LoopCharacteristics newLoopCharacteristics) {
        if (newLoopCharacteristics != loopCharacteristics) {
            NotificationChain msgs = null;
            if (loopCharacteristics != null)
                msgs = ((InternalEObject) loopCharacteristics).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS, null,
                        msgs);
            if (newLoopCharacteristics != null)
                msgs = ((InternalEObject) newLoopCharacteristics).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS, null,
                        msgs);
            msgs = basicSetLoopCharacteristics(newLoopCharacteristics, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS, newLoopCharacteristics,
                    newLoopCharacteristics));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getCompletionQuantity() {
        return completionQuantity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCompletionQuantity(int newCompletionQuantity) {
        int oldCompletionQuantity = completionQuantity;
        completionQuantity = newCompletionQuantity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY, oldCompletionQuantity,
                    completionQuantity));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SequenceFlow getDefault() {
        return default_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefault(SequenceFlow newDefault) {
        SequenceFlow oldDefault = default_;
        default_ = newDefault;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__DEFAULT,
                    oldDefault, default_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsForCompensation() {
        return isForCompensation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsForCompensation(boolean newIsForCompensation) {
        boolean oldIsForCompensation = isForCompensation;
        isForCompensation = newIsForCompensation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION, oldIsForCompensation,
                    isForCompensation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getStartQuantity() {
        return startQuantity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStartQuantity(int newStartQuantity) {
        int oldStartQuantity = startQuantity;
        startQuantity = newStartQuantity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ACTIVITY__START_QUANTITY, oldStartQuantity, startQuantity));
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
                    Bpmn2Package.ACTIVITY__VR_SPECIFICATION, oldVrSpecification, newVrSpecification);
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
                        EOPPOSITE_FEATURE_BASE - Bpmn2Package.ACTIVITY__VR_SPECIFICATION, null,
                        msgs);
            if (newVrSpecification != null)
                msgs = ((InternalEObject) newVrSpecification).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - Bpmn2Package.ACTIVITY__VR_SPECIFICATION, null,
                        msgs);
            msgs = basicSetVrSpecification(newVrSpecification, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.ACTIVITY__VR_SPECIFICATION, newVrSpecification, newVrSpecification));
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
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__VAR_POINT,
                    oldVarPoint, varPoint));
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
                    Bpmn2Package.ACTIVITY__VAR_POINT_TYPE, oldVarPointType, varPointType));
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
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__VARIANT,
                    oldVariant, variant));
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
                    Bpmn2Package.ACTIVITY__VARIANT_TYPE, oldVariantType, variantType));
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
                    Bpmn2Package.ACTIVITY__VARIABILITY_TYPE, oldVariabilityType, variabilityType));
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
                    Bpmn2Package.ACTIVITY__SEQUENTIAL, oldSequential, sequential));
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
                    Bpmn2Package.ACTIVITY__FEATURE_ID, oldFeatureId, featureId));
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
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__TYPE,
                    oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getBoundaryEventRefs())
                    .basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
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
        case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
            return basicSetIoSpecification(null, msgs);
        case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
            return ((InternalEList<?>) getBoundaryEventRefs()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.ACTIVITY__PROPERTIES:
            return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
            return ((InternalEList<?>) getDataInputAssociations()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
            return ((InternalEList<?>) getDataOutputAssociations()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.ACTIVITY__RESOURCES:
            return ((InternalEList<?>) getResources()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
            return basicSetLoopCharacteristics(null, msgs);
        case Bpmn2Package.ACTIVITY__VR_SPECIFICATION:
            return basicSetVrSpecification(null, msgs);
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
        case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
            return getIoSpecification();
        case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
            return getBoundaryEventRefs();
        case Bpmn2Package.ACTIVITY__PROPERTIES:
            return getProperties();
        case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
            return getDataInputAssociations();
        case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
            return getDataOutputAssociations();
        case Bpmn2Package.ACTIVITY__RESOURCES:
            return getResources();
        case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
            return getLoopCharacteristics();
        case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
            return getCompletionQuantity();
        case Bpmn2Package.ACTIVITY__DEFAULT:
            return getDefault();
        case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
            return isIsForCompensation();
        case Bpmn2Package.ACTIVITY__START_QUANTITY:
            return getStartQuantity();
        case Bpmn2Package.ACTIVITY__VR_SPECIFICATION:
            return getVrSpecification();
        case Bpmn2Package.ACTIVITY__VAR_POINT:
            return isVarPoint();
        case Bpmn2Package.ACTIVITY__VAR_POINT_TYPE:
            return getVarPointType();
        case Bpmn2Package.ACTIVITY__VARIANT:
            return isVariant();
        case Bpmn2Package.ACTIVITY__VARIANT_TYPE:
            return getVariantType();
        case Bpmn2Package.ACTIVITY__VARIABILITY_TYPE:
            return getVariabilityType();
        case Bpmn2Package.ACTIVITY__SEQUENTIAL:
            return getSequential();
        case Bpmn2Package.ACTIVITY__FEATURE_ID:
            return getFeatureId();
        case Bpmn2Package.ACTIVITY__TYPE:
            return getType();
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
        case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
            setIoSpecification((InputOutputSpecification) newValue);
            return;
        case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
            getBoundaryEventRefs().clear();
            getBoundaryEventRefs().addAll((Collection<? extends BoundaryEvent>) newValue);
            return;
        case Bpmn2Package.ACTIVITY__PROPERTIES:
            getProperties().clear();
            getProperties().addAll((Collection<? extends Property>) newValue);
            return;
        case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
            getDataInputAssociations().clear();
            getDataInputAssociations()
                    .addAll((Collection<? extends DataInputAssociation>) newValue);
            return;
        case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
            getDataOutputAssociations().clear();
            getDataOutputAssociations().addAll(
                    (Collection<? extends DataOutputAssociation>) newValue);
            return;
        case Bpmn2Package.ACTIVITY__RESOURCES:
            getResources().clear();
            getResources().addAll((Collection<? extends ResourceRole>) newValue);
            return;
        case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
            setLoopCharacteristics((LoopCharacteristics) newValue);
            return;
        case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
            setCompletionQuantity((Integer) newValue);
            return;
        case Bpmn2Package.ACTIVITY__DEFAULT:
            setDefault((SequenceFlow) newValue);
            return;
        case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
            setIsForCompensation((Boolean) newValue);
            return;
        case Bpmn2Package.ACTIVITY__START_QUANTITY:
            setStartQuantity((Integer) newValue);
            return;
        case Bpmn2Package.ACTIVITY__VR_SPECIFICATION:
            setVrSpecification((VariabilitySpecification) newValue);
            return;
        case Bpmn2Package.ACTIVITY__VAR_POINT:
            setVarPoint((Boolean) newValue);
            return;
        case Bpmn2Package.ACTIVITY__VAR_POINT_TYPE:
            setVarPointType((String) newValue);
            return;
        case Bpmn2Package.ACTIVITY__VARIANT:
            setVariant((Boolean) newValue);
            return;
        case Bpmn2Package.ACTIVITY__VARIANT_TYPE:
            setVariantType((String) newValue);
            return;
        case Bpmn2Package.ACTIVITY__VARIABILITY_TYPE:
            setVariabilityType((String) newValue);
            return;
        case Bpmn2Package.ACTIVITY__SEQUENTIAL:
            setSequential((String) newValue);
            return;
        case Bpmn2Package.ACTIVITY__FEATURE_ID:
            setFeatureId((String) newValue);
            return;
        case Bpmn2Package.ACTIVITY__TYPE:
            setType((String) newValue);
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
        case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
            setIoSpecification((InputOutputSpecification) null);
            return;
        case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
            getBoundaryEventRefs().clear();
            return;
        case Bpmn2Package.ACTIVITY__PROPERTIES:
            getProperties().clear();
            return;
        case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
            getDataInputAssociations().clear();
            return;
        case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
            getDataOutputAssociations().clear();
            return;
        case Bpmn2Package.ACTIVITY__RESOURCES:
            getResources().clear();
            return;
        case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
            setLoopCharacteristics((LoopCharacteristics) null);
            return;
        case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
            setCompletionQuantity(COMPLETION_QUANTITY_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__DEFAULT:
            setDefault((SequenceFlow) null);
            return;
        case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
            setIsForCompensation(IS_FOR_COMPENSATION_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__START_QUANTITY:
            setStartQuantity(START_QUANTITY_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__VR_SPECIFICATION:
            setVrSpecification((VariabilitySpecification) null);
            return;
        case Bpmn2Package.ACTIVITY__VAR_POINT:
            setVarPoint(VAR_POINT_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__VAR_POINT_TYPE:
            setVarPointType(VAR_POINT_TYPE_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__VARIANT:
            setVariant(VARIANT_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__VARIANT_TYPE:
            setVariantType(VARIANT_TYPE_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__VARIABILITY_TYPE:
            setVariabilityType(VARIABILITY_TYPE_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__SEQUENTIAL:
            setSequential(SEQUENTIAL_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__FEATURE_ID:
            setFeatureId(FEATURE_ID_EDEFAULT);
            return;
        case Bpmn2Package.ACTIVITY__TYPE:
            setType(TYPE_EDEFAULT);
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
        case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
            return ioSpecification != null;
        case Bpmn2Package.ACTIVITY__BOUNDARY_EVENT_REFS:
            return boundaryEventRefs != null && !boundaryEventRefs.isEmpty();
        case Bpmn2Package.ACTIVITY__PROPERTIES:
            return properties != null && !properties.isEmpty();
        case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
            return dataInputAssociations != null && !dataInputAssociations.isEmpty();
        case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
            return dataOutputAssociations != null && !dataOutputAssociations.isEmpty();
        case Bpmn2Package.ACTIVITY__RESOURCES:
            return resources != null && !resources.isEmpty();
        case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
            return loopCharacteristics != null;
        case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
            return completionQuantity != COMPLETION_QUANTITY_EDEFAULT;
        case Bpmn2Package.ACTIVITY__DEFAULT:
            return default_ != null;
        case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
            return isForCompensation != IS_FOR_COMPENSATION_EDEFAULT;
        case Bpmn2Package.ACTIVITY__START_QUANTITY:
            return startQuantity != START_QUANTITY_EDEFAULT;
        case Bpmn2Package.ACTIVITY__VR_SPECIFICATION:
            return vrSpecification != null;
        case Bpmn2Package.ACTIVITY__VAR_POINT:
            return varPoint != VAR_POINT_EDEFAULT;
        case Bpmn2Package.ACTIVITY__VAR_POINT_TYPE:
            return VAR_POINT_TYPE_EDEFAULT == null ? varPointType != null
                    : !VAR_POINT_TYPE_EDEFAULT.equals(varPointType);
        case Bpmn2Package.ACTIVITY__VARIANT:
            return variant != VARIANT_EDEFAULT;
        case Bpmn2Package.ACTIVITY__VARIANT_TYPE:
            return VARIANT_TYPE_EDEFAULT == null ? variantType != null : !VARIANT_TYPE_EDEFAULT
                    .equals(variantType);
        case Bpmn2Package.ACTIVITY__VARIABILITY_TYPE:
            return VARIABILITY_TYPE_EDEFAULT == null ? variabilityType != null
                    : !VARIABILITY_TYPE_EDEFAULT.equals(variabilityType);
        case Bpmn2Package.ACTIVITY__SEQUENTIAL:
            return SEQUENTIAL_EDEFAULT == null ? sequential != null : !SEQUENTIAL_EDEFAULT
                    .equals(sequential);
        case Bpmn2Package.ACTIVITY__FEATURE_ID:
            return FEATURE_ID_EDEFAULT == null ? featureId != null : !FEATURE_ID_EDEFAULT
                    .equals(featureId);
        case Bpmn2Package.ACTIVITY__TYPE:
            return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
        }
        return super.eIsSet(featureID);
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
        result.append(" (completionQuantity: ");
        result.append(completionQuantity);
        result.append(", isForCompensation: ");
        result.append(isForCompensation);
        result.append(", startQuantity: ");
        result.append(startQuantity);
        result.append(", varPoint: ");
        result.append(varPoint);
        result.append(", varPointType: ");
        result.append(varPointType);
        result.append(", variant: ");
        result.append(variant);
        result.append(", variantType: ");
        result.append(variantType);
        result.append(", variabilityType: ");
        result.append(variabilityType);
        result.append(", sequential: ");
        result.append(sequential);
        result.append(", featureId: ");
        result.append(featureId);
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //ActivityImpl
