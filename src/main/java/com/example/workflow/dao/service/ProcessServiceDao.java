package com.example.workflow.dao.service;

import com.example.workflow.controller.filter.ProcessFilter;
import com.example.workflow.dao.entity.Process;
import com.example.workflow.model.ProcessModel;

import java.util.UUID;

public interface ProcessServiceDao extends BaseService<ProcessModel, ProcessFilter, Process>{

    Process findByDocumentId(UUID docId, String orgId);
}
