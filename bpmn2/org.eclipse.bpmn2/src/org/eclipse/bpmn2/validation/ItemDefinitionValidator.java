/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.ItemKind;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.ItemDefinition}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ItemDefinitionValidator {
    boolean validate();

    boolean validateIsCollection(boolean value);

    boolean validateImport(Import value);

    boolean validateItemKind(ItemKind value);

    boolean validateStructureRef(Object value);
}
