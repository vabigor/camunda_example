package com.example.workflow.dao.repository;

import com.example.workflow.dao.entity.Document;
import com.example.workflow.dao.key.OrgKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, OrgKey> {
}
