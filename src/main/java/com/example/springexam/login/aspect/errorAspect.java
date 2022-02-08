package com.example.springexam.login.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class errorAspect {

    @AfterThrowing(value = "execution(* *..*..*(..))"
    + " && (bean(*Controller) || bean(*Service) || bean(*Repository))"
    , throwing= "ex")

    public void throwingNull(DataAccessException ex){
        System.out.println("============================");
        System.out.println("DataAccessException이 발생했습니다다 : " + ex);
        System.out.println("============================");
   }
}
