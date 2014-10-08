/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import org.eclipse.bpmn2.ItemDefinition;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.Escalation}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface EscalationValidator {
    boolean validate();

    boolean validateEscalationCode(String value);

    boolean validateName(String value);

    boolean validateStructureRef(ItemDefinition value);
}
