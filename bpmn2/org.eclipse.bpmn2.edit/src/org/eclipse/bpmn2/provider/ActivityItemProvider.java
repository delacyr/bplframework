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
package org.eclipse.bpmn2.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.bpmn2.Activity} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ActivityItemProvider extends FlowNodeItemProvider implements
        IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActivityItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addBoundaryEventRefsPropertyDescriptor(object);
            addCompletionQuantityPropertyDescriptor(object);
            addDefaultPropertyDescriptor(object);
            addIsForCompensationPropertyDescriptor(object);
            addStartQuantityPropertyDescriptor(object);
            addVarPointPropertyDescriptor(object);
            addVarPointTypePropertyDescriptor(object);
            addVariantPropertyDescriptor(object);
            addFeatureTypePropertyDescriptor(object);
            addFeatureIdPropertyDescriptor(object);
            addCheckPropertyDescriptor(object);
            addSolvedPropertyDescriptor(object);
            addSeqPropertyDescriptor(object);
            addGatewayPropertyDescriptor(object);
            addShowInstanceNamePropertyDescriptor(object);
            addInstanceNamePropertyDescriptor(object);
            addSpecsNamePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Boundary Event Refs feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addBoundaryEventRefsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_boundaryEventRefs_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_Activity_boundaryEventRefs_feature", "_UI_Activity_type"),
                Bpmn2Package.Literals.ACTIVITY__BOUNDARY_EVENT_REFS, true, false, true, null, null,
                null));
    }

    /**
     * This adds a property descriptor for the Completion Quantity feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addCompletionQuantityPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_completionQuantity_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_Activity_completionQuantity_feature", "_UI_Activity_type"),
                Bpmn2Package.Literals.ACTIVITY__COMPLETION_QUANTITY, true, false, false,
                ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Default feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addDefaultPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_default_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_default_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__DEFAULT, true, false,
                true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Is For Compensation feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addIsForCompensationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_isForCompensation_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_Activity_isForCompensation_feature", "_UI_Activity_type"),
                Bpmn2Package.Literals.ACTIVITY__IS_FOR_COMPENSATION, true, false, false,
                ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Start Quantity feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addStartQuantityPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_startQuantity_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_Activity_startQuantity_feature", "_UI_Activity_type"),
                Bpmn2Package.Literals.ACTIVITY__START_QUANTITY, true, false, false,
                ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Var Point feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addVarPointPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_varPoint_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_varPoint_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__VAR_POINT, true,
                false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Var Point Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addVarPointTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_varPointType_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_Activity_varPointType_feature", "_UI_Activity_type"),
                Bpmn2Package.Literals.ACTIVITY__VAR_POINT_TYPE, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Variant feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addVariantPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_variant_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_variant_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__VARIANT, true, false,
                false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Feature Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addFeatureTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_featureType_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_featureType_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__FEATURE_TYPE, true,
                false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Feature Id feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addFeatureIdPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_featureId_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_featureId_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__FEATURE_ID, true,
                false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Check feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addCheckPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_check_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_check_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__CHECK, true, false,
                false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Solved feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSolvedPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_solved_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_solved_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__SOLVED, true, false,
                false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Seq feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSeqPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_seq_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_seq_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__SEQ, true, false,
                false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Gateway feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addGatewayPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_gateway_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_gateway_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__GATEWAY, true, false,
                false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Instance Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addInstanceNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_instanceName_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_Activity_instanceName_feature", "_UI_Activity_type"),
                Bpmn2Package.Literals.ACTIVITY__INSTANCE_NAME, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Specs Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSpecsNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_specsName_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Activity_specsName_feature",
                        "_UI_Activity_type"), Bpmn2Package.Literals.ACTIVITY__SPECS_NAME, true,
                false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Show Instance Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addShowInstanceNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Activity_showInstanceName_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_Activity_showInstanceName_feature", "_UI_Activity_type"),
                Bpmn2Package.Literals.ACTIVITY__SHOW_INSTANCE_NAME, true, false, false,
                ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(Bpmn2Package.Literals.ACTIVITY__IO_SPECIFICATION);
            childrenFeatures.add(Bpmn2Package.Literals.ACTIVITY__PROPERTIES);
            childrenFeatures.add(Bpmn2Package.Literals.ACTIVITY__DATA_INPUT_ASSOCIATIONS);
            childrenFeatures.add(Bpmn2Package.Literals.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS);
            childrenFeatures.add(Bpmn2Package.Literals.ACTIVITY__RESOURCES);
            childrenFeatures.add(Bpmn2Package.Literals.ACTIVITY__LOOP_CHARACTERISTICS);
            childrenFeatures.add(Bpmn2Package.Literals.ACTIVITY__VR_SPECIFICATION);
            childrenFeatures.add(Bpmn2Package.Literals.ACTIVITY__INSTANTIATION);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((Activity) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_Activity_type")
                : getString("_UI_Activity_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Activity.class)) {
        case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
        case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
        case Bpmn2Package.ACTIVITY__START_QUANTITY:
        case Bpmn2Package.ACTIVITY__VAR_POINT:
        case Bpmn2Package.ACTIVITY__VAR_POINT_TYPE:
        case Bpmn2Package.ACTIVITY__VARIANT:
        case Bpmn2Package.ACTIVITY__FEATURE_TYPE:
        case Bpmn2Package.ACTIVITY__FEATURE_ID:
        case Bpmn2Package.ACTIVITY__CHECK:
        case Bpmn2Package.ACTIVITY__SOLVED:
        case Bpmn2Package.ACTIVITY__SEQ:
        case Bpmn2Package.ACTIVITY__GATEWAY:
        case Bpmn2Package.ACTIVITY__SHOW_INSTANCE_NAME:
        case Bpmn2Package.ACTIVITY__INSTANCE_NAME:
        case Bpmn2Package.ACTIVITY__SPECS_NAME:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(),
                    false, true));
            return;
        case Bpmn2Package.ACTIVITY__IO_SPECIFICATION:
        case Bpmn2Package.ACTIVITY__PROPERTIES:
        case Bpmn2Package.ACTIVITY__DATA_INPUT_ASSOCIATIONS:
        case Bpmn2Package.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS:
        case Bpmn2Package.ACTIVITY__RESOURCES:
        case Bpmn2Package.ACTIVITY__LOOP_CHARACTERISTICS:
        case Bpmn2Package.ACTIVITY__VR_SPECIFICATION:
        case Bpmn2Package.ACTIVITY__INSTANTIATION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(),
                    true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ACTIVITY__IO_SPECIFICATION,
                Bpmn2Factory.eINSTANCE.createInputOutputSpecification()));

        newChildDescriptors.add(createChildParameter(Bpmn2Package.Literals.ACTIVITY__PROPERTIES,
                Bpmn2Factory.eINSTANCE.createProperty()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ACTIVITY__DATA_INPUT_ASSOCIATIONS,
                Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ACTIVITY__DATA_OUTPUT_ASSOCIATIONS,
                Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));

        newChildDescriptors.add(createChildParameter(Bpmn2Package.Literals.ACTIVITY__RESOURCES,
                Bpmn2Factory.eINSTANCE.createResourceRole()));

        newChildDescriptors.add(createChildParameter(Bpmn2Package.Literals.ACTIVITY__RESOURCES,
                Bpmn2Factory.eINSTANCE.createPerformer()));

        newChildDescriptors.add(createChildParameter(Bpmn2Package.Literals.ACTIVITY__RESOURCES,
                Bpmn2Factory.eINSTANCE.createHumanPerformer()));

        newChildDescriptors.add(createChildParameter(Bpmn2Package.Literals.ACTIVITY__RESOURCES,
                Bpmn2Factory.eINSTANCE.createPotentialOwner()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ACTIVITY__LOOP_CHARACTERISTICS,
                Bpmn2Factory.eINSTANCE.createMultiInstanceLoopCharacteristics()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ACTIVITY__LOOP_CHARACTERISTICS,
                Bpmn2Factory.eINSTANCE.createStandardLoopCharacteristics()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ACTIVITY__VR_SPECIFICATION,
                Bpmn2Factory.eINSTANCE.createVariabilitySpecification()));

        newChildDescriptors.add(createChildParameter(Bpmn2Package.Literals.ACTIVITY__INSTANTIATION,
                Bpmn2Factory.eINSTANCE.createInstatiationPhase()));
    }

}
