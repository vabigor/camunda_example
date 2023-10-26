package com.example.workflow.controller;

import com.example.workflow.service.BpmnProcessService;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bp")
public class BusinessProcessController {

    private final BpmnProcessService bpmnProcessService;

    @Autowired
    public BusinessProcessController(BpmnProcessService bpmnProcessService) {
        this.bpmnProcessService = bpmnProcessService;
    }

    @PostMapping(value = "/upload")
    public void upload(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException, JAXBException {
        bpmnProcessService.upload(request, resp);
    }

    @PostMapping(value = "/{key}")
    public String startProcess(@PathVariable("key") String key){
        return bpmnProcessService.start(key);
    }

    @PutMapping(value = "/execute/{instanceId}")
    public void execute(@PathVariable("instanceId") String instanceId){
        bpmnProcessService.execute(instanceId);
    }

    @PutMapping(value = "/message-run/{key}")
    public void messageStart(@PathVariable("key") String instanceId){
        bpmnProcessService.messageStart(instanceId);
    }

    @PutMapping(value = "/terminate/{key}")
    public void terminate(@PathVariable("key") String instanceId){
        bpmnProcessService.terminate(instanceId);
    }

    @GetMapping
    public List<ProcessDefinitionDto> getProcess(Pageable pageable){
        return bpmnProcessService.getAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public ProcessDefinitionDto get(@PathVariable("id") String id){
        return bpmnProcessService.get(id);
    }

    @GetMapping("/deployment")
    public List<DeploymentDto> getDeployments(Pageable pageable){
        return bpmnProcessService.getAllDeployment(pageable);
    }

    @GetMapping("/deployment/{id}")
    public DeploymentDto getDeployments(@PathVariable("id") String deploymentId){
        return bpmnProcessService.getDeployment(deploymentId);
    }

    @GetMapping(value = "/deployment/{id}/resource/{name}", produces = "multipart/form-data")
    public ResponseEntity<InputStreamResource> getResource(@PathVariable("id") String id, @PathVariable("name") String name){
        return ResponseEntity.ok(bpmnProcessService.getResource(id, name));
    }

    @DeleteMapping(value = "/{id}")
    public void suspend(@PathVariable("id") String id){
        bpmnProcessService.suspend(id);
    }

    @PutMapping(value = "/{id}")
    public void activate(@PathVariable("id") String id){
        bpmnProcessService.activate(id);
    }

    @DeleteMapping(value = "/deployment/{id}")
    public void deleteDeployment(@PathVariable("id") String id){
        bpmnProcessService.deleteDeployment(id);
    }

}
