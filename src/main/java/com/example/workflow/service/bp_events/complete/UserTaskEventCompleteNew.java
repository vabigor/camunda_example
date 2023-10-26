package com.example.workflow.service.bp_events.complete;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class UserTaskEventCompleteNew implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setVariable("x", false);
        System.out.println("userTask new execute");
    }
}
