package com.example.workflow.service.document.process;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.request.DocumentProcessResult;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.entity.Process;
import com.example.workflow.dao.entity.ProcessTask;
import com.example.workflow.dao.model.DocumentStatus;
import com.example.workflow.dao.service.DocumentServiceDao;
import com.example.workflow.dao.service.ProcessServiceDao;
import com.example.workflow.dao.service.ProcessTaskServiceDao;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class ProcessExecuteExecution implements ProcessExecute{

    private final TaskService taskService;
    private final DocumentServiceDao documentServiceDao;
    private final ProcessServiceDao processServiceDao;
    private final ProcessTaskServiceDao processTaskServiceDao;

    @Autowired
    public ProcessExecuteExecution(TaskService taskService,
                                   DocumentServiceDao documentServiceDao, ProcessServiceDao processServiceDao,
                                   ProcessTaskServiceDao processTaskServiceDao) {
        this.taskService = taskService;
        this.documentServiceDao = documentServiceDao;
        this.processServiceDao = processServiceDao;
        this.processTaskServiceDao = processTaskServiceDao;
    }

    @Override
    public void execute(UUID docId, DocumentProcessResult result, CustomUser user) {
        Document document = documentServiceDao.findById(docId, user).orElseThrow();
        Process process = processServiceDao.findByDocumentId(docId, user.getOrganizationId());
        ProcessTask processTask = processTaskServiceDao.findById(process.getCurrentTaskId(), user).orElseThrow();
        if (processTask.getExecutionCount()>processTask.getExecuteCount() && result.isResult()){
            processTask.setExecuteCount(processTask.getExecuteCount()+1);
        }
        if (processTask.getExecutionCount()==processTask.getExecuteCount() && result.isNext()){
            Task task = taskService.createTaskQuery().processInstanceId(process.getProcessInstanceId()).taskId(document.getTaskId()).singleResult();
            taskService.complete(task.getId(), Map.of("lastStepResult", result.isResult()));
            System.out.println("Execution execute");
        }
    }

    @Override
    public boolean validate(DelegateTask delegateTask) {
        return true;
    }

    @Override
    public DocumentStatus getType() {
        return DocumentStatus.EXECUTION;
    }
}
