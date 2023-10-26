package com.example.workflow.service.document.validate;

import org.camunda.bpm.engine.delegate.DelegateTask;

public interface ProcessValidate {

    boolean validate(DelegateTask delegateTask);

}
