package com.example.workflow.model;

import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.model.DocumentStatus;

import java.time.Instant;
import java.util.UUID;

public class ProcessTaskModel {

    private UUID id;
    private DocumentStatus type;
    private String processDefId;
    private Instant start;
    private Instant end;
    private int executionCount;
    private int executeCount;
    private UUID documentId;
    private String documentOrgId;
    private Document document;

    public ProcessTaskModel() {
    }

    public UUID getId() {
        return id;
    }

    public ProcessTaskModel setId(UUID id) {
        this.id = id;
        return this;
    }

    public DocumentStatus getType() {
        return type;
    }

    public ProcessTaskModel setType(DocumentStatus type) {
        this.type = type;
        return this;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public ProcessTaskModel setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
        return this;
    }

    public Instant getStart() {
        return start;
    }

    public ProcessTaskModel setStart(Instant start) {
        this.start = start;
        return this;
    }

    public Instant getEnd() {
        return end;
    }

    public ProcessTaskModel setEnd(Instant end) {
        this.end = end;
        return this;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public ProcessTaskModel setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
        return this;
    }

    public int getExecuteCount() {
        return executeCount;
    }

    public ProcessTaskModel setExecuteCount(int executeCount) {
        this.executeCount = executeCount;
        return this;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public ProcessTaskModel setDocumentId(UUID documentId) {
        this.documentId = documentId;
        return this;
    }

    public String getDocumentOrgId() {
        return documentOrgId;
    }

    public ProcessTaskModel setDocumentOrgId(String documentOrgId) {
        this.documentOrgId = documentOrgId;
        return this;
    }

    public Document getDocument() {
        return document;
    }

    public ProcessTaskModel setDocument(Document document) {
        this.document = document;
        return this;
    }
}
