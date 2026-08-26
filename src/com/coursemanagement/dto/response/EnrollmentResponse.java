package com.coursemanagement.dto.response;

import com.coursemanagement.model.Enums.EnrollmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EnrollmentResponse {
    private Long id;
    private Long studentId;
    private Long courseId;
    private BigDecimal originalPrice;
    private BigDecimal discountAmount;
    private BigDecimal finalPrice;
    private EnrollmentStatus status;
    private LocalDateTime enrollmentDate;

    public EnrollmentResponse(Long id, Long studentId, Long courseId, BigDecimal originalPrice, BigDecimal discountAmount, BigDecimal finalPrice, EnrollmentStatus status, LocalDateTime enrollmentDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.originalPrice = originalPrice;
        this.discountAmount = discountAmount;
        this.finalPrice = finalPrice;
        this.status = status;
        this.enrollmentDate = enrollmentDate;
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
}