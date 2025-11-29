package com.example.stock_manager.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class ExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Around("execution(* com.example.stock_manager.product.ProductService..*(..))")
    public Object  execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long executionTime = end - start;

        System.out.println("Method {} executed in {} ms"+ joinPoint.getSignature()+ executionTime);
        logger.info("Method {} executed in {} ms  ",
                joinPoint.getSignature(), executionTime);

        return result;
    }
}
