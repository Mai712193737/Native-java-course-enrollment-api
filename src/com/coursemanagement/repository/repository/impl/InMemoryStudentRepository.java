package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.Student;
import com.coursemanagement.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryStudentRepository implements StudentRepository {

    private Map<Long, Student> students = new HashMap<>();
    private Long nextId = 1L;


    @Override
    public void save(Student student) {

        if(student.getId() == 0) {
            student.setId(nextId++);
        }

        students.put(student.getId(), student);
    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(students.get((long) id));
    }

    @Override
    public String findByEmail(String Email) {
        return "";
    }

    @Override
    public List<Student> findAll() {
        return students ;
    }

    @Override
    public void existsByEmail(String Email) {

    }

    @Override
    public void deleteById(int id) {
        students.remove((long) id);
    }

    @Override
    public boolean existsById(int id) {
        return students.containsKey((long) id);
    }
}