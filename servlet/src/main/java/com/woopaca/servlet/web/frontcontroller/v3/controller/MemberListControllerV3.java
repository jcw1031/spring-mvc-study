package com.woopaca.servlet.web.frontcontroller.v3.controller;

import com.woopaca.servlet.domain.member.Member;
import com.woopaca.servlet.domain.member.MemberRepository;
import com.woopaca.servlet.web.frontcontroller.ModelView;
import com.woopaca.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);

        return modelView;
    }
}
