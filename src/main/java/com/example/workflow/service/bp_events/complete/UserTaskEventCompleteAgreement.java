package com.example.workflow.service.bp_events.complete;

import com.example.workflow.common.CustomUser;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.model.DocumentStatus;
import com.example.workflow.dao.service.DocumentServiceDao;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserTaskEventCompleteAgreement implements TaskListener {

    private final DocumentServiceDao documentServiceDao;

    @Autowired
    public UserTaskEventCompleteAgreement(DocumentServiceDao documentServiceDao) {
        this.documentServiceDao = documentServiceDao;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        UUID documentId = (UUID) delegateTask.getVariables().get("documentId");
        CustomUser user = (CustomUser) delegateTask.getVariables().get("user");
        Document document = documentServiceDao.findById(documentId, user).orElseThrow();
        document.setStatus(DocumentStatus.AGREED);
        System.out.println("userTask agreement execute");
    }
}
