package com.example.workflow.controller.request;

import com.example.workflow.service.document.model.DocOperationType;

import javax.validation.constraints.NotNull;

public class DocOperation {

    @NotNull
    private DocOperationType operationType;

    private Object body;

    public DocOperation() {
    }

    public DocOperationType getOperationType() {
        return operationType;
    }

    public DocOperation setOperationType(DocOperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    public Object getBody() {
        return body;
    }

    public DocOperation setBody(Object body) {
        this.body = body;
        return this;
    }
}
