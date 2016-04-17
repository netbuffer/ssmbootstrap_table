package cn.com.ttblog.ssmbootstrap_table.aop;

import org.apache.commons.lang.builder.ToStringBuilder;
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

	@Pointcut("execution(* cn.com.ttblog.ssmbootstrap_table.service.*Service*.*(..))")
	public void pointCut() {
	}

	@After("pointCut()")
	public void after(JoinPoint joinPoint) {
		log.debug("after aspect executed");
	}

	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		// 如果需要这里可以取出参数进行处理
		// Object[] args = joinPoint.getArgs();
		log.debug("before aspect executing param:{}",ToStringBuilder.reflectionToString(joinPoint));
		log.debug("method param:{}",ToStringBuilder.reflectionToString(joinPoint.getArgs()[0]));
	}

	@AfterReturning(pointcut = "pointCut()", returning = "returnVal")
	public void afterReturning(JoinPoint joinPoint, Object returnVal) {
		log.debug("afterReturning executed, return result is " + returnVal);
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		log.debug("around start..");
		Object obj = null;
		try {
			obj = pjp.proceed();
		} catch (Throwable ex) {
			log.debug("error in around");
			throw ex;
		}
		log.debug("around end");
		return obj;
	}

	@AfterThrowing(pointcut = "pointCut()", throwing = "error")
	public void afterThrowing(JoinPoint jp, Throwable error) {
		log.error("error:" + error);
	}
}
