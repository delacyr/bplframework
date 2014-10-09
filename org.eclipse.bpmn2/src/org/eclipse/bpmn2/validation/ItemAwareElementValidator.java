/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.DataState;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.VariabilitySpecification;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.ItemAwareElement}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ItemAwareElementValidator {
    boolean validate();

    boolean validateDataState(DataState value);

    boolean validateItemSubjectRef(ItemDefinition value);

    boolean validateVrSpecification(VariabilitySpecification value);

    boolean validateVarPoint(boolean value);

    boolean validateVarPointType(String value);

    boolean validateVariant(boolean value);

    boolean validateVariantType(String value);

    boolean validateVariabilityType(String value);

    boolean validateSequencial(String value);

    boolean validateFeatureId(String value);

    boolean validateType(String value);

    boolean validateIoSpecification(InputOutputSpecification value);

    boolean validateDataInputAssociations(List<DataInputAssociation> value);

    boolean validateDataOutputAssociations(List<DataOutputAssociation> value);

    boolean validateSequential(String value);
}