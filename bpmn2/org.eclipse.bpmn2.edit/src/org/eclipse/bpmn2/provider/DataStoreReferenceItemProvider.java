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
import org.eclipse.bpmn2.DataStoreReference;
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
 * This is the item provider adapter for a {@link org.eclipse.bpmn2.DataStoreReference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DataStoreReferenceItemProvider extends FlowElementItemProvider implements
        IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataStoreReferenceItemProvider(AdapterFactory adapterFactory) {
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
            addFeatureTypePropertyDescriptor(object);
            addFeatureIdPropertyDescriptor(object);
            addCheckPropertyDescriptor(object);
            addSolvedPropertyDescriptor(object);
            addDataStoreRefPropertyDescriptor(object);
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
     * This adds a property descriptor for the Feature Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addFeatureTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_ItemAwareElement_featureType_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_featureType_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__FEATURE_TYPE, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
     * This adds a property descriptor for the Check feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addCheckPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_ItemAwareElement_check_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_check_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__CHECK, true, false, false,
                ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
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
                getString("_UI_ItemAwareElement_solved_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_ItemAwareElement_solved_feature", "_UI_ItemAwareElement_type"),
                Bpmn2Package.Literals.ITEM_AWARE_ELEMENT__SOLVED, true, false, false,
                ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Data Store Ref feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addDataStoreRefPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_DataStoreReference_dataStoreRef_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_DataStoreReference_dataStoreRef_feature",
                        "_UI_DataStoreReference_type"),
                Bpmn2Package.Literals.DATA_STORE_REFERENCE__DATA_STORE_REF, true, false, true,
                null, null, null));
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
     * This returns DataStoreReference.png.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        try {
            return overlayImage(object,
                    getResourceLocator().getImage("full/obj16/DataStoreReference.png"));
        } catch (java.util.MissingResourceException e) {
            return overlayImage(object,
                    getResourceLocator().getImage("full/obj16/DataStoreReference.gif"));
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
        String label = ((DataStoreReference) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DataStoreReference_type")
                : getString("_UI_DataStoreReference_type") + " " + label;
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

        switch (notification.getFeatureID(DataStoreReference.class)) {
        case Bpmn2Package.DATA_STORE_REFERENCE__VAR_POINT:
        case Bpmn2Package.DATA_STORE_REFERENCE__VAR_POINT_TYPE:
        case Bpmn2Package.DATA_STORE_REFERENCE__VARIANT:
        case Bpmn2Package.DATA_STORE_REFERENCE__FEATURE_TYPE:
        case Bpmn2Package.DATA_STORE_REFERENCE__FEATURE_ID:
        case Bpmn2Package.DATA_STORE_REFERENCE__CHECK:
        case Bpmn2Package.DATA_STORE_REFERENCE__SOLVED:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(),
                    false, true));
            return;
        case Bpmn2Package.DATA_STORE_REFERENCE__DATA_STATE:
        case Bpmn2Package.DATA_STORE_REFERENCE__VR_SPECIFICATION:
        case Bpmn2Package.DATA_STORE_REFERENCE__IO_SPECIFICATION:
        case Bpmn2Package.DATA_STORE_REFERENCE__DATA_INPUT_ASSOCIATIONS:
        case Bpmn2Package.DATA_STORE_REFERENCE__DATA_OUTPUT_ASSOCIATIONS:
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
