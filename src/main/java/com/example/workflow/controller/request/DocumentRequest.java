package com.example.workflow.controller.request;

import java.util.UUID;

public class DocumentRequest {

    private UUID id;
    private String subject;
    private String processDefId;

    public DocumentRequest() {
    }

    public UUID getId() {
        return id;
    }

    public DocumentRequest setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public DocumentRequest setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public DocumentRequest setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
        return this;
    }
}
