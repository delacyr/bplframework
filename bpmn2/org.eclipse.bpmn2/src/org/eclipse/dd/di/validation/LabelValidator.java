/**
 *
 * $Id$
 */
package org.eclipse.dd.di.validation;

import org.eclipse.dd.dc.Bounds;

/**
 * A sample validator interface for {@link org.eclipse.dd.di.Label}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface LabelValidator {
    boolean validate();

    boolean validateBounds(Bounds value);
}
