package com.example.workflow.service.document.operation;

import com.example.workflow.common.CustomUser;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.service.DocumentServiceDao;
import com.example.workflow.service.document.model.DocMainFields;
import com.example.workflow.service.document.model.DocOperationType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class DocumentMainFieldsOperationExecutor implements DocumentOperationExecutor<DocMainFields>{

    private final DocumentServiceDao documentServiceDao;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public DocumentMainFieldsOperationExecutor(DocumentServiceDao documentServiceDao) {
        this.documentServiceDao = documentServiceDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void accept(UUID documentId, DocMainFields operation, CustomUser user) {
        Document document = documentServiceDao.findById(documentId, user).orElseThrow();
        document.setSubject(operation.getSubject());
    }

    @Override
    public DocMainFields convert(Object body) {
        return mapper.convertValue(body, DocMainFields.class);
    }

    @Override
    public DocOperationType getType() {
        return DocOperationType.DOCUMENT_MAIN_FIELDS_UPDATE;
    }
}
