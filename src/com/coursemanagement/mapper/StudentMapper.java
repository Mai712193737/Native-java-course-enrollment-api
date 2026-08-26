package com.coursemanagement.mapper;

import com.coursemanagement.dto.response.StudentResponse;
import com.coursemanagement.model.Student;

public class StudentMapper {

    public StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFullName(),
                student.getEmail(),
                student.getRole(),
                student.isActive(),
                student.getCreatedAt()
        );
    }
}