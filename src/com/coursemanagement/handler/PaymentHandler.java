package com.coursemanagement.handler;

import com.coursemanagement.util.HttpUtil;
import com.coursemanagement.util.JsonUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class PaymentHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpUtil.sendJson(exchange, 404, JsonUtil.errorToJson("Not Found"));
    }
}