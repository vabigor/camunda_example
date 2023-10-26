package com.example.workflow.service.bp_events.end;

import com.example.workflow.common.CustomUser;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.entity.Process;
import com.example.workflow.dao.entity.ProcessTask;
import com.example.workflow.dao.service.DocumentServiceDao;
import com.example.workflow.dao.service.ProcessServiceDao;
import com.example.workflow.dao.service.ProcessTaskServiceDao;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Component
public class UserTaskEnd implements JavaDelegate {

    private final DocumentServiceDao documentServiceDao;
    private final ProcessServiceDao processServiceDao;
    private final ProcessTaskServiceDao processTaskServiceDao;

    @Autowired
    public UserTaskEnd(DocumentServiceDao documentServiceDao,
                       ProcessServiceDao processServiceDao,
                       ProcessTaskServiceDao processTaskServiceDao) {
        this.documentServiceDao = documentServiceDao;
        this.processServiceDao = processServiceDao;
        this.processTaskServiceDao = processTaskServiceDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(DelegateExecution execution) throws Exception {
        UUID documentId = (UUID) execution.getVariables().get("documentId");
        CustomUser user = (CustomUser) execution.getVariables().get("user");
        Document document = documentServiceDao.findById(documentId, user).orElseThrow();
//        document.setStatus(DocumentStatus.findByValue(BpmnUtils.extensionProperties(execution).get("status")));
        Process process = processServiceDao.findById(document.getProcessId(), user).orElseThrow();
        ProcessTask processTask = processTaskServiceDao.findById(process.getCurrentTaskId(), user).orElseThrow();
        processTask.setEnd(Instant.now());
        System.out.println("userTask end");
    }

}
