package com.example.workflow.service.document.impl;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.filter.DocumentFilter;
import com.example.workflow.controller.request.DocOperation;
import com.example.workflow.controller.request.DocumentProcessResult;
import com.example.workflow.controller.request.DocumentRequest;
import com.example.workflow.controller.request.ProcessExecuteRequest;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.entity.Process;
import com.example.workflow.dao.entity.ProcessTask;
import com.example.workflow.dao.model.DocumentStatus;
import com.example.workflow.dao.service.DocumentServiceDao;
import com.example.workflow.dao.service.ProcessServiceDao;
import com.example.workflow.dao.service.ProcessTaskServiceDao;
import com.example.workflow.model.ProcessModel;
import com.example.workflow.service.BpmnProcessService;
import com.example.workflow.service.document.DocumentService;
import com.example.workflow.service.document.model.DocOperationType;
import com.example.workflow.service.document.operation.DocumentOperationExecutor;
import com.example.workflow.service.document.process.ProcessExecute;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentServiceDao documentServiceDao;
    private final BpmnProcessService bpmnProcessService;
    private final ProcessServiceDao processServiceDao;
    private final ProcessTaskServiceDao processTaskServiceDao;
    private final Map<DocOperationType, DocumentOperationExecutor> executors;
    private final Map<DocumentStatus, ProcessExecute> processExecutors;

    public DocumentServiceImpl(DocumentServiceDao documentServiceDao,
                               List<DocumentOperationExecutor> executorList,
                               List<ProcessExecute> processExecutorList,
                               BpmnProcessService bpmnProcessService,
                               ProcessServiceDao processServiceDao,
                               ProcessTaskServiceDao processTaskServiceDao) {
        this.documentServiceDao = documentServiceDao;
        this.bpmnProcessService = bpmnProcessService;
        this.processServiceDao = processServiceDao;
        this.processTaskServiceDao = processTaskServiceDao;
        this.executors = new EnumMap<>(DocOperationType.class);
        for (DocumentOperationExecutor item : executorList) {
            executors.put(item.getType(),item);
        }
        this.processExecutors = new EnumMap<>(DocumentStatus.class);
        for (ProcessExecute item : processExecutorList) {
            processExecutors.put(item.getType(),item);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Document> findAll(DocumentFilter filter, CustomUser user) {
        return documentServiceDao.findAll(filter, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Document findById(UUID id, CustomUser user) {
        return documentServiceDao.findById(id, user).orElseThrow();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Document save(DocumentRequest request, CustomUser user) {
        ProcessDefinition processDefinition = bpmnProcessService.findByDefId(request.getProcessDefId());
        if (processDefinition==null){
            throw new RuntimeException();
        }

        Document document = new Document();
        document.setOrganizationId(user.getOrganizationId());
        document.setStatus(DocumentStatus.DRAFT);
        document.setProcessDefId(request.getProcessDefId());
        document = documentServiceDao.save(document, user);

        ProcessModel processModel = new ProcessModel();
        processModel.setProcessDefId(processDefinition.getKey());
        processModel.setDocumentId(document.getId());
        processModel.setDocumentOrgId(user.getOrganizationId());
        processModel.setStart(Instant.now());
        Process process = processServiceDao.save(processModel, user);
        document.setProcessId(process.getId());
        document.setProcessOrganizationId(user.getOrganizationId());

        String processInstanceId = bpmnProcessService.start(document.getProcessDefId(), Map.of("documentId", document.getId(), "user", user));
        process.setProcessInstanceId(processInstanceId);

        return document;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UUID documentId, List<DocOperation> operations, CustomUser user) {
        for (DocOperation operation : operations){
            Object o = executors.get(operation.getOperationType()).convert(operation.getBody());
            executors.get(operation.getOperationType()).accept(documentId, o, user);
        }
    }

    @Override
    public void delete(UUID id, CustomUser user) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(UUID id, ProcessExecuteRequest request, CustomUser user) {
        Process process = processServiceDao.findByDocumentId(id, user.getOrganizationId());
        DocumentProcessResult result = new DocumentProcessResult();
        result.setResult(request.isResult());
        result.setNext(request.isNext());
        ProcessTask processTask = processTaskServiceDao.findById(process.getCurrentTaskId(), user).orElseThrow();
        processExecutors.get(processTask.getType()).execute(id, result, user);
    }

    @Override
    public void next(UUID id, ProcessExecuteRequest request, CustomUser user) {
        Document document = documentServiceDao.findById(id, user).orElseThrow();
        document.setTaskId(null);
        Process process = processServiceDao.findByDocumentId(id, user.getOrganizationId());
        bpmnProcessService.execute(process.getProcessInstanceId());
    }

    @Override
    public void terminate(UUID id, CustomUser user) {
        Document document = documentServiceDao.findById(id, user).orElseThrow();
        document.setStatus(DocumentStatus.CANCEL);
        Process process = processServiceDao.findByDocumentId(id, user.getOrganizationId());
        bpmnProcessService.terminate(process.getProcessInstanceId());
    }
}
