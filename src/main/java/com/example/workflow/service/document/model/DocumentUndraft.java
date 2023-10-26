package com.example.workflow.service.document.model;

import java.util.UUID;

public class DocumentUndraft {

    private UUID id;

    public DocumentUndraft() {
    }

    public DocumentUndraft(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public DocumentUndraft setId(UUID id) {
        this.id = id;
        return this;
    }
}
