package com.example.workflow.common;

import java.io.Serializable;
import java.util.UUID;

public class CustomUser implements Serializable {

    private UUID id;
    private String organizationId;

    public CustomUser() {
    }

    public CustomUser(UUID id, String organizationId) {
        this.id = id;
        this.organizationId = organizationId;
    }

    public UUID getId() {
        return id;
    }

    public CustomUser setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public CustomUser setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public static CustomUser getUser(){
        return new CustomUser(UUID.fromString("c2b0ea2e-332c-4cb9-b102-cbf1cbe5312b"), "1");
    }
}
