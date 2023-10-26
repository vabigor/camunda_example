package com.example.workflow.dao.service.impl;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.filter.ProcessFilter;
import com.example.workflow.dao.entity.Process;
import com.example.workflow.dao.key.OrgKey;
import com.example.workflow.dao.repository.ProcessRepository;
import com.example.workflow.dao.service.ProcessServiceDao;
import com.example.workflow.model.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProcessServiceDaoImpl implements ProcessServiceDao {

    private final ProcessRepository rep;

    @Autowired
    public ProcessServiceDaoImpl(ProcessRepository rep) {
        this.rep = rep;
    }

    @Override
    public List<Process> findAll(ProcessFilter filter, CustomUser user) {
        return rep.findAll();
    }

    @Override
    public Optional<Process> findById(UUID id, CustomUser user) {
        return rep.findById(new OrgKey(id, user.getOrganizationId()));
    }

    @Override
    public Process save(ProcessModel model, CustomUser user) {
        Process process = fillProcess(new Process(), model);
        process.setOrganizationId(user.getOrganizationId());
        return rep.saveAndFlush(process);
    }

    @Override
    public Process edit(ProcessModel model, CustomUser user) {
        Process process = rep.findById(new OrgKey(model.getId(), user.getOrganizationId())).orElseThrow();
        return rep.save(fillProcess(process, model));
    }

    @Override
    public void delete(UUID id, CustomUser user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Process findByDocumentId(UUID docId, String orgId) {
        return rep.findByDocumentIdAndOrganizationId(docId, orgId);
    }

    private Process fillProcess(Process process, ProcessModel model){
        return process.setProcessDefId(model.getProcessDefId())
                .setDocumentId(model.getDocumentId())
                .setDocumentOrgId(model.getDocumentOrgId())
                .setCurrentTaskId(model.getCurrentTaskId())
                .setCurrentTaskOrganizationId(model.getCurrentTaskOrganizationId())
                .setPrevTaskId(model.getPrevTaskId())
                .setPrevTaskOrganizationId(model.getPrevTaskOrganizationId())
                .setStart(model.getStart())
                .setEnd(model.getEnd());
    }
}
