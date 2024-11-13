package com.disaster.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectjHandler {

    @Pointcut("execution(* com.disaster.aop.aspectj.Performance.perform(..))")
    public void write(){
    }

    @Before("write()")
    public void before(){
        System.out.println("前置处理器");
    }

    @After("write()")
    public void after(){
        System.out.println("后置处理器");
    }

    @AfterThrowing("write()")
    public void AfterThrowing(){
        //TODO
    }

    @AfterReturning("write()")
    public void AfterReturning(){
        //TODO
    }

    @Around("write()")
    public void Around(ProceedingJoinPoint joinPoint){
        //TODO
    }
}
