/**
 *
 * $Id$
 */
package org.eclipse.dd.di.validation;

import java.util.List;

import org.eclipse.dd.dc.Point;

import org.eclipse.dd.di.DiagramElement;

/**
 * A sample validator interface for {@link org.eclipse.dd.di.Edge}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface EdgeValidator {
    boolean validate();

    boolean validateSource(DiagramElement value);

    boolean validateTarget(DiagramElement value);

    boolean validateWaypoint(List<Point> value);
}
