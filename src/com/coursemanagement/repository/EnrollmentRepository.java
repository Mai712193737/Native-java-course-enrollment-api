package com.coursemanagement.repository;

import com.coursemanagement.model.Student;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    /*save
    findById
    findAll
    findByStudentId
    existsByStudentIdAndCourseId
    deleteById
     */
     public void save (EnrollmentRepository enrollmentRepository);

    public Optional<EnrollmentRepository> findById(int id);

    List<EnrollmentRepository> findAll();

    public Optional<Student> findByStudentId(int Student_id);

    public void existsByStudentIdAndCourseId(int StudentId, int CourseId );

    public void deleteById(int id );

}
