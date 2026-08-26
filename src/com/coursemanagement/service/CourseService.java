package com.coursemanagement.service;

import com.coursemanagement.dto.request.CreateCourseRequest;
import com.coursemanagement.ecxception.ecxception;
import com.coursemanagement.model.Course;
import com.coursemanagement.model.Enums.CourseStatus;
import com.coursemanagement.repository.CourseRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(CreateCourseRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new ecxception("Title is required");
        }
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new ecxception("Description is required");
        }
        if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ecxception("Price must be greater than zero");
        }
        if (request.getCapacity() <= 0) {
            throw new ecxception("Capacity must be greater than zero");
        }

        Course course = new Course(
                null,
                request.getTitle(),
                request.getDescription(),
                request.getPrice(),
                request.getCapacity(),
                request.getCapacity(),
                request.getStatus() != null ? request.getStatus() : CourseStatus.OPEN,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        courseRepository.save(course);
        return course;
    }

    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
}