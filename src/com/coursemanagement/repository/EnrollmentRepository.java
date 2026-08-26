package com.coursemanagement.repository;

import com.coursemanagement.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {

    void save(Enrollment enrollment);

    Optional<Enrollment> findById(Long id);

    List<Enrollment> findAll();

    List<Enrollment> findByStudentId(Long studentId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    void deleteById(Long id);
}