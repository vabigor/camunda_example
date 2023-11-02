package com.example.workflow.service.bp_events.create;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class UserTaskCreateEventException implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        throw new BpmnError("error");
    }
}
