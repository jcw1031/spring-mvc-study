package com.woopaca.servlet.web.frontcontroller.v3;

import com.woopaca.servlet.web.frontcontroller.ModelView;
import com.woopaca.servlet.web.frontcontroller.MyView;
import com.woopaca.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.woopaca.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.woopaca.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 디테일한 로직이기 때문에 메서드로 만드는 게 좋다.
        Map<String, String> paramMap = createParamMap(request);
        ModelView modelView = controller.process(paramMap);

        MyView myView = viewResolve(modelView);

        myView.render(modelView.getModel(), request, response);
    }

    private static MyView viewResolve(ModelView modelView) {
        String viewName = modelView.getViewName();// 논리 주소
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName ->
                paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
