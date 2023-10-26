package com.example.workflow.model;

import com.example.workflow.dao.entity.Document;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

public class ProcessModel {

    private UUID id;
    private String processDefId;
    private String processInstanceId;
    private UUID currentTaskId;
    private String currentTaskOrganizationId;
    private UUID prevTaskId;
    private String prevTaskOrganizationId;
    private Instant start;
    private Instant end;
    private UUID documentId;
    private String documentOrgId;
    private Document document;

    public ProcessModel() {
    }

    public UUID getId() {
        return id;
    }

    public ProcessModel setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public ProcessModel setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
        return this;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public ProcessModel setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
        return this;
    }

    public UUID getCurrentTaskId() {
        return currentTaskId;
    }

    public ProcessModel setCurrentTaskId(UUID currentTaskId) {
        this.currentTaskId = currentTaskId;
        return this;
    }

    public String getCurrentTaskOrganizationId() {
        return currentTaskOrganizationId;
    }

    public ProcessModel setCurrentTaskOrganizationId(String currentTaskOrganizationId) {
        this.currentTaskOrganizationId = currentTaskOrganizationId;
        return this;
    }

    public UUID getPrevTaskId() {
        return prevTaskId;
    }

    public ProcessModel setPrevTaskId(UUID prevTaskId) {
        this.prevTaskId = prevTaskId;
        return this;
    }

    public String getPrevTaskOrganizationId() {
        return prevTaskOrganizationId;
    }

    public ProcessModel setPrevTaskOrganizationId(String prevTaskOrganizationId) {
        this.prevTaskOrganizationId = prevTaskOrganizationId;
        return this;
    }

    public Instant getStart() {
        return start;
    }

    public ProcessModel setStart(Instant start) {
        this.start = start;
        return this;
    }

    public Instant getEnd() {
        return end;
    }

    public ProcessModel setEnd(Instant end) {
        this.end = end;
        return this;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public ProcessModel setDocumentId(UUID documentId) {
        this.documentId = documentId;
        return this;
    }

    public String getDocumentOrgId() {
        return documentOrgId;
    }

    public ProcessModel setDocumentOrgId(String documentOrgId) {
        this.documentOrgId = documentOrgId;
        return this;
    }

    public Document getDocument() {
        return document;
    }

    public ProcessModel setDocument(Document document) {
        this.document = document;
        return this;
    }
}
