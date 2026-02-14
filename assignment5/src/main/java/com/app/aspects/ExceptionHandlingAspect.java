package com.app.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.app.exceptions.ServiceException;

@Aspect
@Component
public class ExceptionHandlingAspect {
	
	@Around("execution(* com.app.service.*Impl.*(..))")
	public Object wrapDataAccessException(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			return joinPoint.proceed();
		} catch (DataAccessException ex) {
			String methodName = joinPoint.getSignature().toShortString();
			System.out.println("Throwing Service Exception --------------------");
			throw new ServiceException(
					"Service layer failure in " + methodName, ex);
		}
	}
}
