package com.coursemanagement.dto.response;

import com.coursemanagement.model.Enums.PaymentMethod;
import com.coursemanagement.model.Enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {
    private Long id;
    private Long enrollmentId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionReference;
    private LocalDateTime paymentDate;

    public PaymentResponse(Long id, Long enrollmentId, BigDecimal amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus, String transactionReference, LocalDateTime paymentDate) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.transactionReference = transactionReference;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
}