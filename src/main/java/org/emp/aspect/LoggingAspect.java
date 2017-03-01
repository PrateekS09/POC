package org.emp.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	static Logger log = Logger.getLogger(LoggingAspect.class.getName());


	@Before("execution(* org.emp.*.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {

		log.info("Executing : "+joinPoint.getSignature().getName());
	}

	@After("execution(* org.emp.*.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		log.info("Execution complete :"+joinPoint.getSignature().getName());
	}
}