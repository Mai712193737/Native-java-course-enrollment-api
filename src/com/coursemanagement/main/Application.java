package com.coursemanagement.main;

import com.coursemanagement.handler.*;
import com.coursemanagement.mapper.CourseMapper;
import com.coursemanagement.mapper.StudentMapper;
import com.coursemanagement.repository.CourseRepository;
import com.coursemanagement.repository.repository.impl.InMemoryStudentRepository;
import com.coursemanagement.repository.StudentRepository;
import com.coursemanagement.repository.repository.impl.InMemoryCourseRepository;
import com.coursemanagement.service.CourseService;
import com.coursemanagement.service.StudentService;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        System.out.println("Course Enrollment Management System");
        System.out.println("Application started successfully");

        StudentRepository studentRepository = new InMemoryStudentRepository();
        CourseRepository courseRepository = new InMemoryCourseRepository();

        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);

        StudentMapper studentMapper = new StudentMapper();
        CourseMapper courseMapper = new CourseMapper();

        StudentHandler studentHandler = new StudentHandler(studentService, studentMapper);
        CourseHandler courseHandler = new CourseHandler(courseService, courseMapper);
        AuthHandler authHandler = new AuthHandler();
        EnrollmentHandler enrollmentHandler = new EnrollmentHandler();
        PaymentHandler paymentHandler = new PaymentHandler();

        HttpServerManager serverManager = new HttpServerManager();
        try {
            serverManager.start(studentHandler, courseHandler, authHandler, enrollmentHandler, paymentHandler);
            System.out.println("Server started on http://localhost:8080");
        } catch (IOException e) {
            System.out.println("Failed to start server: " + e.getMessage());
        }
    }
}