package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.AuditLog;
import com.coursemanagement.repository.AuditLogRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryAuditLogRepository implements AuditLogRepository {
    @Override
    public void save(AuditLog auditLog) {

    }

    @Override
    public Optional<AuditLog> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<AuditLog> findAll() {
        return List.of();
    }

    @Override
    public List<AuditLog> findByEntityType(String entityType) {
        return List.of();
    }
}
