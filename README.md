# BPL-Framework 2.0: Support tool for Creation and Instantiation of Business Process Lines.

### Installation
* TODO
### Development
Want to contribute? Great! You'll need...

* [Eclipse Kepler] and Java 6:
* Open Eclipse and go to Help > Install New Software… > Add all update sites below and install them
    * [FeatureIDE v2.6 or v2.7]
    * Equinox Advanced Configurator
    * [EMF]
    * [Graphiti]
    * [BPMN2 Modeler]

* Import packages below from BPLFramework repository
    * de.ovgu.featureide
	* de.ovgu.featureide.core
	* de.ovgu.featureide.core.ahead
	* de.ovgu.featureide.fm.core
	* de.ovgu.featureide.fm.ui
	* de.ovgu.featureide.ui
	* *.bpmn2
	* *.bpmn2.edit
	* *.bpmn2.editor
	* *.bpmn2.feature
	* *.bpmn2.modeler.core
	* *.bpmn2.modeler.ui
* If you are getting some error at packages *.bpmn2.modeler.core e *.bpmn2.modeler.ui:

    * Righ click button at package *.bpmn2.modeler.core -> Properties -> Java Compiler -> Enable project specific settings -> Use compliance from execution environment 'JavaSE-1.6'

* If you have newer java version and is getting "Cannot locate Execution Environment definition: "JavaSE-1.7". Launch aborted." message:

    * Righ click button at package *.bpmn2.modeler.ui -> Run As -> Run Configurations, tab Main -> Java Runtime Environment, select JavaSE 1.6 at field Execution Environment.

* Right click button at package *.bpmn2.modeler.ui -> Run as -> Eclipse Application

### Building

* TODO

### Todos
 - Migrate to newer Eclipse version

### Publications
* TODO

   [FeatureIDE v2.6 or v2.7]: <http://wwwiti.cs.uni-magdeburg.de/iti_db/research/featureide/deploy/>
   [Eclipse Kepler]: <https://www.eclipse.org/downloads/packages/release/Kepler/SR2>
   [EMF]: <http://download.eclipse.org/modeling/emf/emf/updates/releases/>
   [Graphiti]: <http://archive.eclipse.org/graphiti/updates/0.10.2>
   [BPMN2 Modeler]: <http://download.eclipse.org/bpmn2-modeler/updates/kepler/>

