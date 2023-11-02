package com.example.workflow.service.bp_events;

import com.example.workflow.common.CustomUser;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.model.DocumentStatus;
import com.example.workflow.dao.service.DocumentServiceDao;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ServiceTaskSimpleExecute implements JavaDelegate {

    private final DocumentServiceDao documentServiceDao;

    @Autowired
    public ServiceTaskSimpleExecute(DocumentServiceDao documentServiceDao) {
        this.documentServiceDao = documentServiceDao;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        UUID documentId = (UUID) delegateExecution.getVariables().get("documentId");
        CustomUser user = (CustomUser) delegateExecution.getVariables().get("user");
        Document document = documentServiceDao.findById(documentId, user).orElseThrow();
        document.setStatus(DocumentStatus.AGREED);
        System.out.println("userTask agreement execute");
    }

}
