package com.example.workflow.service.bp_events.start;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserTaskStartEventNew implements JavaDelegate {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("userTask start NEW");
    }

}
