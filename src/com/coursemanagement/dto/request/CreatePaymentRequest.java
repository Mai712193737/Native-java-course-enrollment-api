package com.coursemanagement.dto.request;

import com.coursemanagement.model.Enums.PaymentMethod;

import java.math.BigDecimal;

public class CreatePaymentRequest {
    private Long enrollmentId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;

    public CreatePaymentRequest() {
    }

    public CreatePaymentRequest(Long enrollmentId, BigDecimal amount, PaymentMethod paymentMethod) {
        this.enrollmentId = enrollmentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}