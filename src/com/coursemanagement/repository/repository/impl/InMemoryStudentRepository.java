package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.Student;
import com.coursemanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryStudentRepository implements StudentRepository {

    private Map<Long, Student> students = new HashMap<>();
    private long nextId = 1L;

    @Override
    public void save(Student student) {
        if (student.getId() == null) {
            student.setId(nextId++);
        }
        students.put(student.getId(), student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return students.values().stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public boolean existsByEmail(String email) {
        return students.values().stream()
                .anyMatch(student -> student.getEmail().equals(email));
    }

    @Override
    public void deleteById(Long id) {
        students.remove(id);
    }
}