package com.example.workflow.dao.service.impl;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.filter.ProcessTaskFilter;
import com.example.workflow.dao.entity.ProcessTask;
import com.example.workflow.dao.key.OrgKey;
import com.example.workflow.dao.repository.ProcessTaskRepository;
import com.example.workflow.dao.service.ProcessTaskServiceDao;
import com.example.workflow.model.ProcessTaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProcessTaskServiceDaoImpl implements ProcessTaskServiceDao {

    private final ProcessTaskRepository rep;

    @Autowired
    public ProcessTaskServiceDaoImpl(ProcessTaskRepository rep) {
        this.rep = rep;
    }

    @Override
    public List<ProcessTask> findAll(ProcessTaskFilter filter, CustomUser user) {
        return rep.findAll();
    }

    @Override
    public Optional<ProcessTask> findById(UUID id, CustomUser user) {
        return rep.findById(new OrgKey(id, user.getOrganizationId()));
    }

    @Override
    public ProcessTask save(ProcessTaskModel model, CustomUser user) {
        ProcessTask processTask = fillProcess(new ProcessTask(), model);
        processTask.setOrganizationId(user.getOrganizationId());
        return rep.save(processTask);
    }

    @Override
    public ProcessTask edit(ProcessTaskModel model, CustomUser user) {
        ProcessTask processTask = rep.findById(new OrgKey(model.getId(), user.getOrganizationId())).orElseThrow();
        return rep.save(fillProcess(processTask, model));
    }

    @Override
    public void delete(UUID id, CustomUser user) {
        throw new UnsupportedOperationException();
    }

    private ProcessTask fillProcess(ProcessTask processTask, ProcessTaskModel model){
        return processTask.setType(model.getType())
                .setDocumentId(model.getDocumentId())
                .setDocumentOrgId(model.getDocumentOrgId())
                .setProcessDefId(model.getProcessDefId())
                .setStart(model.getStart())
                .setEnd(model.getEnd())
                .setExecutionCount(model.getExecutionCount())
                .setExecuteCount(model.getExecuteCount());
    }
}
