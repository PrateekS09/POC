package org.emp.aspect;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;


public class ExecutionAspect {
	
   static Logger log = Logger.getLogger(ExecutionAspect.class.getName());

	public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(proceedingJoinPoint.toShortString());
		boolean isExceptionThrown = false;
		try {
			return proceedingJoinPoint.proceed();
		} catch (RuntimeException e) {
			isExceptionThrown = true;
			throw e;
		} finally {
			stopWatch.stop();
			TaskInfo taskInfo = stopWatch.getLastTaskInfo();
			// Log the method's profiling result
			String duration = taskInfo.getTaskName() + ": " + taskInfo.getTimeMillis() + " ms" +
					(isExceptionThrown ? " (thrown Exception)" : "");
			log.info(duration);
		}
	}
	
}