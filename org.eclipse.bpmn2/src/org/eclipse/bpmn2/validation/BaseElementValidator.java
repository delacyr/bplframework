/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.ExtensionDefinition;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.BaseElement}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface BaseElementValidator {
    boolean validate();

    boolean validateExtensionValues(List<ExtensionAttributeValue> value);

    boolean validateDocumentation(List<Documentation> value);

    boolean validateExtensionDefinitions(List<ExtensionDefinition> value);

    boolean validateId(String value);

    boolean validateAnyAttribute(FeatureMap value);
}
