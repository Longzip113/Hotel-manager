package com.hotelManager.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class QLKSUser {

    @Pointcut(value = "execution(* com.hotelManager.services.impl.*.*(..))")
    public void logAround() {}

//    @Before("logAround())")
//    public void logBefore(JoinPoint joinPoint) {
//
//        System.out.println("logBefore() is running!");
//        System.out.println("Method name : " + joinPoint.getSignature().getName());
//        System.out.println("Pra : " + Arrays.toString(joinPoint.getArgs()));
//        System.out.println("******");
//    }

//    @After("logAround())")
//    public void logAfter(JoinPoint joinPoint) {
//
//        System.out.println("logAfter() is running!");
//        System.out.println("Method : " + joinPoint.getSignature().getName());
//        System.out.println("******");
//
//    }

//    @AfterReturning(
//            pointcut = "execution(* com.hotelManager.services.impl.*.*(..))",
//            returning= "result")
//    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//
//        System.out.println("logAfterReturning() is running!");
//        System.out.println("Method name : " + joinPoint.getSignature().getName());
//        System.out.println("Method returned value is : " + result);
//        System.out.println("******");
//
//    }

//    @AfterThrowing(
//            pointcut = "logAround())",
//            throwing= "error")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
//
//        System.out.println("logAfterThrowing() is running!");
//        System.out.println("Method name : " + joinPoint.getSignature().getName());
//        System.out.println("Exception : " + error);
//
//        System.out.println("******");
//    }

//    @Around("logAround())")
//    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        System.out.println("logAround() is running!");
//        System.out.println("Method name : " + joinPoint.getSignature().getName());
//        System.out.println("Response : " + Arrays.toString(joinPoint.getArgs()));
//
//        System.out.println("Class name : " + joinPoint.getTarget().getClass());
//
//        System.out.println("Around before is running!");
//        joinPoint.proceed();
//        System.out.println("Around after is running!");
//
//        System.out.println("******");
//
//    }
}
