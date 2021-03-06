/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import org.eclipse.bpmn2.ExtensionAttributeDefinition;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.ExtensionAttributeValue}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ExtensionAttributeValueValidator {
    boolean validate();

    boolean validateValueRef(Object value);

    boolean validateValue(FeatureMap value);

    boolean validateExtensionAttributeDefinition(ExtensionAttributeDefinition value);
}
