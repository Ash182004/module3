package com.app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.app.service.BookServiceImpl.*(..))")
    public void logBefore(JoinPoint jp) {
        logger.info("Before method: " + jp.getSignature());
    }

    @After("execution(* com.app.service.BookServiceImpl.*(..))")
    public void logAfter(JoinPoint jp) {
        logger.info("After method: " + jp.getSignature());
    }
    
    @AfterReturning(
    	    value = "execution(* com.app.service.BookServiceImpl.getBookById(..))",
    	    returning = "result")
    	public void logReturn(Object result) {
    	    logger.info("Method returned: " + result);
    	}
    
    @AfterThrowing(
    	    value = "execution(* com.app.service.BookServiceImpl.*(..))",
    	    throwing = "ex")
    	public void logException(Exception ex) {
    	    logger.error("Exception thrown: " + ex.getMessage());
    	}
    
    @Around("execution(* com.app.service.BookServiceImpl.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = pjp.proceed();

        long end = System.currentTimeMillis();

        logger.info(
            "Execution Time of " + pjp.getSignature() +
            ": " + (end - start) + "ms"
        );

        return result;
    }
}
