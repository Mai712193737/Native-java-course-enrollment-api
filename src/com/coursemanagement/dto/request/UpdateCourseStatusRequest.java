package com.coursemanagement.dto.request;

import com.coursemanagement.model.Enums.CourseStatus;

public class UpdateCourseStatusRequest {
    private CourseStatus status;

    public UpdateCourseStatusRequest() {
    }

    public UpdateCourseStatusRequest(CourseStatus status) {
        this.status = status;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}