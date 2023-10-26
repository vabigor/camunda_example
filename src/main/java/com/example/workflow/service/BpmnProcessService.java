package com.example.workflow.service;

import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.camunda.bpm.engine.task.Task;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BpmnProcessService {

    void upload(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException, JAXBException;
    ProcessDefinition findByDefId(String defId);
    String start(String key);
    String start(String key, Map<String, Object> params);
    void execute(String instanceId);
    void execute(String instanceId, Map<String, Object> params);
    void messageStart(String key);
    void message(String key, String msg, Map<String, Object> params);
    Task getCurrentTask(String instanceId);
    void terminate(String key);
    List<ProcessDefinitionDto> getAll(Pageable pageable);
    ProcessDefinitionDto get(String id);
    List<DeploymentDto> getAllDeployment(Pageable pageable);
    DeploymentDto getDeployment(String id);
    InputStreamResource getResource(String id, String name);
    void suspend(String id);
    void activate(String id);
    void deleteDeployment(String id);
}
