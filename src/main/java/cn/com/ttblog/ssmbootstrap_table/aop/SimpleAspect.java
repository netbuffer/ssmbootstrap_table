package cn.com.ttblog.ssmbootstrap_table.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SimpleAspect {
	private Logger log = LoggerFactory.getLogger(getClass());
	//http://jinnianshilongnian.iteye.com/blog/1415606  切入点语法
	@Pointcut("execution(* cn.com.ttblog.ssmbootstrap_table.service.*Service*.*(..))")
	public void pointCut() {
	}

	@After("pointCut()")
	public void after(JoinPoint joinPoint) {
		log.warn("after aspect executed");
	}

	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		// 如果需要这里可以取出参数进行处理
		// Object[] args = joinPoint.getArgs();
		log.warn("before aspect executing param:{}",ToStringBuilder.reflectionToString(joinPoint));
		Object[] arr=joinPoint.getArgs();
		if(arr.length>0){
			log.warn("method param:{}",ToStringBuilder.reflectionToString(joinPoint.getArgs()[0]));
		}
	}

	@AfterReturning(pointcut = "pointCut()", returning = "returnVal")
	public void afterReturning(JoinPoint joinPoint, Object returnVal) {
		log.warn("afterReturning executed, return result is " + returnVal);
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		log.warn("around start..");
		Object obj = null;
		long startTime=System.nanoTime();
		long endTime;
		try {
			obj = pjp.proceed();
			endTime=System.nanoTime();
		} catch (Throwable ex) {
			log.warn("error in around");
			throw ex;
		}
		log.warn("around end");
		log.warn(pjp.getSignature()+"-time consume is " + (endTime-startTime)/1000/1000/1000*0.1+"s");
		return obj;
	}

	@AfterThrowing(pointcut = "pointCut()", throwing = "error")
	public void afterThrowing(JoinPoint jp, Throwable error) {
		log.error("error:" + error);
	}
}