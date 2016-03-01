/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.LaneSet;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.Lane}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface LaneValidator {
    boolean validate();

    boolean validatePartitionElement(BaseElement value);

    boolean validateFlowNodeRefs(List<FlowNode> value);

    boolean validateChildLaneSet(LaneSet value);

    boolean validateName(String value);

    boolean validatePartitionElementRef(BaseElement value);
}