package com.example.springexam.login.controller;

import com.example.springexam.domian.model.GroupOrder;
import com.example.springexam.domian.model.signupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String getSignup(@ModelAttribute signupForm form, Model model){

        model.addAttribute("signupForm", form);
        //라디오버튼의 초기화 메소드 호출
        radioMarriage = initRadioMarriage();
        //라디오버튼용 Map을 model에 등록
        model.addAttribute("radioMarrige", radioMarriage);

        return "login/signup";
    }


    @PostMapping("/signup")
    public String postSignup(@ModelAttribute @Validated(GroupOrder.class) signupForm form,
                             BindingResult result, Model model){
        //입력항목 조건에 걸리면 사용자등록 화면으로 리턴
        if (result.hasErrors()){
            //get 요청을 위한 메소드를 호출하여 사용자등록 화면으로 리턴
            return getSignup(form, model);
        }
        System.out.println(form);
        //login.html로 리다이렉션
        return "redirect:/login";
    }
}
