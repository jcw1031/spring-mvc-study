package com.woopaca.servlet.web.frontcontroller.v5.config;

import com.woopaca.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.woopaca.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.woopaca.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.woopaca.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.woopaca.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.woopaca.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.woopaca.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import com.woopaca.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.woopaca.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerConfig {

    public Map<String, Object> handlerMappingMap() {
        Map<String, Object> handlerMappingMap = new HashMap<>();

        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());

        return handlerMappingMap;
    }

    public List<MyHandlerAdapter> handlerAdapters() {
        List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());

        return handlerAdapters;
    }
}
