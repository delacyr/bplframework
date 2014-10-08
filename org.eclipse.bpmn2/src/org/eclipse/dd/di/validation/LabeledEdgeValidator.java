/**
 *
 * $Id$
 */
package org.eclipse.dd.di.validation;

import java.util.List;

import org.eclipse.dd.di.Label;

/**
 * A sample validator interface for {@link org.eclipse.dd.di.LabeledEdge}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface LabeledEdgeValidator {
    boolean validate();

    boolean validateOwnedLabel(List<Label> value);
}
