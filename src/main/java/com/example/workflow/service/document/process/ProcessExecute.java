package com.example.workflow.service.document.process;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.request.DocumentProcessResult;
import com.example.workflow.dao.model.DocumentStatus;
import com.example.workflow.service.document.validate.ProcessValidate;

import java.util.UUID;

public interface ProcessExecute extends ProcessValidate {

    void execute(UUID docId, DocumentProcessResult result, CustomUser user);
    DocumentStatus getType();
}
