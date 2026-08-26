package com.coursemanagement.mapper;

import com.coursemanagement.dto.response.CourseResponse;
import com.coursemanagement.model.Course;

public class CourseMapper {

    public CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getCapacity(),
                course.getAvailableSeats(),
                course.getStatus(),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
}