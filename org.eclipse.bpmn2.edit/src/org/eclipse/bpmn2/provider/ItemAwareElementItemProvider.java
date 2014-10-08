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

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.ItemAwareElement;
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
 * This is the item provider adapter for a {@link org.eclipse.bpmn2.ItemAwareElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ItemAwareElementItemProvider extends BaseElementItemProvider implements
        IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ItemAwareElementItemProvider(AdapterFactory adapterFactory) {
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

            addItemSubjectRefPropertyDescriptor(object);
            addVarPointPropertyDescriptor(object);
            addVarPointTypePropertyDescriptor(object);
            addVariantPropertyDescriptor(object);
            addVariantTypePropertyDescriptor(object);
            addVariabilityTypePropertyDescriptor(object);
            addFeatureIdPropertyDescriptor(object);
            addTypePropertyDescriptor(object);
            addSequentialPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Item Subject Ref feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addItemSubjectRefPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_ItemAwareElement_itemSubjectRef_feature"),
                        getString("_UI_PropertyDescriptor_description",
                                "_UI_ItemAwareElement_itemSubjectRef_feature",
                                "_UI_ItemAwareElement_type"),
                        Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__ITEM_SUBJECT_REF, true, false,
                        true, null, null, null));
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
                getString("_UI_ItemAwareElement_varPoint_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_varPoint_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__VAR_POINT, true, false, false,
                ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
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
                getString("_UI_ItemAwareElement_varPointType_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_varPointType_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__VAR_POINT_TYPE, true, false, false,
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
                getString("_UI_ItemAwareElement_variant_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_variant_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__VARIANT, true, false, false,
                ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Variant Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addVariantTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_ItemAwareElement_variantType_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_variantType_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__VARIANT_TYPE, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Variability Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addVariabilityTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_ItemAwareElement_variabilityType_feature"),
                        getString("_UI_PropertyDescriptor_description",
                                "_UI_ItemAwareElement_variabilityType_feature",
                                "_UI_ItemAwareElement_type"),
                        Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__VARIABILITY_TYPE, true, false,
                        false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
                getString("_UI_ItemAwareElement_featureId_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_featureId_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__FEATURE_ID, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_ItemAwareElement_type_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_type_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__TYPE, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Sequential feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSequentialPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_ItemAwareElement_sequential_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_sequential_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__SEQUENTIAL, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__DATA_STATE);
            childrenFeatures.add(Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__VR_SPECIFICATION);
            childrenFeatures.add(Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__IO_SPECIFICATION);
            childrenFeatures.add(Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__DATA_INPUT_ASSOCIATIONS);
            childrenFeatures
                    .add(Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__DATA_OUTPUT_ASSOCIATIONS);
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
     * This returns ItemAwareElement.png.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        try {
            return overlayImage(object,
                    getResourceLocator().getImage("full/obj16/ItemAwareElement.png"));
        } catch (java.util.MissingResourceException e) {
            return overlayImage(object,
                    getResourceLocator().getImage("full/obj16/ItemAwareElement.gif"));
        }
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((ItemAwareElement) object).getId();
        return label == null || label.length() == 0 ? getString("_UI_ItemAwareElement_type")
                : getString("_UI_ItemAwareElement_type") + " " + label;
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

        switch (notification.getFeatureID(ItemAwareElement.class)) {
        case Bpmn2Package.ITEM_AWARE_ELEMENT__VAR_POINT:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__VAR_POINT_TYPE:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__VARIANT:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__VARIANT_TYPE:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__VARIABILITY_TYPE:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__FEATURE_ID:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__TYPE:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__SEQUENTIAL:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(),
                    false, true));
            return;
        case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__VR_SPECIFICATION:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__IO_SPECIFICATION:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_INPUT_ASSOCIATIONS:
        case Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_OUTPUT_ASSOCIATIONS:
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
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__DATA_STATE,
                Bpmn2Factory.eINSTANCE.createDataState()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__VR_SPECIFICATION,
                Bpmn2Factory.eINSTANCE.createVariabilitySpecification()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__IO_SPECIFICATION,
                Bpmn2Factory.eINSTANCE.createInputOutputSpecification()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__DATA_INPUT_ASSOCIATIONS,
                Bpmn2Factory.eINSTANCE.createDataInputAssociation()));

        newChildDescriptors.add(createChildParameter(
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__DATA_OUTPUT_ASSOCIATIONS,
                Bpmn2Factory.eINSTANCE.createDataOutputAssociation()));
    }

}
