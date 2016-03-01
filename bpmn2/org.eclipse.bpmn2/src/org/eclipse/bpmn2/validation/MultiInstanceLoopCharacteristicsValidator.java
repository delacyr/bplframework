/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.ComplexBehaviorDefinition;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.MultiInstanceBehavior;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface MultiInstanceLoopCharacteristicsValidator {
    boolean validate();

    boolean validateLoopCardinality(Expression value);

    boolean validateLoopDataInputRef(ItemAwareElement value);

    boolean validateLoopDataOutputRef(ItemAwareElement value);

    boolean validateInputDataItem(DataInput value);

    boolean validateOutputDataItem(DataOutput value);

    boolean validateComplexBehaviorDefinition(List<ComplexBehaviorDefinition> value);

    boolean validateCompletionCondition(Expression value);

    boolean validateBehavior(MultiInstanceBehavior value);

    boolean validateIsSequential(boolean value);

    boolean validateNoneBehaviorEventRef(EventDefinition value);

    boolean validateOneBehaviorEventRef(EventDefinition value);
}