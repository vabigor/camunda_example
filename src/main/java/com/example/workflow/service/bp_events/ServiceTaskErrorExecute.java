package com.example.workflow.service.bp_events;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ServiceTaskErrorExecute implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        throw new BpmnError("error2", "msg2");
    }

}
