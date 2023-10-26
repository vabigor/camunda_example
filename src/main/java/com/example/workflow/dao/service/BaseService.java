package com.example.workflow.dao.service;

import com.example.workflow.common.CustomUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<M, F, R> {

    List<R> findAll(F filter, CustomUser user);
    Optional<R> findById(UUID id, CustomUser user);
    R save(M model, CustomUser user);
    R edit(M model, CustomUser user);
    void delete(UUID id, CustomUser user);

}
