package com.example.workflow.service.bp_events.create;

import com.example.workflow.common.CustomUser;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.service.DocumentServiceDao;
import com.example.workflow.dao.service.ProcessServiceDao;
import com.example.workflow.dao.service.ProcessTaskServiceDao;
import com.example.workflow.service.document.process.ProcessExecuteAgreement;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserTaskCreateEventValidateAgreement implements TaskListener {

    private final DocumentServiceDao documentServiceDao;
    private final ProcessExecuteAgreement processExecuteAgreement;

    @Autowired
    public UserTaskCreateEventValidateAgreement(DocumentServiceDao documentServiceDao,
                                                ProcessExecuteAgreement processExecuteAgreement) {
        this.documentServiceDao = documentServiceDao;
        this.processExecuteAgreement = processExecuteAgreement;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        UUID documentId = (UUID) delegateTask.getVariables().get("documentId");
        CustomUser user = (CustomUser) delegateTask.getVariables().get("user");
        Document document = documentServiceDao.findById(documentId, user).orElseThrow();
        if(processExecuteAgreement.validate(delegateTask)){
            delegateTask.complete();
        }else{
            document.setTaskId(delegateTask.getId());
        }
    }

}
