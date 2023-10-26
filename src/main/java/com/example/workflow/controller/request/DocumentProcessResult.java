package com.example.workflow.controller.request;

import java.util.UUID;

public class DocumentProcessResult {

    private UUID id;
    private boolean result;
    private boolean next;
    private boolean valid;

    public DocumentProcessResult() {
    }

    public UUID getId() {
        return id;
    }

    public DocumentProcessResult setId(UUID id) {
        this.id = id;
        return this;
    }

    public boolean isResult() {
        return result;
    }

    public DocumentProcessResult setResult(boolean result) {
        this.result = result;
        return this;
    }

    public boolean isNext() {
        return next;
    }

    public DocumentProcessResult setNext(boolean next) {
        this.next = next;
        return this;
    }

    public boolean isValid() {
        return valid;
    }

    public DocumentProcessResult setValid(boolean valid) {
        this.valid = valid;
        return this;
    }
}
