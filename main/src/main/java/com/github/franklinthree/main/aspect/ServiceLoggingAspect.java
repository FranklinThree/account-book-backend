package com.github.franklinthree.main.aspect;


import org.slf4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLoggingAspect {
    @Autowired
    @Qualifier("serviceLogger")
    private Logger serviceLogger;

    @Pointcut("execution(* com.github.franklinthree.main.service.*.*(..))")
    public void serviceMethods() {}

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logServiceMethod(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        serviceLogger.info("class: {}; method: {}; value: {}", className, methodName, result);
//        System.out.println("serviceLoggingAspect");
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logServiceMethodException(JoinPoint joinPoint, Exception exception) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        serviceLogger.warn("class: {}; method: {}; exception: {}", className, methodName, exception.getMessage());
    }
}