/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.ParticipantAssociation;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.CallConversation}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CallConversationValidator {
    boolean validate();

    boolean validateParticipantAssociations(List<ParticipantAssociation> value);

    boolean validateCalledCollaborationRef(Collaboration value);
}
