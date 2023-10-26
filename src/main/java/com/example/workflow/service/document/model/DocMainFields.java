package com.example.workflow.service.document.model;

public class DocMainFields {

    private String subject;
    private Object delegateModel;

    public DocMainFields() {
    }

    public String getSubject() {
        return subject;
    }

    public DocMainFields setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Object getDelegateModel() {
        return delegateModel;
    }

    public DocMainFields setDelegateModel(Object delegateModel) {
        this.delegateModel = delegateModel;
        return this;
    }
}
