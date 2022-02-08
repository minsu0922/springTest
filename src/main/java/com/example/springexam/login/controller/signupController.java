package com.example.springexam.login.controller;

import com.example.springexam.domian.model.GroupOrder;
import com.example.springexam.domian.model.signupForm;
import com.example.springexam.domian.model.user;
import com.example.springexam.domian.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class signupController {

    @Autowired
    private userService userService;

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

        user user = new user();
        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());
        user.setRole("ROLE_GENERAL");

        boolean results = userService.insert(user);

        if(results == true){
            System.out.println("등록성공");

        }else {
            System.out.println("등록실패");
        }
        //login.html로 리다이렉션
        return "redirect:/login";
    }

    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model){
        model.addAttribute("error", "내부서버 오류(db) : ExceptionHandler");

        model.addAttribute("message", "SignupController DataAccessException이 발생합니다");

        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){
        model.addAttribute("error", "내부 서버 오류 : ExceptionHandler");

        model.addAttribute("message", "SignupController에서 Exception이 발생합니다");

        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

}
