package com.coursemanagement.repository;

import com.coursemanagement.model.AuditLog;

import java.util.List;
import java.util.Optional;

public interface AuditLogRepository {
    void save(AuditLog auditLog);

    Optional<AuditLog> findById(int id);

    List<AuditLog> findAll();

    List<AuditLog> findByEntityType(String entityType);
}
