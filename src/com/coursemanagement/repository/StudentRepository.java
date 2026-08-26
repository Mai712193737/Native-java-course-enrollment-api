package com.coursemanagement.repository;

import com.coursemanagement.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void save(Student student);

    Optional<Student> findById(Long id);

    Optional<Student> findByEmail(String email);

    List<Student> findAll();

    boolean existsByEmail(String email);

    void deleteById(Long id);
}