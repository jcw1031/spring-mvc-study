package com.woopaca.servlet.web.frontcontroller.v5;

import com.woopaca.servlet.web.frontcontroller.ModelView;
import com.woopaca.servlet.web.frontcontroller.MyView;
import com.woopaca.servlet.web.frontcontroller.v5.config.HandlerConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap;
    private final List<MyHandlerAdapter> handlerAdapters;

    public FrontControllerServletV5() {
        HandlerConfig handlerConfig = new HandlerConfig();

        this.handlerMappingMap = handlerConfig.handlerMappingMap();
        this.handlerAdapters = handlerConfig.handlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request); // Handler 찾아 오셈
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler); // HandlerAdapter 찾아 오셈

        ModelView modelView = adapter.handle(request, response, handler); // ModelView 반환

        String viewName = modelView.getViewName();
        MyView myView = viewResolve(viewName);

        myView.render(modelView.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.isSupport(handler)) {
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("HandlerAdapter Not Found: " + handler);
    }

    private MyView viewResolve(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
