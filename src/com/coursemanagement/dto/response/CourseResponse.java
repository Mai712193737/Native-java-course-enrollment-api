package com.coursemanagement.dto.response;

import com.coursemanagement.model.Enums.CourseStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private int capacity;
    private int availableSeats;
    private CourseStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CourseResponse(Long id, String title, String description, BigDecimal price, int capacity, int availableSeats, CourseStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}