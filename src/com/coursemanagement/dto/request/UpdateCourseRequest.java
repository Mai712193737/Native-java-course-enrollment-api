package com.coursemanagement.dto.request;

import com.coursemanagement.model.Enums.CourseStatus;

import java.math.BigDecimal;

public class UpdateCourseRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private int capacity;
    private CourseStatus status;

    public UpdateCourseRequest() {
    }

    public UpdateCourseRequest(String title, String description, BigDecimal price, int capacity, CourseStatus status) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}