package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.AuditLog;
import com.coursemanagement.repository.AuditLogRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryAuditLogRepository implements AuditLogRepository {

    private Map<Long, AuditLog> auditLogs = new HashMap<>();
    private long nextId = 1L;

    @Override
    public void save(AuditLog auditLog) {
        if (auditLog.getId() == null) {
            auditLog.setId(nextId++);
        }
        auditLogs.put(auditLog.getId(), auditLog);
    }

    @Override
    public Optional<AuditLog> findById(Long id) {
        return Optional.ofNullable(auditLogs.get(id));
    }

    @Override
    public List<AuditLog> findAll() {
        return new ArrayList<>(auditLogs.values());
    }

    @Override
    public List<AuditLog> findByEntityType(String entityType) {
        List<AuditLog> result = new ArrayList<>();
        for (AuditLog auditLog : auditLogs.values()) {
            if (auditLog.getEntityType().equals(entityType)) {
                result.add(auditLog);
            }
        }
        return result;
    }
}