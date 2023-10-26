package com.example.workflow.service.bp_events;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class UserTaskErrorExecute implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        throw new BpmnError("error");
//        UserTask userTask = delegateTask.getBpmnModelElementInstance();
//        Collection<BoundaryEvent> boundaryEvents = userTask.getModelInstance().getModelElementsByType(BoundaryEvent.class);
//        if (!CollectionUtils.isEmpty(boundaryEvents)) {
//            boundaryEvents.forEach(boundaryEvent -> {
//                if (boundaryEvent.getAttachedTo().getName().equals(userTask.getName())) {
//                    boundaryEvent.getEventDefinitions().forEach(events -> {
//                        if (events instanceof ErrorEventDefinition) {
//                            throw new BpmnError("Error");
//                        }
//                    });
//                }
//            });
//        }
    }
}
