package com.example.workflow.service.bp_events;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageThrowEvent implements JavaDelegate {

    private final RuntimeService runtimeService;

    @Autowired
    public MessageThrowEvent(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
//        runtimeService.createMessageCorrelation("testMessage")
//                .processInstanceBusinessKey("camunda-process")
//                .setVariable("111", "333")
//                .correlate();
        System.out.println("message throw event");
    }
}
