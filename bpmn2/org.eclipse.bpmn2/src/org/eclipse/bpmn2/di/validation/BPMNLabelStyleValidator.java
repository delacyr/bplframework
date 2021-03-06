/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.di.validation;

import org.eclipse.dd.dc.Font;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.di.BPMNLabelStyle}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface BPMNLabelStyleValidator {
    boolean validate();

    boolean validateFont(Font value);
}
