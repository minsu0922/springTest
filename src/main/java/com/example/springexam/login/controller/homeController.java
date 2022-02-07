package com.example.springexam.login.controller;

import com.example.springexam.domian.model.signupForm;
import com.example.springexam.domian.model.user;
import com.example.springexam.domian.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class homeController {

    @Autowired
    com.example.springexam.domian.service.userService userService;

    //결혼여부 라디오버튼
    private Map<String, String> radioMarriage;

    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new LinkedHashMap<>();
        radio.put("기혼", "true");
        radio.put("미혼", "false");
        return radio;
    }

    @GetMapping("/home")
    public String getHome(Model model){
        model.addAttribute("contents",
                "login/home :: home_contents");
        return "login/homeLayout";
    }

    @GetMapping("/userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute signupForm form,
                                Model model, @PathVariable("id") String userId){
        System.out.println("userId =" + userId);

        model.addAttribute("contents", "login/userDetail" +
                ":: userDetail_contents");
        radioMarriage = initRadioMarriage();
        model.addAttribute("radioMarriage", radioMarriage);

        if(userId != null && userId.length()>0){
            user user = userService.selectOne(userId);
            form.setUserId(user.getUserId());
            form.setUserName(user.getUserName());
            form.setBirthday(user.getBirthday());
            form.setAge(user.getAge());
            form.setMarriage(user.isMarriage());
            model.addAttribute("signupForm", form);
        }
        return "login/homeLayout";
    }

    @PostMapping(value = "/userDetail", params = "update")
    public String postUserDetailUpdate(@ModelAttribute signupForm form,
                                       Model model){
        System.out.println("변경 버튼 처리");

        user user = new user();
        user.setUserId(form.getUserId());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());
        boolean result = userService.updateOne(user);

        if(result == true){
            model.addAttribute("result", "변경성공");
        }else {
            model.addAttribute("result", "변경실패");
        }
        return getUserList(model);
    }

    @PostMapping(value = "/userDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute signupForm form,
                                       Model model){
        System.out.println("삭제 버튼 실행");

        boolean result = userService.deleteOne(form.getUserId());
        if(result == true){
            model.addAttribute("result", "삭제성공");
        }else {
            model.addAttribute("result", "삭제실패");

        }
        return getUserList(model);
    }

    @GetMapping("/userList")
    public String getUserList(Model model){
        model.addAttribute("contents", "/login/userList :: userList_contents");

        List<user> userList = userService.selectMany();
        model.addAttribute("userList", userList);
        int count = userService.count();
        model.addAttribute("userListCount", count);
        return "login/homeLayout";
    }

    @GetMapping("/userList/csv")
    public String getUserListCsv(Model model){
        return getUserList(model);
    }

    @PostMapping("/logout")
    public String postLogout(){
        return "redirect:/login";
    }
}
