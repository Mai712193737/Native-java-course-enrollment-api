package com.coursemanagement.repository;

import com.coursemanagement.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    void save(Payment payment);

    Optional<Payment> findById(Long id);

    Optional<Payment> findByEnrollmentId(Long enrollmentId);

    List<Payment> findAll();
}