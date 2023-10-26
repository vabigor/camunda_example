package com.example.workflow.dao.repository;

import com.example.workflow.dao.entity.ProcessTask;
import com.example.workflow.dao.key.OrgKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessTaskRepository extends JpaRepository<ProcessTask, OrgKey> {
}
