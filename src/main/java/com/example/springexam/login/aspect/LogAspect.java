package com.example.springexam.login.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Around("execution(* *..*.*controller.*(..))")
    public Object startLog(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("메소드 시작: "+ pjp.getSignature());

        try {
            Object result = pjp.proceed();
            System.out.println("메소드 종료:" + pjp.getSignature());
            return result;
        }catch (Exception e){
            System.out.println("메소드 이상 종료:" + pjp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

    @Around("execution(* *..*.*userDao.*(..))")
    public Object daoLog(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("메소드 시작: "+ pjp.getSignature());

        try {
            Object result = pjp.proceed();
            System.out.println("메소드 종료:" + pjp.getSignature());
            return result;
        }catch (Exception e){
            System.out.println("메소드 이상 종료:" + pjp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}
