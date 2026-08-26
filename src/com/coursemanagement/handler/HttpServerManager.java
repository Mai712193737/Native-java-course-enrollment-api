package com.coursemanagement.handler;

import com.coursemanagement.util.HttpUtil;
import com.coursemanagement.util.JsonUtil;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpServerManager {

    private HttpServer server;

    public void start(StudentHandler studentHandler, CourseHandler courseHandler, AuthHandler authHandler, EnrollmentHandler enrollmentHandler, PaymentHandler paymentHandler) throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/health", exchange -> {
            if (!HttpUtil.getMethod(exchange).equals("GET")) {
                HttpUtil.sendJson(exchange, 405, JsonUtil.errorToJson("Method Not Allowed"));
                return;
            }
            Map<String, Object> fields = new LinkedHashMap<>();
            fields.put("status", "UP");
            fields.put("application", "Course Enrollment API");
            HttpUtil.sendJson(exchange, 200, JsonUtil.toJson(fields));
        });

        server.createContext("/api/students", studentHandler);
        server.createContext("/api/courses", courseHandler);
        server.createContext("/api/auth", authHandler);
        server.createContext("/api/enrollments", enrollmentHandler);
        server.createContext("/api/payments", paymentHandler);

        server.setExecutor(null);
        server.start();
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
        }
    }
}