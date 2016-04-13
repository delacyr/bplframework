/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.VariabilitySpecification;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.Activity}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ActivityValidator {
    boolean validate();

    boolean validateIoSpecification(InputOutputSpecification value);

    boolean validateBoundaryEventRefs(List<BoundaryEvent> value);

    boolean validateProperties(List<Property> value);

    boolean validateDataInputAssociations(List<DataInputAssociation> value);

    boolean validateDataOutputAssociations(List<DataOutputAssociation> value);

    boolean validateResources(List<ResourceRole> value);

    boolean validateLoopCharacteristics(LoopCharacteristics value);

    boolean validateCompletionQuantity(int value);

    boolean validateDefault(SequenceFlow value);

    boolean validateIsForCompensation(boolean value);

    boolean validateStartQuantity(int value);

    boolean validateVrSpecification(VariabilitySpecification value);

    boolean validateVarPoint(boolean value);

    boolean validateVarPoint(String value);

    boolean validateIsVarpoint(boolean value);

    boolean validateVarPointType(String value);

    boolean validateVariant(boolean value);

    boolean validateFeatureType(String value);

    boolean validateVariantType(String value);

    boolean validateVariabilityType(String value);

    boolean validateSequential(String value);

    boolean validateSequencial(String value);

    boolean validateFeatureId(String value);

    boolean validateCheck(boolean value);

    boolean validateSolved(boolean value);

    boolean validateSeq(int value);

    boolean validateGateway(String value);

    boolean validateOrder(int value);

    boolean validateGateway(int value);

    boolean validateType(String value);
}
