package com.example.workflow.service.document;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.filter.DocumentFilter;
import com.example.workflow.controller.request.DocOperation;
import com.example.workflow.controller.request.DocumentRequest;
import com.example.workflow.controller.request.ProcessExecuteRequest;
import com.example.workflow.dao.entity.Document;

import java.util.List;
import java.util.UUID;

public interface DocumentService {

    List<Document> findAll(DocumentFilter filter, CustomUser user);
    Document findById(UUID id, CustomUser user);
    Document save(DocumentRequest request, CustomUser user);
    void edit(UUID documentId, List<DocOperation> operations, CustomUser user);
    void delete(UUID id, CustomUser user);
    void execute(UUID id, ProcessExecuteRequest request, CustomUser user);
    void next(UUID id, ProcessExecuteRequest request, CustomUser user);
    void terminate(UUID id, CustomUser user);
}
