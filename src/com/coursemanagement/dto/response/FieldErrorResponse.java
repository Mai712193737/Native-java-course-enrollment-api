package com.coursemanagement.dto.response;

public class FieldErrorResponse {
    private String field;
    private String message;

    public FieldErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}