package com.coursemanagement.service;

import com.coursemanagement.dto.request.RegisterStudentRequest;
import com.coursemanagement.ecxception.ecxception;
import com.coursemanagement.model.Enums.Role;
import com.coursemanagement.model.Student;
import com.coursemanagement.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(RegisterStudentRequest request) {
        if (request.getFullName() == null || request.getFullName().isBlank()) {
            throw new ecxception("Full name is required");
        }
        if (request.getEmail() == null || !request.getEmail().matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new ecxception("Valid email is required");
        }
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new ecxception("Email already exists");
        }
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new ecxception("Password must contain at least six characters");
        }

        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setPassword(request.getPassword());
        student.setRole(Role.STUDENT);
        student.setActive(true);
        student.setCreatedAt(LocalDateTime.now());

        studentRepository.save(student);
        return student;
    }

    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
}