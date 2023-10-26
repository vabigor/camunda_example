package com.example.workflow.dao.service.impl;

import com.example.workflow.common.CustomUser;
import com.example.workflow.controller.filter.DocumentFilter;
import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.key.OrgKey;
import com.example.workflow.dao.repository.DocumentRepository;
import com.example.workflow.dao.service.DocumentServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentServiceDaoImpl implements DocumentServiceDao {

    private final DocumentRepository rep;

    @Autowired
    public DocumentServiceDaoImpl(DocumentRepository rep) {
        this.rep = rep;
    }

    @Override
    public List<Document> findAll(DocumentFilter filter, CustomUser user) {
        return rep.findAll();
    }

    @Override
    public Optional<Document> findById(UUID id, CustomUser user) {
        return rep.findById(new OrgKey(id, user.getOrganizationId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Document save(Document document, CustomUser user) {
        return rep.save(document);
    }

    @Override
    public Document edit(Document document, CustomUser user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(UUID id, CustomUser user) {
        rep.deleteById(new OrgKey(id, user.getOrganizationId()));
    }
}
