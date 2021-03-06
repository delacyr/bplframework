/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.List;

import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.InputSet;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.OutputSet}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface OutputSetValidator {
    boolean validate();

    boolean validateDataOutputRefs(List<DataOutput> value);

    boolean validateOptionalOutputRefs(List<DataOutput> value);

    boolean validateWhileExecutingOutputRefs(List<DataOutput> value);

    boolean validateInputSetRefs(List<InputSet> value);

    boolean validateName(String value);
}
