/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.ChoreographyLoopType;
import org.eclipse.bpmn2.CorrelationKey;
import org.eclipse.bpmn2.Participant;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.ChoreographyActivity}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ChoreographyActivityValidator {
    boolean validate();

    boolean validateParticipantRefs(List<Participant> value);

    boolean validateCorrelationKeys(List<CorrelationKey> value);

    boolean validateInitiatingParticipantRef(Participant value);

    boolean validateLoopType(ChoreographyLoopType value);
}
