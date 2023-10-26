package com.example.workflow.utils;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BpmnUtils {

    public static Map<String, String>  extensionProperties(DelegateExecution delegateExecution){
        UserTask userTask = (UserTask) delegateExecution.getBpmnModelElementInstance();
        return extensionProperties(userTask);
    }

    public static Map<String, String>  extensionProperties(DelegateTask delegateTask){
        UserTask userTask = delegateTask.getBpmnModelElementInstance();
        return extensionProperties(userTask);
    }

    public static Map<String, String>  extensionProperties(UserTask userTask){
        ExtensionElements extensionElements = userTask.getExtensionElements();
        Collection<CamundaProperty> properties = extensionElements.getElementsQuery()
                .filterByType(CamundaProperties.class)
                .singleResult()
                .getCamundaProperties();
        Map<String, String> map = new HashMap<>(properties.size());
        for (CamundaProperty property : properties) {
            map.put(property.getCamundaName(), property.getCamundaValue());
        }
        return map;
    }
}
