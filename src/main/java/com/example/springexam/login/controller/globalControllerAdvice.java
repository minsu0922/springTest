package com.example.springexam.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class globalControllerAdvice {
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model
            model) {
        // 예외 클래스의 메시지를 Model 에 등록
        model.addAttribute("error", "내부 서버 오류（DB）： GlobalControllerAdvice");
                // 예외 클래스의 메시지를 Model 에 등록
                model.addAttribute("message", "DataAccessException 이 발생했습니다");
                        // HTTP 오류코드（500）를 Model 에 등록
                        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        // 예외 클래스의 메시지를 Model 에 등록
        model.addAttribute("error", "내부 서버 오류： GlobalControllerAdvice");
                // 예외 클래스의 메시지를 Model 에 등록
                model.addAttribute("message", "Exception 이 발생했습니다");
        // HTTP 오류코드（500）를 Model 에 등록
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }
}

