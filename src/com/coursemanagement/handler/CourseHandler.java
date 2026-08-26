package com.coursemanagement.handler;

import com.coursemanagement.dto.request.CreateCourseRequest;
import com.coursemanagement.dto.response.CourseResponse;
import com.coursemanagement.ecxception.ecxception;
import com.coursemanagement.mapper.CourseMapper;
import com.coursemanagement.model.Course;
import com.coursemanagement.model.Enums.CourseStatus;
import com.coursemanagement.service.CourseService;
import com.coursemanagement.util.HttpUtil;
import com.coursemanagement.util.JsonUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CourseHandler implements HttpHandler {

    private CourseService courseService;
    private CourseMapper courseMapper;

    public CourseHandler(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = HttpUtil.getMethod(exchange);
        String path = HttpUtil.getPath(exchange);

        try {
            if (path.equals("/api/courses") && method.equals("GET")) {
                handleFindAll(exchange);
                return;
            }
            if (path.equals("/api/courses") && method.equals("POST")) {
                handleCreate(exchange);
                return;
            }
            if (path.matches("/api/courses/\\d+") && method.equals("GET")) {
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

    private void handleCreate(HttpExchange exchange) throws IOException {
        String body = HttpUtil.readRequestBody(exchange);
        Map<String, Object> json = JsonUtil.parseObject(body);

        CreateCourseRequest request = new CreateCourseRequest(
                JsonUtil.getString(json, "title"),
                JsonUtil.getString(json, "description"),
                JsonUtil.getDecimal(json, "price"),
                JsonUtil.getInt(json, "capacity"),
                JsonUtil.getEnum(json, "status", CourseStatus.class)
        );

        Course course = courseService.createCourse(request);
        CourseResponse response = courseMapper.toResponse(course);

        HttpUtil.addHeader(exchange, "Location", "/api/courses/" + course.getId());
        HttpUtil.sendJson(exchange, 201, toJson(response));
    }

    private void handleFindAll(HttpExchange exchange) throws IOException {
        List<Course> courses = courseService.findAllCourses();
        List<Map<String, Object>> items = new ArrayList<>();
        for (Course course : courses) {
            items.add(toFieldsMap(courseMapper.toResponse(course)));
        }
        HttpUtil.sendJson(exchange, 200, JsonUtil.toJsonArray(items));
    }

    private void handleFindById(HttpExchange exchange, String path) throws IOException {
        Long id = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
        Optional<Course> course = courseService.findCourseById(id);
        if (course.isEmpty()) {
            HttpUtil.sendJson(exchange, 404, JsonUtil.errorToJson("Course not found"));
            return;
        }
        CourseResponse response = courseMapper.toResponse(course.get());
        HttpUtil.sendJson(exchange, 200, toJson(response));
    }

    private String toJson(CourseResponse response) {
        return JsonUtil.toJson(toFieldsMap(response));
    }

    private Map<String, Object> toFieldsMap(CourseResponse response) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("id", response.getId());
        fields.put("title", response.getTitle());
        fields.put("description", response.getDescription());
        fields.put("price", response.getPrice());
        fields.put("capacity", response.getCapacity());
        fields.put("availableSeats", response.getAvailableSeats());
        fields.put("status", response.getStatus());
        fields.put("createdAt", response.getCreatedAt());
        fields.put("updatedAt", response.getUpdatedAt());
        return fields;
    }
}