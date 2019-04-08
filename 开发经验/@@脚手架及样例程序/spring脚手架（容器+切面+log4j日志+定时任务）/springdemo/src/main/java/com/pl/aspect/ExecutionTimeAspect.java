package com.pl.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    private static final Logger log = LogManager.getLogger();

    // 指定一个可重用的切入点表达式
    @Pointcut("execution(* com.pl.service.impl..*.*(..))")
    public void expression() {
    }

    @Around("expression()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        long start = System.currentTimeMillis();
        Object result = null;
        String targetClassName = proceedingJoinPoint.getTarget().getClass().getName();
        String targetMethodName = proceedingJoinPoint.getSignature().getName();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行耗时为：" + (System.currentTimeMillis() - start) + "毫秒");
        return result;
    }

}
