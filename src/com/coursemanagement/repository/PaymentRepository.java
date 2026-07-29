package com.coursemanagement.repository;

import com.coursemanagement.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    /*save
    findById
    findByEnrollmentId
    findAll*/

    public void save (PaymentRepository paymentRepository);

    Optional<Payment> findById(int id);

    Optional<Payment> findByEnrollmentId(int enrollmentId);

    List<Payment> findAll();



}
