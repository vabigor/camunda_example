package com.example.workflow.controller;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.request.DocOperation;
import com.example.workflow.controller.request.DocumentRequest;
import com.example.workflow.controller.request.ProcessExecuteRequest;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/document")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Document> create(@RequestBody DocumentRequest request){
        return ResponseEntity.ok(documentService.save(request, CustomUser.getUser()));
    }

    @PutMapping(value = "/{documentId}")
    public void edit(@PathVariable("documentId") UUID documentId, @RequestBody List<DocOperation> operations){
        documentService.edit(documentId, operations, CustomUser.getUser());
    }

    @PutMapping(value = "/{documentId}/execute")
    public void execute(@PathVariable("documentId") UUID documentId, @RequestBody ProcessExecuteRequest request){
        documentService.execute(documentId, request, CustomUser.getUser());
    }

    @PutMapping(value = "/{documentId}/next")
    public void next(@PathVariable("documentId") UUID documentId, @RequestBody ProcessExecuteRequest request){
        documentService.next(documentId, request, CustomUser.getUser());
    }

    @PutMapping(value = "/{documentId}/terminate")
    public void terminate(@PathVariable("documentId") UUID documentId){
        documentService.terminate(documentId, CustomUser.getUser());
    }
}
