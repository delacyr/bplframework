/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.Artifact;
import org.eclipse.bpmn2.Auditing;
import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.CorrelationSubscription;
import org.eclipse.bpmn2.Monitoring;
import org.eclipse.bpmn2.ProcessType;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ResourceRole;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.Process}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ProcessValidator {
    boolean validate();

    boolean validateAuditing(Auditing value);

    boolean validateMonitoring(Monitoring value);

    boolean validateProperties(List<Property> value);

    boolean validateArtifacts(List<Artifact> value);

    boolean validateResources(List<ResourceRole> value);

    boolean validateCorrelationSubscriptions(List<CorrelationSubscription> value);

    boolean validateSupports(List<org.eclipse.bpmn2.Process> value);

    boolean validateDefinitionalCollaborationRef(Collaboration value);

    boolean validateIsClosed(boolean value);

    boolean validateIsExecutable(boolean value);

    boolean validateProcessType(ProcessType value);
}