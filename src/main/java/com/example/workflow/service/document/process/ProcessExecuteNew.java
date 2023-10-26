package com.example.workflow.service.document.process;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.request.DocumentProcessResult;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.entity.Process;
import com.example.workflow.dao.model.DocumentStatus;
import com.example.workflow.dao.service.DocumentServiceDao;
import com.example.workflow.dao.service.ProcessServiceDao;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class ProcessExecuteNew implements ProcessExecute{

    private final TaskService taskService;
    private final DocumentServiceDao documentServiceDao;
    private final ProcessServiceDao processServiceDao;

    @Autowired
    public ProcessExecuteNew(TaskService taskService, DocumentServiceDao documentServiceDao, ProcessServiceDao processServiceDao) {
        this.taskService = taskService;
        this.documentServiceDao = documentServiceDao;
        this.processServiceDao = processServiceDao;
    }

    @Override
    public void execute(UUID docId, DocumentProcessResult result, CustomUser user) {
        Document document = documentServiceDao.findById(docId, user).orElseThrow();
        Process process = processServiceDao.findById(document.getProcessId(), user).orElseThrow();
        Task task = taskService.createTaskQuery().processInstanceId(process.getProcessInstanceId()).taskId(document.getTaskId()).singleResult();
        taskService.complete(task.getId(), Map.of("lastStepResult", result.isResult()));
        System.out.println("New execute");
    }

    @Override
    public boolean validate(DelegateTask delegateTask) {
        return true;
    }

    @Override
    public DocumentStatus getType() {
        return DocumentStatus.NEW;
    }
}
