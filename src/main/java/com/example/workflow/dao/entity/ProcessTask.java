package com.example.workflow.dao.entity;

import com.example.workflow.dao.key.OrgKey;
import com.example.workflow.dao.model.DocumentStatus;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@IdClass(value = OrgKey.class)
public class ProcessTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;
    @Id
    @Column(name = "organization_id")
    private String organizationId;
    @Enumerated(EnumType.STRING)
    private DocumentStatus type;
    private String processDefId;
    private Instant start;
    @Column(name = "ended")
    private Instant end;
    private int executionCount;
    private int executeCount;
    @Column(name = "document_id")
    private UUID documentId;
    @Column(name = "document_organization_id")
    private String documentOrgId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "document_id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "document_organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false)})
    private Document document;

    public ProcessTask() {
    }

    public UUID getId() {
        return id;
    }

    public ProcessTask setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public ProcessTask setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public DocumentStatus getType() {
        return type;
    }

    public ProcessTask setType(DocumentStatus type) {
        this.type = type;
        return this;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public ProcessTask setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
        return this;
    }

    public Instant getStart() {
        return start;
    }

    public ProcessTask setStart(Instant start) {
        this.start = start;
        return this;
    }

    public Instant getEnd() {
        return end;
    }

    public ProcessTask setEnd(Instant end) {
        this.end = end;
        return this;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public ProcessTask setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
        return this;
    }

    public int getExecuteCount() {
        return executeCount;
    }

    public ProcessTask setExecuteCount(int executeCount) {
        this.executeCount = executeCount;
        return this;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public ProcessTask setDocumentId(UUID documentId) {
        this.documentId = documentId;
        return this;
    }

    public String getDocumentOrgId() {
        return documentOrgId;
    }

    public ProcessTask setDocumentOrgId(String documentOrgId) {
        this.documentOrgId = documentOrgId;
        return this;
    }

    public Document getDocument() {
        return document;
    }

    public ProcessTask setDocument(Document document) {
        this.document = document;
        return this;
    }
}
