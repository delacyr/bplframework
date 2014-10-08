/**
 *
 * $Id$
 */
package org.eclipse.bpmn2.validation;

import java.util.Map;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.Artifact;
import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.Auditing;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.BusinessRuleTask;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallChoreography;
import org.eclipse.bpmn2.CallConversation;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.CancelEventDefinition;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Category;
import org.eclipse.bpmn2.CategoryValue;
import org.eclipse.bpmn2.Choreography;
import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.CompensateEventDefinition;
import org.eclipse.bpmn2.ComplexBehaviorDefinition;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.ConditionalEventDefinition;
import org.eclipse.bpmn2.Conversation;
import org.eclipse.bpmn2.ConversationAssociation;
import org.eclipse.bpmn2.ConversationLink;
import org.eclipse.bpmn2.ConversationNode;
import org.eclipse.bpmn2.CorrelationKey;
import org.eclipse.bpmn2.CorrelationProperty;
import org.eclipse.bpmn2.CorrelationPropertyBinding;
import org.eclipse.bpmn2.CorrelationPropertyRetrievalExpression;
import org.eclipse.bpmn2.CorrelationSubscription;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.DataState;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.EndPoint;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.Escalation;
import org.eclipse.bpmn2.EscalationEventDefinition;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventBasedGateway;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.Extension;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.GlobalBusinessRuleTask;
import org.eclipse.bpmn2.GlobalChoreographyTask;
import org.eclipse.bpmn2.GlobalConversation;
import org.eclipse.bpmn2.GlobalManualTask;
import org.eclipse.bpmn2.GlobalScriptTask;
import org.eclipse.bpmn2.GlobalTask;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.bpmn2.Group;
import org.eclipse.bpmn2.HumanPerformer;
import org.eclipse.bpmn2.ImplicitThrowEvent;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.InputOutputBinding;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.LinkEventDefinition;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.MessageFlowAssociation;
import org.eclipse.bpmn2.Monitoring;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.ParticipantAssociation;
import org.eclipse.bpmn2.ParticipantMultiplicity;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.PartnerRole;
import org.eclipse.bpmn2.Performer;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.Relationship;
import org.eclipse.bpmn2.Rendering;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.ResourceParameter;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.Signal;
import org.eclipse.bpmn2.SignalEventDefinition;
import org.eclipse.bpmn2.StandardLoopCharacteristics;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubChoreography;
import org.eclipse.bpmn2.SubConversation;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.TextAnnotation;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.Transaction;
import org.eclipse.bpmn2.UserTask;

