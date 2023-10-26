package com.example.workflow.dao.entity;

import com.example.workflow.dao.key.OrgKey;
import com.example.workflow.dao.model.DocumentStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@IdClass(value = OrgKey.class)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;
    @Id
    @Column(name = "organization_id")
    private String organizationId;
    private String subject;
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;
    private String processDefId;
    @Column(name = "process_id")
    private UUID processId;
    @Column(name = "process_organization_id")
    private String processOrganizationId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "process_id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "process_organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false)})
    private Process process;
    private String taskId;

    public Document() {
    }

    public UUID getId() {
        return id;
    }

    public Document setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public Document setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Document setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public Document setStatus(DocumentStatus status) {
        this.status = status;
        return this;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public Document setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
        return this;
    }

    public UUID getProcessId() {
        return processId;
    }

    public Document setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    public String getProcessOrganizationId() {
        return processOrganizationId;
    }

    public Document setProcessOrganizationId(String processOrganizationId) {
        this.processOrganizationId = processOrganizationId;
        return this;
    }

    public Process getProcess() {
        return process;
    }

    public Document setProcess(Process process) {
        this.process = process;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public Document setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }
}
