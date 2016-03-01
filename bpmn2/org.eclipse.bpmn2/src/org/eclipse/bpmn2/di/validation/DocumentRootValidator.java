/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.di.validation;

import java.util.Map;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNLabel;
import org.eclipse.bpmn2.di.BPMNLabelStyle;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.di.BPMNShape;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.di.DocumentRoot}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface DocumentRootValidator {
    boolean validate();

    boolean validateMixed(FeatureMap value);

    boolean validateXMLNSPrefixMap(Map<String, String> value);

    boolean validateXSISchemaLocation(Map<String, String> value);

    boolean validateBPMNDiagram(BPMNDiagram value);

    boolean validateBPMNEdge(BPMNEdge value);

    boolean validateBPMNLabel(BPMNLabel value);

    boolean validateBPMNLabelStyle(BPMNLabelStyle value);

    boolean validateBPMNPlane(BPMNPlane value);

    boolean validateBPMNShape(BPMNShape value);
}