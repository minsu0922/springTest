package com.example.springexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class signupController {

    //라디오 버튼용 변수
    private Map<String , String> radioMarriage;

    //라디오버튼 초기화
    private Map<String, String> initRadioMarriage(){
        Map<String, String> radio = new LinkedHashMap<>();

        //기혼/미혼 정보를 map에 저장
        radio.put("기혼", "true");
        radio.put("미혼", "false");

        return radio;
    }

    @PostMapping("/signup")
    public String postSignup(Model model){
        //login.html로 리다이렉션
        return "redirect:/login";
    }
}
