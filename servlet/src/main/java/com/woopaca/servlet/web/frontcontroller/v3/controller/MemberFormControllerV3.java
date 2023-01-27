package com.woopaca.servlet.web.frontcontroller.v3.controller;

import com.woopaca.servlet.web.frontcontroller.ModelView;
import com.woopaca.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form"); // 논리적인 이름만 전달
    }
}
