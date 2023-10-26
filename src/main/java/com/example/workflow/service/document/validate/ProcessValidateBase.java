package com.example.workflow.service.document.validate;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.springframework.stereotype.Component;

@Component
public class ProcessValidateBase implements ProcessValidate{

    @Override
    public boolean validate(DelegateTask delegateTask) {
        return true;
    }
}
