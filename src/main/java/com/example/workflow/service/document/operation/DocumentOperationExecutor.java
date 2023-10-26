package com.example.workflow.service.document.operation;

import com.example.workflow.common.CustomUser;
import com.example.workflow.service.document.model.DocOperationType;

import java.util.UUID;

public interface DocumentOperationExecutor<T> {

    void accept(UUID documentId, T operation, CustomUser user);
    T convert(Object body);
    DocOperationType getType();
}
