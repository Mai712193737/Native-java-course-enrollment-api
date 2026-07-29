package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.Student;
import com.coursemanagement.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryCourseRepository implements CourseRepository {
    @Override
    public void save(Student student) {

    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public boolean existsById(int id) {
        return false;
    }
}
