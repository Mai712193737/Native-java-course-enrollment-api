package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.Student;
import com.coursemanagement.repository.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryEnrollmentRepository implements EnrollmentRepository {
    @Override
    public void save(EnrollmentRepository enrollmentRepository) {

    }

    @Override
    public Optional<EnrollmentRepository> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<EnrollmentRepository> findAll() {
        return List.of();
    }

    @Override
    public Optional<Student> findByStudentId(int Student_id) {
        return Optional.empty();
    }

    @Override
    public void existsByStudentIdAndCourseId(int StudentId, int CourseId) {

    }

    @Override
    public void deleteById(int id) {

    }
}
