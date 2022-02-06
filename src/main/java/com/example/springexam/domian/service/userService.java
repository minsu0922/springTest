package com.example.springexam.domian.service;

import com.example.springexam.domian.repository.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    userDao dao;
}
