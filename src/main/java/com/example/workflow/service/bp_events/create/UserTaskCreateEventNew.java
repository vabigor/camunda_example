package com.example.workflow.service.bp_events.create;

import com.example.workflow.common.CustomUser;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.entity.Process;
import com.example.workflow.dao.entity.ProcessTask;
import com.example.workflow.dao.model.DocumentStatus;
import com.example.workflow.dao.service.DocumentServiceDao;
import com.example.workflow.dao.service.ProcessServiceDao;
import com.example.workflow.dao.service.ProcessTaskServiceDao;
import com.example.workflow.model.ProcessTaskModel;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Component
public class UserTaskCreateEventNew implements TaskListener {

    private final DocumentServiceDao documentServiceDao;
    private final ProcessServiceDao processServiceDao;
    private final ProcessTaskServiceDao processTaskServiceDao;
    private final TaskService taskService;

    @Autowired
    public UserTaskCreateEventNew(DocumentServiceDao documentServiceDao,
                                  ProcessServiceDao processServiceDao,
                                  ProcessTaskServiceDao processTaskServiceDao, TaskService taskService) {
        this.documentServiceDao = documentServiceDao;
        this.processServiceDao = processServiceDao;
        this.processTaskServiceDao = processTaskServiceDao;
        this.taskService = taskService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void notify(DelegateTask delegateTask) {
        UUID documentId = (UUID) delegateTask.getVariables().get("documentId");
        CustomUser user = (CustomUser) delegateTask.getVariables().get("user");
        Document document = documentServiceDao.findById(documentId, user).orElseThrow();
        document.setStatus(DocumentStatus.NEW);
        document.setTaskId(delegateTask.getId());

        ProcessTaskModel processTaskModel = new ProcessTaskModel();
        processTaskModel.setStart(Instant.now());
        processTaskModel.setProcessDefId(delegateTask.getProcessDefinitionId());
        processTaskModel.setType(DocumentStatus.NEW);
        processTaskModel.setDocumentId(document.getId());
        processTaskModel.setDocumentOrgId(document.getOrganizationId());
        ProcessTask processTask = processTaskServiceDao.save(processTaskModel, user);

        Process process = processServiceDao.findByDocumentId(documentId, user.getOrganizationId());
        UUID prevTaskId = process.getCurrentTaskId();
        String prevTaskOrganizationId = process.getCurrentTaskOrganizationId();
        process.setCurrentTaskId(processTask.getId());
        process.setCurrentTaskOrganizationId(processTask.getOrganizationId());
        process.setPrevTaskId(prevTaskId);
        process.setPrevTaskOrganizationId(prevTaskOrganizationId);
//        taskService.complete(delegateTask.getId());
        System.out.println("userTask create NEW");
    }

}
