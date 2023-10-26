package com.example.workflow.service.bp_events.end;

import com.example.workflow.common.CustomUser;
import com.example.workflow.dao.entity.Process;
import com.example.workflow.dao.service.ProcessServiceDao;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Component
public class ProcessDefEnd implements JavaDelegate {

    private final ProcessServiceDao processServiceDao;

    @Autowired
    public ProcessDefEnd(ProcessServiceDao processServiceDao) {
        this.processServiceDao = processServiceDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(DelegateExecution execution) throws Exception {
        UUID documentId = (UUID) execution.getVariables().get("documentId");
        CustomUser user = (CustomUser) execution.getVariables().get("user");
        Process process = processServiceDao.findByDocumentId(documentId, user.getOrganizationId());
        process.setProcessInstanceId(execution.getProcessInstanceId());
        process.setEnd(Instant.now());
    }

}
