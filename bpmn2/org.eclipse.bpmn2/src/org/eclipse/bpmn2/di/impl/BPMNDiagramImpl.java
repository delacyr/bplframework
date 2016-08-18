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
package org.eclipse.bpmn2.di.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNLabelStyle;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.dd.di.impl.DiagramImpl;
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
 * An implementation of the model object '<em><b>BPMN Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNDiagramImpl#getPlane <em>Plane</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNDiagramImpl#getLabelStyle <em>Label Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNDiagramImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNDiagramImpl#getPhase <em>Phase</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNDiagramImpl#getFeatureModel <em>Feature Model</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.di.impl.BPMNDiagramImpl#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BPMNDiagramImpl extends DiagramImpl implements BPMNDiagram {
    /**
     * The cached value of the '{@link #getPlane() <em>Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPlane()
     * @generated
     * @ordered
     */
    protected BPMNPlane plane;

    /**
     * The cached value of the '{@link #getLabelStyle() <em>Label Style</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelStyle()
     * @generated
     * @ordered
     */
    protected EList<BPMNLabelStyle> labelStyle;

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final long VERSION_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected long version = VERSION_EDEFAULT;

    /**
     * The default value of the '{@link #getPhase() <em>Phase</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPhase()
     * @generated
     * @ordered
     */
    protected static final String PHASE_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getPhase() <em>Phase</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPhase()
     * @generated
     * @ordered
     */
    protected String phase = PHASE_EDEFAULT;

    /**
     * The default value of the '{@link #getFeatureModel() <em>Feature Model</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatureModel()
     * @generated
     * @ordered
     */
    protected static final String FEATURE_MODEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFeatureModel() <em>Feature Model</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatureModel()
     * @generated
     * @ordered
     */
    protected String featureModel = FEATURE_MODEL_EDEFAULT;

    /**
     * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected static final String LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected String location = LOCATION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BPMNDiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmnDiPackage.Literals.BPMN_DIAGRAM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNPlane getPlane() {
        return plane;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPlane(BPMNPlane newPlane, NotificationChain msgs) {
        BPMNPlane oldPlane = plane;
        plane = newPlane;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_DIAGRAM__PLANE, oldPlane, newPlane);
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
    public void setPlane(BPMNPlane newPlane) {
        if (newPlane != plane) {
            NotificationChain msgs = null;
            if (plane != null)
                msgs = ((InternalEObject) plane).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - BpmnDiPackage.BPMN_DIAGRAM__PLANE, null, msgs);
            if (newPlane != null)
                msgs = ((InternalEObject) newPlane).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - BpmnDiPackage.BPMN_DIAGRAM__PLANE, null, msgs);
            msgs = basicSetPlane(newPlane, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_DIAGRAM__PLANE, newPlane, newPlane));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<BPMNLabelStyle> getLabelStyle() {
        if (labelStyle == null) {
            labelStyle = new EObjectContainmentEList<BPMNLabelStyle>(BPMNLabelStyle.class, this,
                    BpmnDiPackage.BPMN_DIAGRAM__LABEL_STYLE);
        }
        return labelStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVersion(long newVersion) {
        long oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_DIAGRAM__VERSION, oldVersion, version));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPhase() {
        return phase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPhase(String newPhase) {
        String oldPhase = phase;
        phase = newPhase;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_DIAGRAM__PHASE, oldPhase, phase));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFeatureModel() {
        return featureModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeatureModel(String newFeatureModel) {
        String oldFeatureModel = featureModel;
        featureModel = newFeatureModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_DIAGRAM__FEATURE_MODEL, oldFeatureModel, featureModel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLocation() {
        return location;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLocation(String newLocation) {
        String oldLocation = location;
        location = newLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    BpmnDiPackage.BPMN_DIAGRAM__LOCATION, oldLocation, location));
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
        case BpmnDiPackage.BPMN_DIAGRAM__PLANE:
            return basicSetPlane(null, msgs);
        case BpmnDiPackage.BPMN_DIAGRAM__LABEL_STYLE:
            return ((InternalEList<?>) getLabelStyle()).basicRemove(otherEnd, msgs);
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
        case BpmnDiPackage.BPMN_DIAGRAM__PLANE:
            return getPlane();
        case BpmnDiPackage.BPMN_DIAGRAM__LABEL_STYLE:
            return getLabelStyle();
        case BpmnDiPackage.BPMN_DIAGRAM__VERSION:
            return getVersion();
        case BpmnDiPackage.BPMN_DIAGRAM__PHASE:
            return getPhase();
        case BpmnDiPackage.BPMN_DIAGRAM__FEATURE_MODEL:
            return getFeatureModel();
        case BpmnDiPackage.BPMN_DIAGRAM__LOCATION:
            return getLocation();
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
        case BpmnDiPackage.BPMN_DIAGRAM__PLANE:
            setPlane((BPMNPlane) newValue);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__LABEL_STYLE:
            getLabelStyle().clear();
            getLabelStyle().addAll((Collection<? extends BPMNLabelStyle>) newValue);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__VERSION:
            setVersion((Long) newValue);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__PHASE:
            setPhase((String) newValue);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__FEATURE_MODEL:
            setFeatureModel((String) newValue);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__LOCATION:
            setLocation((String) newValue);
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
        case BpmnDiPackage.BPMN_DIAGRAM__PLANE:
            setPlane((BPMNPlane) null);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__LABEL_STYLE:
            getLabelStyle().clear();
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__VERSION:
            setVersion(VERSION_EDEFAULT);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__PHASE:
            setPhase(PHASE_EDEFAULT);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__FEATURE_MODEL:
            setFeatureModel(FEATURE_MODEL_EDEFAULT);
            return;
        case BpmnDiPackage.BPMN_DIAGRAM__LOCATION:
            setLocation(LOCATION_EDEFAULT);
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
        case BpmnDiPackage.BPMN_DIAGRAM__PLANE:
            return plane != null;
        case BpmnDiPackage.BPMN_DIAGRAM__LABEL_STYLE:
            return labelStyle != null && !labelStyle.isEmpty();
        case BpmnDiPackage.BPMN_DIAGRAM__VERSION:
            return version != VERSION_EDEFAULT;
        case BpmnDiPackage.BPMN_DIAGRAM__PHASE:
            return PHASE_EDEFAULT == null ? phase != null : !PHASE_EDEFAULT.equals(phase);
        case BpmnDiPackage.BPMN_DIAGRAM__FEATURE_MODEL:
            return FEATURE_MODEL_EDEFAULT == null ? featureModel != null : !FEATURE_MODEL_EDEFAULT
                    .equals(featureModel);
        case BpmnDiPackage.BPMN_DIAGRAM__LOCATION:
            return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT
                    .equals(location);
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
        result.append(" (version: ");
        result.append(version);
        result.append(", phase: ");
        result.append(phase);
        result.append(", featureModel: ");
        result.append(featureModel);
        result.append(", location: ");
        result.append(location);
        result.append(')');
        return result.toString();
    }

    @Override
    public DiagramElement getRootElement() {
        return getPlane();
    }
} //BPMNDiagramImpl
