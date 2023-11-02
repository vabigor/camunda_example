package com.example.workflow;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.xml.Model;
import org.camunda.bpm.model.xml.instance.DomDocument;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.camunda.bpm.model.xml.validation.ModelElementValidator;
import org.camunda.bpm.model.xml.validation.ValidationResults;

import java.util.Collection;

public class ModifiableBpmnModelInstance implements BpmnModelInstance {

    private BpmnModelInstance modelInstance;

    public ModifiableBpmnModelInstance(BpmnModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }

    @Override
    public Definitions getDefinitions() {
        return null;
    }

    @Override
    public void setDefinitions(Definitions definitions) {
        modelInstance.setDefinitions(definitions);
    }

    @Override
    public BpmnModelInstance clone() {
        return modelInstance.clone();
    }

    @Override
    public DomDocument getDocument() {
        return null;
    }

    @Override
    public ModelElementInstance getDocumentElement() {
        return null;
    }

    @Override
    public void setDocumentElement(ModelElementInstance modelElementInstance) {

    }

    @Override
    public <T extends ModelElementInstance> T newInstance(Class<T> aClass) {
        return null;
    }

    @Override
    public <T extends ModelElementInstance> T newInstance(Class<T> aClass, String s) {
        return null;
    }

    @Override
    public <T extends ModelElementInstance> T newInstance(ModelElementType modelElementType) {
        return null;
    }

    @Override
    public <T extends ModelElementInstance> T newInstance(ModelElementType modelElementType, String s) {
        return null;
    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public <T extends ModelElementInstance> T getModelElementById(String s) {
        return null;
    }

    @Override
    public Collection<ModelElementInstance> getModelElementsByType(ModelElementType modelElementType) {
        return null;
    }

    @Override
    public <T extends ModelElementInstance> Collection<T> getModelElementsByType(Class<T> aClass) {
        return null;
    }

    @Override
    public ValidationResults validate(Collection<ModelElementValidator<?>> collection) {
        return null;
    }
}
