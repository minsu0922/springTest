package com.example.springexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/signup")
    public String getSignup(Model model){
        //라디오버튼의 초기화 메소드 호출
        radioMarriage = initRadioMarriage();
        //라디오버튼용 Map을 model에 등록
        model.addAttribute("radioMarrige", radioMarriage);
        return "login/signup";
    }


    @PostMapping("/signup")
    public String postSignup(Model model){
        //login.html로 리다이렉션
        return "redirect:/login";
    }
}
