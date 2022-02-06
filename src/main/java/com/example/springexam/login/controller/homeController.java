package com.example.springexam.login.controller;

import com.example.springexam.domian.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class homeController {

    @Autowired
    com.example.springexam.domian.service.userService userService;

    @GetMapping("/home")
    public String getHome(Model model){
        model.addAttribute("contents",
                "login/home :: home_contents");
        return "login/homeLayout";
    }

    @PostMapping("/logout")
    public String postLogout(){
        return "redirect:/login";
    }
}
