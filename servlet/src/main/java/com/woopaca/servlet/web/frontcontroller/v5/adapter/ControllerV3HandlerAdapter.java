package com.woopaca.servlet.web.frontcontroller.v5.adapter;

import com.woopaca.servlet.web.frontcontroller.ModelView;
import com.woopaca.servlet.web.frontcontroller.v3.ControllerV3;
import com.woopaca.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    // 해당 Controller를 지원하는지 판단
    @Override
    public boolean isSupport(Object handler) {
        return (handler instanceof ControllerV3);
    }

    //
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);

        return controller.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> {
            paramMap.put(paramName, request.getParameter(paramName));
        });

        return paramMap;
    }
}
