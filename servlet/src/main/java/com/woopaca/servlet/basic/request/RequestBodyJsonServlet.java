package com.woopaca.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woopaca.servlet.basic.BasicData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);

        BasicData basicData = mapper.readValue(messageBody, BasicData.class);
        System.out.println("basicData.username = " + basicData.getUsername());
        System.out.println("basicData.age = " + basicData.getAge());

        response.getWriter().write("200 OK");
    }
}
