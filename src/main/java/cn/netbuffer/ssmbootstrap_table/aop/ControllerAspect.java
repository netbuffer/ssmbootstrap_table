package cn.netbuffer.ssmbootstrap_table.aop;

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
public class ControllerAspect {

	private static final Logger LOG = LoggerFactory
			.getLogger(ControllerAspect.class);

	@Pointcut("execution(* cn.netbuffer.ssmbootstrap_table.controller..*.*(..))")
	public void pointCut() {
	}

	@After("pointCut()")
	public void after(JoinPoint joinPoint) {
	}

	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		// Object[] args = joinPoint.getArgs();
		LOG.warn("控制器切面参数 param:{}",
				ToStringBuilder.reflectionToString(joinPoint));
		Object[] arr = joinPoint.getArgs();
		if (arr.length > 0) {
			LOG.warn("method param:{}",
					ToStringBuilder.reflectionToString(joinPoint.getArgs()[0]));
		}
	}

	@AfterReturning(pointcut = "pointCut()", returning = "returnVal")
	public void afterReturning(JoinPoint joinPoint, Object returnVal) {
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		long startTime = System.nanoTime();
		long endTime;
		try {
			obj = pjp.proceed();
			endTime = System.nanoTime();
		} catch (Throwable ex) {
			throw ex;
		}
		LOG.warn(pjp.getSignature() + "-time consume is "
				+ (endTime - startTime) / 1000 / 1000 / 1000 * 0.1 + "s");
		return obj;
	}

	@AfterThrowing(pointcut = "pointCut()", throwing = "error")
	public void afterThrowing(JoinPoint jp, Throwable error) {
		LOG.error("控制器发生错误:" + error);
	}
}
