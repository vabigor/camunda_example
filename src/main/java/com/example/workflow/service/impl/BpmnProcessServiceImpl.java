package com.example.workflow.service.impl;

import camundajar.impl.com.google.gson.InstanceCreator;
import com.example.workflow.service.BpmnProcessService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * act_hi_procinst - Сам процесс
 * act_hi_actinst - Активный этап
 * act_hi_taskinst
 */
@Service
public class BpmnProcessServiceImpl implements BpmnProcessService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final ProcessEngine processEngine;

    @Autowired
    public BpmnProcessServiceImpl(RuntimeService runtimeService,
                                  TaskService taskService,
                                  ProcessEngine processEngine) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.processEngine = processEngine;
    }

    @Override
    public void upload(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException, JAXBException {

        BpmnModelInstance bpmnModelInstance = Bpmn.readModelFromStream(request.getPart("bp").getInputStream());

        bpmnModelInstance.getModelElementsByType(Process.class).stream().toList().forEach(p->p.setId("org114_"+UUID.randomUUID()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Bpmn.writeModelToStream(outputStream, bpmnModelInstance);

        RepositoryService repositoryService = processEngine.getRepositoryService();

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().enableDuplicateFiltering(true).name("self").tenantId(null);
        deploymentBuilder.addInputStream(request.getPart("bp").getSubmittedFileName(), new ByteArrayInputStream(outputStream.toByteArray()));
        Deployment deploy = deploymentBuilder.deploy();

//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().enableDuplicateFiltering(true).name("self").tenantId(null);
//        deploymentBuilder.addInputStream(request.getPart("bp").getSubmittedFileName(), new ByteArrayInputStream(request.getPart("bp").getInputStream().readAllBytes()));
//        Deployment deploy = deploymentBuilder.deploy();
    }

    @Override
    public ProcessDefinition findByDefId(String defId) {
        return processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(defId).withoutTenantId().latestVersion().singleResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String start(String key) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        return processInstance.getProcessInstanceId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String start(String key, Map<String, Object> params) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, params);
        return processInstance.getProcessInstanceId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(String instanceId) {
        Task reviewTask = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        taskService.complete(reviewTask.getId());
    }

    @Override
    public void execute(String instanceId, Map<String, Object> params) {
        Task reviewTask = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        taskService.complete(reviewTask.getId(), params);
    }

    @Override
    public void messageStart(String key) {
        runtimeService.createMessageCorrelation("testMessageStart").processInstanceBusinessKey(key).correlate();
    }

    @Override
    public void message(String key, String msg, Map<String, Object> params) {
        runtimeService.createMessageCorrelation(msg).processInstanceId(key).setVariables(params).correlate();
    }

    @Override
    public Task getCurrentTask(String instanceId) {
        return taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
    }

    @Override
    public void terminate(String key) {
        runtimeService.deleteProcessInstance(key, "Документ аннулирован");
    }

    @Override
    public List<ProcessDefinitionDto> getAll(Pageable pageable) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> list =  repositoryService.createProcessDefinitionQuery().active().listPage(pageable.getPageNumber(), pageable.getPageSize());
        List<ProcessDefinitionDto> result = new ArrayList<>(list.size());
        list.forEach(l->result.add(ProcessDefinitionDto.fromProcessDefinition(l)));
        return result;
    }

    @Override
    public ProcessDefinitionDto get(String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = Optional.of(repositoryService.createProcessDefinitionQuery().processDefinitionKey(id).singleResult()).orElseThrow();
        return ProcessDefinitionDto.fromProcessDefinition(processDefinition);
    }

    @Override
    public List<DeploymentDto> getAllDeployment(Pageable pageable) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<Deployment> deployments = repositoryService.createDeploymentQuery().listPage(pageable.getPageNumber(), pageable.getPageSize());
        List<DeploymentDto> result = new ArrayList<>(deployments.size());
        deployments.forEach(d->result.add(DeploymentDto.fromDeployment(d)));
        return result;
    }

    @Override
    public DeploymentDto getDeployment(String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = Optional.of(repositoryService.createDeploymentQuery().deploymentId(id).singleResult()).orElseThrow();
        return DeploymentDto.fromDeployment(deployment);
    }

    @Override
    public InputStreamResource getResource(String id, String name) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        return new InputStreamResource(repositoryService.getResourceAsStream(id, name));
    }

    @Override
    public void suspend(String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.suspendProcessDefinitionById(id);
    }

    @Override
    public void activate(String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.activateProcessDefinitionById(id);
    }

    @Override
    public void deleteDeployment(String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment(id, true);
    }

    private Map<String, Object> paramFill(){
        Map<String, Object> params = new HashMap<>();
//        params.put("document", dto);
//        params.put("resetStatus", resetStatus);
        return params;
    }
}
