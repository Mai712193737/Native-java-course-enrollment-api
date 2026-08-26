package com.coursemanagement.handler;

import com.coursemanagement.dto.request.RegisterStudentRequest;
import com.coursemanagement.dto.response.StudentResponse;
import com.coursemanagement.ecxception.ecxception;
import com.coursemanagement.mapper.StudentMapper;
import com.coursemanagement.model.Student;
import com.coursemanagement.service.StudentService;
import com.coursemanagement.util.HttpUtil;
import com.coursemanagement.util.JsonUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class StudentHandler implements HttpHandler {

    private StudentService studentService;
    private StudentMapper studentMapper;

    public StudentHandler(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = HttpUtil.getMethod(exchange);
        String path = HttpUtil.getPath(exchange);

        try {
            if (path.equals("/api/students") && method.equals("POST")) {
                handleRegister(exchange);
                return;
            }
            if (path.matches("/api/students/\\d+") && method.equals("GET")) {
                handleFindById(exchange, path);
                return;
            }
            HttpUtil.sendJson(exchange, 405, JsonUtil.errorToJson("Method Not Allowed"));
        } catch (ecxception e) {
            HttpUtil.sendJson(exchange, 400, JsonUtil.errorToJson(e.getMessage()));
        } catch (Exception e) {
            HttpUtil.sendJson(exchange, 400, JsonUtil.errorToJson("Invalid request"));
        }
    }

    private void handleRegister(HttpExchange exchange) throws IOException {
        String body = HttpUtil.readRequestBody(exchange);
        Map<String, Object> json = JsonUtil.parseObject(body);

        RegisterStudentRequest request = new RegisterStudentRequest(
                JsonUtil.getString(json, "fullName"),
                JsonUtil.getString(json, "email"),
                JsonUtil.getString(json, "password")
        );

        Student student = studentService.registerStudent(request);
        StudentResponse response = studentMapper.toResponse(student);

        HttpUtil.addHeader(exchange, "Location", "/api/students/" + student.getId());
        HttpUtil.sendJson(exchange, 201, toJson(response));
    }

    private void handleFindById(HttpExchange exchange, String path) throws IOException {
        Long id = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
        Optional<Student> student = studentService.findStudentById(id);
        if (student.isEmpty()) {
            HttpUtil.sendJson(exchange, 404, JsonUtil.errorToJson("Student not found"));
            return;
        }
        StudentResponse response = studentMapper.toResponse(student.get());
        HttpUtil.sendJson(exchange, 200, toJson(response));
    }

    private String toJson(StudentResponse response) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("id", response.getId());
        fields.put("fullName", response.getFullName());
        fields.put("email", response.getEmail());
        fields.put("role", response.getRole());
        fields.put("active", response.isActive());
        fields.put("createdAt", response.getCreatedAt());
        return JsonUtil.toJson(fields);
    }
}