package com.coursemanagement.repository;

import com.coursemanagement.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    void save(Course course);

    Optional<Course> findById(Long id);

    List<Course> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);
}