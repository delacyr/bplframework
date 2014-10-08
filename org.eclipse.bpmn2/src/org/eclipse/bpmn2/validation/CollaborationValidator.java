/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.Artifact;
import org.eclipse.bpmn2.Choreography;
import org.eclipse.bpmn2.ConversationAssociation;
import org.eclipse.bpmn2.ConversationLink;
import org.eclipse.bpmn2.ConversationNode;
import org.eclipse.bpmn2.CorrelationKey;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.MessageFlowAssociation;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.ParticipantAssociation;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.Collaboration}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CollaborationValidator {
    boolean validate();

    boolean validateParticipants(List<Participant> value);

    boolean validateMessageFlows(List<MessageFlow> value);

    boolean validateArtifacts(List<Artifact> value);

    boolean validateConversations(List<ConversationNode> value);

    boolean validateConversationAssociations(List<ConversationAssociation> value);

    boolean validateParticipantAssociations(List<ParticipantAssociation> value);

    boolean validateMessageFlowAssociations(List<MessageFlowAssociation> value);

    boolean validateCorrelationKeys(List<CorrelationKey> value);

    boolean validateChoreographyRef(List<Choreography> value);

    boolean validateConversationLinks(List<ConversationLink> value);

    boolean validateIsClosed(boolean value);

    boolean validateName(String value);
}
