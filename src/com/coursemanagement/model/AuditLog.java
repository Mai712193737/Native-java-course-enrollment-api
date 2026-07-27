package com.coursemanagement.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuditLog {
    private Long id;
    private String action;
    private String entityType;
    private Long entityId;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuditLog auditLog = (AuditLog) o;
        return Objects.equals(id, auditLog.id) && Objects.equals(action, auditLog.action) && Objects.equals(entityType, auditLog.entityType) && Objects.equals(entityId, auditLog.entityId) && Objects.equals(description, auditLog.description) && Objects.equals(createdAt, auditLog.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, entityType, entityId, description, createdAt);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public AuditLog(Long id, String action, String entityType, Long entityId, String description, LocalDateTime createdAt) {
        this.id = id;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.description = description;
        this.createdAt = createdAt;
    }

    public String getAction() {
        return action;
    }

    public String getEntityType() {
        return entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private String description;
    private LocalDateTime createdAt;
}
