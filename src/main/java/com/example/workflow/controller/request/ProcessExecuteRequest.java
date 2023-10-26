package com.example.workflow.controller.request;

public class ProcessExecuteRequest {

    private boolean result;
    private boolean next;
    private boolean valid;

    public ProcessExecuteRequest() {
    }

    public boolean isResult() {
        return result;
    }

    public ProcessExecuteRequest setResult(boolean result) {
        this.result = result;
        return this;
    }

    public boolean isNext() {
        return next;
    }

    public ProcessExecuteRequest setNext(boolean next) {
        this.next = next;
        return this;
    }

    public boolean isValid() {
        return valid;
    }

    public ProcessExecuteRequest setValid(boolean valid) {
        this.valid = valid;
        return this;
    }
}
