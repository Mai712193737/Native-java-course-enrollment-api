package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.Payment;
import com.coursemanagement.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryPaymentRepository implements PaymentRepository {

    private Map<Long, Payment> payments = new HashMap<>();
    private long nextId = 1L;

    @Override
    public void save(Payment payment) {
        if (payment.getId() == null) {
            payment.setId(nextId++);
        }
        payments.put(payment.getId(), payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return Optional.ofNullable(payments.get(id));
    }

    @Override
    public Optional<Payment> findByEnrollmentId(Long enrollmentId) {
        return payments.values().stream()
                .filter(payment -> payment.getEnrollmentId().equals(enrollmentId))
                .findFirst();
    }

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(payments.values());
    }
}