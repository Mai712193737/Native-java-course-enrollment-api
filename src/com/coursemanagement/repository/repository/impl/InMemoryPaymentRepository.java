package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.Payment;
import com.coursemanagement.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryPaymentRepository implements PaymentRepository {
    @Override
    public void save(PaymentRepository paymentRepository) {

    }

    @Override
    public Optional<Payment> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Payment> findByEnrollmentId(int enrollmentId) {
        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() {
        return List.of();
    }
}
