package com.example.springexam.login.controller;

import com.example.springexam.domian.model.user;
import com.example.springexam.domian.service.restService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userRestController {

    @Autowired
    restService service;

    @GetMapping("/rest/get")
    public List<user> getUserMany(){
        return service.selectMany();
    }

    @GetMapping("/rest/get/{id:.+}")
    private user getUserOne(@PathVariable("id") String userId){
        return service.selectOne(userId);
    }

    @PostMapping("/rest/insert")
    public String postUserOne(@RequestBody user user){
        boolean result = service.insert(user);

        String str = "";

        if(result == true){
            str= "{\"result\":\"ok\"}";
        }else {
            str= "{\"result\":\"error\"}";
        }
        return str;
    }

    @PutMapping("/rest/update")
    public String putUserOne(@RequestBody user user){
        boolean result = service.insert(user);

        String str = "";

        if(result == true){
            str= "{\"result\":\"ok\"}";
        }else {
            str= "{\"result\":\"error\"}";
        }
        return str;
    }

    @DeleteMapping("/rest/delete/{id:.+}")
    private String deleteUserOne(@PathVariable("id") String userId){
        boolean result = service.delete(userId);

        String str = "";

        if(result == true){
            str= "{\"result\":\"ok\"}";
        }else {
            str= "{\"result\":\"error\"}";
        }
        return str;
    }
}
