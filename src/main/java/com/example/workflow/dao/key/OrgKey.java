package com.example.workflow.dao.key;

import java.io.Serializable;
import java.util.UUID;

public class OrgKey implements Serializable {

    private UUID id;
    private String organizationId;

    public OrgKey() {
    }

    public OrgKey(UUID id, String organizationId) {
        this.id = id;
        this.organizationId = organizationId;
    }

    public UUID getId() {
        return id;
    }

    public OrgKey setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public OrgKey setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }
}
