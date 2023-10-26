package com.example.workflow.service.document.operation;

import com.example.workflow.common.CustomUser;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.model.DocumentStatus;
import com.example.workflow.dao.service.DocumentServiceDao;
import com.example.workflow.service.BpmnProcessService;
import com.example.workflow.service.document.model.DocOperationType;
import com.example.workflow.service.document.model.DocumentUndraft;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Component
public class DocumentUndraftOperationExecutor implements DocumentOperationExecutor<DocumentUndraft>{

    private final DocumentServiceDao documentServiceDao;
    private final BpmnProcessService bpmnProcessService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public DocumentUndraftOperationExecutor(DocumentServiceDao documentServiceDao,
                                            BpmnProcessService bpmnProcessService) {
        this.documentServiceDao = documentServiceDao;
        this.bpmnProcessService = bpmnProcessService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void accept(UUID documentId, DocumentUndraft operation, CustomUser user) {
//        Document document = documentServiceDao.findById(documentId, user).orElseThrow();
//        document.setStatus(DocumentStatus.NEW);
//        bpmnProcessService.start(document.getProcessDefId(), Map.of("documentId", document.getId(), "user", user));
    }

    @Override
    public DocumentUndraft convert(Object body) {
        return mapper.convertValue(body, DocumentUndraft.class);
    }

    @Override
    public DocOperationType getType() {
        return DocOperationType.DOCUMENT_UNDRAFT;
    }
}
