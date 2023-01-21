package com.woopaca.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woopaca.servlet.basic.BasicData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json;charset=UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        BasicData data = new BasicData();
        data.setUsername("woopaca");
        data.setAge(24);

        // {"username":"woopaca","age":24}
        String result = mapper.writeValueAsString(data);
        response.getWriter().write(result);
    }
}
