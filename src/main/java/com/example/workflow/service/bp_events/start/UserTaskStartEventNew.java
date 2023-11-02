package com.example.workflow.service.bp_events.start;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserTaskStartEventNew implements JavaDelegate {

    private final RuntimeService runtimeService;
    private final ProcessEngine processEngine;

    @Autowired
    public UserTaskStartEventNew(RuntimeService runtimeService, ProcessEngine processEngine) {
        this.runtimeService = runtimeService;
        this.processEngine = processEngine;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(DelegateExecution execution) throws Exception {
        UserTask userTask = (UserTask) execution.getBpmnModelElementInstance();
        System.out.println("userTask start NEW");
    }

}
