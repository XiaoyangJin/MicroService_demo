package com.example.university.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.university.service.impl.UniversityServiceImpl.*(..))")
    public void logBefore() {
        log.info("Starting method execution");
    }

    @AfterReturning(pointcut = "execution(* com.example.university.service.impl.UniversityServiceImpl.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        log.info("Method executed successfully, returning: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.university.service.impl.UniversityServiceImpl.*(..))", throwing = "error")
    public void logAfterThrowing(Throwable error) {
        log.error("An error occurred: " + error.getMessage());
    }
}