import org.eclipse.bpmn2.VariabilitySpecification;
import org.eclipse.bpmn2.VrProcess;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * A sample validator interface for {@link org.eclipse.bpmn2.DocumentRoot}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface DocumentRootValidator {
    boolean validate();

    boolean validateMixed(FeatureMap value);

    boolean validateXMLNSPrefixMap(Map<String, String> value);

    boolean validateXSISchemaLocation(Map<String, String> value);

    boolean validateActivity(Activity value);

    boolean validateAdHocSubProcess(AdHocSubProcess value);

    boolean validateFlowElement(FlowElement value);

    boolean validateArtifact(Artifact value);

    boolean validateAssignment(Assignment value);

    boolean validateAssociation(Association value);

    boolean validateAuditing(Auditing value);

    boolean validateBaseElement(BaseElement value);

    boolean validateBaseElementWithMixedContent(BaseElement value);

    boolean validateBoundaryEvent(BoundaryEvent value);

    boolean validateBusinessRuleTask(BusinessRuleTask value);

    boolean validateCallableElement(CallableElement value);

    boolean validateCallActivity(CallActivity value);

    boolean validateCallChoreography(CallChoreography value);

    boolean validateCallConversation(CallConversation value);

    boolean validateConversationNode(ConversationNode value);

    boolean validateCancelEventDefinition(CancelEventDefinition value);

    boolean validateEventDefinition(EventDefinition value);

    boolean validateRootElement(RootElement value);

    boolean validateCatchEvent(CatchEvent value);

    boolean validateCategory(Category value);

    boolean validateCategoryValue(CategoryValue value);

    boolean validateChoreography(Choreography value);

    boolean validateCollaboration(Collaboration value);

    boolean validateChoreographyActivity(ChoreographyActivity value);

    boolean validateChoreographyTask(ChoreographyTask value);

    boolean validateCompensateEventDefinition(CompensateEventDefinition value);

    boolean validateComplexBehaviorDefinition(ComplexBehaviorDefinition value);

    boolean validateComplexGateway(ComplexGateway value);

    boolean validateConditionalEventDefinition(ConditionalEventDefinition value);

    boolean validateConversation(Conversation value);

    boolean validateConversationAssociation(ConversationAssociation value);

    boolean validateConversationLink(ConversationLink value);

    boolean validateCorrelationKey(CorrelationKey value);

    boolean validateCorrelationProperty(CorrelationProperty value);

    boolean validateCorrelationPropertyBinding(CorrelationPropertyBinding value);

    boolean validateCorrelationPropertyRetrievalExpression(
            CorrelationPropertyRetrievalExpression value);

    boolean validateCorrelationSubscription(CorrelationSubscription value);

    boolean validateDataAssociation(DataAssociation value);

    boolean validateDataInput(DataInput value);

    boolean validateDataInputAssociation(DataInputAssociation value);

    boolean validateDataObject(DataObject value);

    boolean validateDataObjectReference(DataObjectReference value);

    boolean validateDataOutput(DataOutput value);

    boolean validateDataOutputAssociation(DataOutputAssociation value);

    boolean validateDataState(DataState value);

    boolean validateDataStore(DataStore value);

    boolean validateDataStoreReference(DataStoreReference value);

    boolean validateDefinitions(Definitions value);

    boolean validateDocumentation(Documentation value);

    boolean validateEndEvent(EndEvent value);

    boolean validateEndPoint(EndPoint value);

    boolean validateError(org.eclipse.bpmn2.Error value);

    boolean validateErrorEventDefinition(ErrorEventDefinition value);

    boolean validateEscalation(Escalation value);

    boolean validateEscalationEventDefinition(EscalationEventDefinition value);

    boolean validateEvent(Event value);

    boolean validateEventBasedGateway(EventBasedGateway value);

    boolean validateExclusiveGateway(ExclusiveGateway value);

    boolean validateExpression(Expression value);

    boolean validateExtension(Extension value);

    boolean validateExtensionElements(ExtensionAttributeValue value);

    boolean validateFlowNode(FlowNode value);

    boolean validateFormalExpression(FormalExpression value);

    boolean validateGateway(Gateway value);

    boolean validateGlobalBusinessRuleTask(GlobalBusinessRuleTask value);

    boolean validateGlobalChoreographyTask(GlobalChoreographyTask value);

    boolean validateGlobalConversation(GlobalConversation value);

    boolean validateGlobalManualTask(GlobalManualTask value);

    boolean validateGlobalScriptTask(GlobalScriptTask value);

    boolean validateGlobalTask(GlobalTask value);

    boolean validateGlobalUserTask(GlobalUserTask value);

    boolean validateGroup(Group value);

    boolean validateHumanPerformer(HumanPerformer value);

    boolean validatePerformer(Performer value);

    boolean validateResourceRole(ResourceRole value);

    boolean validateImplicitThrowEvent(ImplicitThrowEvent value);

    boolean validateImport(Import value);

    boolean validateInclusiveGateway(InclusiveGateway value);

    boolean validateInputSet(InputSet value);

    boolean validateInterface(Interface value);

    boolean validateIntermediateCatchEvent(IntermediateCatchEvent value);

    boolean validateIntermediateThrowEvent(IntermediateThrowEvent value);

    boolean validateIoBinding(InputOutputBinding value);

    boolean validateIoSpecification(InputOutputSpecification value);

    boolean validateItemDefinition(ItemDefinition value);

    boolean validateLane(Lane value);

    boolean validateLaneSet(LaneSet value);

    boolean validateLinkEventDefinition(LinkEventDefinition value);

    boolean validateLoopCharacteristics(LoopCharacteristics value);

    boolean validateManualTask(ManualTask value);

    boolean validateMessage(Message value);

    boolean validateMessageEventDefinition(MessageEventDefinition value);

    boolean validateMessageFlow(MessageFlow value);

    boolean validateMessageFlowAssociation(MessageFlowAssociation value);

    boolean validateMonitoring(Monitoring value);

    boolean validateMultiInstanceLoopCharacteristics(MultiInstanceLoopCharacteristics value);

    boolean validateOperation(Operation value);

    boolean validateOutputSet(OutputSet value);

    boolean validateParallelGateway(ParallelGateway value);

    boolean validateParticipant(Participant value);

    boolean validateParticipantAssociation(ParticipantAssociation value);

    boolean validateParticipantMultiplicity(ParticipantMultiplicity value);

    boolean validatePartnerEntity(PartnerEntity value);

    boolean validatePartnerRole(PartnerRole value);

    boolean validatePotentialOwner(PotentialOwner value);

    boolean validateProcess(org.eclipse.bpmn2.Process value);

    boolean validateProperty(Property value);

    boolean validateReceiveTask(ReceiveTask value);

    boolean validateRelationship(Relationship value);

    boolean validateRendering(Rendering value);

    boolean validateResource(Resource value);

    boolean validateResourceAssignmentExpression(ResourceAssignmentExpression value);

    boolean validateResourceParameter(ResourceParameter value);

    boolean validateResourceParameterBinding(ResourceParameterBinding value);

    boolean validateScript(Object value);

    boolean validateScriptTask(ScriptTask value);

    boolean validateSendTask(SendTask value);

    boolean validateSequenceFlow(SequenceFlow value);

    boolean validateServiceTask(ServiceTask value);

    boolean validateSignal(Signal value);

    boolean validateSignalEventDefinition(SignalEventDefinition value);

    boolean validateStandardLoopCharacteristics(StandardLoopCharacteristics value);

    boolean validateStartEvent(StartEvent value);

    boolean validateSubChoreography(SubChoreography value);

    boolean validateSubConversation(SubConversation value);

    boolean validateSubProcess(SubProcess value);

    boolean validateTask(Task value);

    boolean validateTerminateEventDefinition(TerminateEventDefinition value);

    boolean validateText(Object value);

    boolean validateTextAnnotation(TextAnnotation value);

    boolean validateThrowEvent(ThrowEvent value);

    boolean validateTimerEventDefinition(TimerEventDefinition value);

    boolean validateTransaction(Transaction value);

    boolean validateUserTask(UserTask value);

    boolean validateType(Object value);

    boolean validateVrSpecification(VariabilitySpecification value);

    boolean validateVarPoint(Object value);

    boolean validateVrProcess(VrProcess value);

    boolean validateTVarPoint(Object value);
}
