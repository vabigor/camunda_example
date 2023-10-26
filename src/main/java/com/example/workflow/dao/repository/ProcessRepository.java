package com.example.workflow.dao.repository;

import com.example.workflow.dao.entity.Process;
import com.example.workflow.dao.key.OrgKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessRepository extends JpaRepository<Process, OrgKey> {

    Process findByDocumentIdAndOrganizationId(UUID id, String organizationId);
}
