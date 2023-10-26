package com.example.workflow.dao.entity;

import com.example.workflow.dao.key.OrgKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@IdClass(value = OrgKey.class)
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;
    @Id
    @Column(name = "organization_id")
    private String organizationId;
    private String processDefId;
    private String processInstanceId;
    @Column(name = "current_task_id")
    private UUID currentTaskId;
    @Column(name = "current_task_organization_id")
    private String currentTaskOrganizationId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "current_task_id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "current_task_organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false)})
    private ProcessTask currentTask;
    @Column(name = "prev_task_id")
    private UUID prevTaskId;
    @Column(name = "prev_task_organization_id")
    private String prevTaskOrganizationId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "prev_task_id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "prev_task_organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false)})
    private ProcessTask prevTask;
    private Instant start;
    @Column(name = "ended")
    private Instant end;
    @Column(name = "document_id")
    private UUID documentId;
    @Column(name = "document_organization_id")
    private String documentOrgId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "document_id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "document_organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false)})
    private Document document;

    public Process() {
    }

    public UUID getId() {
        return id;
    }

    public Process setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public Process setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public Process setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
        return this;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public Process setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
        return this;
    }

    public UUID getCurrentTaskId() {
        return currentTaskId;
    }

    public Process setCurrentTaskId(UUID currentTaskId) {
        this.currentTaskId = currentTaskId;
        return this;
    }

    public String getCurrentTaskOrganizationId() {
        return currentTaskOrganizationId;
    }

    public Process setCurrentTaskOrganizationId(String currentTaskOrganizationId) {
        this.currentTaskOrganizationId = currentTaskOrganizationId;
        return this;
    }

    public ProcessTask getCurrentTask() {
        return currentTask;
    }

    public Process setCurrentTask(ProcessTask currentTask) {
        this.currentTask = currentTask;
        return this;
    }

    public UUID getPrevTaskId() {
        return prevTaskId;
    }

    public Process setPrevTaskId(UUID prevTaskId) {
        this.prevTaskId = prevTaskId;
        return this;
    }

    public String getPrevTaskOrganizationId() {
        return prevTaskOrganizationId;
    }

    public Process setPrevTaskOrganizationId(String prevTaskOrganizationId) {
        this.prevTaskOrganizationId = prevTaskOrganizationId;
        return this;
    }

    public ProcessTask getPrevTask() {
        return prevTask;
    }

    public Process setPrevTask(ProcessTask prevTask) {
        this.prevTask = prevTask;
        return this;
    }

    public Instant getStart() {
        return start;
    }

    public Process setStart(Instant start) {
        this.start = start;
        return this;
    }

    public Instant getEnd() {
        return end;
    }

    public Process setEnd(Instant end) {
        this.end = end;
        return this;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public Process setDocumentId(UUID documentId) {
        this.documentId = documentId;
        return this;
    }

    public String getDocumentOrgId() {
        return documentOrgId;
    }

    public Process setDocumentOrgId(String documentOrgId) {
        this.documentOrgId = documentOrgId;
        return this;
    }

    public Document getDocument() {
        return document;
    }

    public Process setDocument(Document document) {
        this.document = document;
        return this;
    }
}
