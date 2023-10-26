package com.example.workflow.model;

import com.example.workflow.dao.model.DocumentStatus;

import java.util.UUID;

public class DocumentModel {

    private UUID id;
    private String organizationId;
    private String subject;
    private DocumentStatus status;
    private String processDefId;
    private UUID processId;

    public DocumentModel() {
    }

    public UUID getId() {
        return id;
    }

    public DocumentModel setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public DocumentModel setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public DocumentModel setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public DocumentModel setStatus(DocumentStatus status) {
        this.status = status;
        return this;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public DocumentModel setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
        return this;
    }

    public UUID getProcessId() {
        return processId;
    }

    public DocumentModel setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }
}
