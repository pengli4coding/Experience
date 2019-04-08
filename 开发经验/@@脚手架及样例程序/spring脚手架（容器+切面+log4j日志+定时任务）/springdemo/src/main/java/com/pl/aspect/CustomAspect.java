package com.pl.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 自定义切面
 */
@Order(1)// 如果有多个切面的话，可以使用@Order注解指定切面的执行优先级，值越小优先级越高
@Aspect// 通过注解的方式声明该类为一个切面
@Component// 切面也需要加入到IoC容器的管理中
public class CustomAspect {

    private static final Logger log = LogManager.getLogger();

    /**
     * 前置通知是在目标方法执行之前执行的
     *
     * @param joinPoint
     */
    @Before("execution(* com.pl.dao.impl..*.*(..))")
    public void before(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getName();// 获取被代理对象的类名
        String targetMethodName = joinPoint.getSignature().getName();// 获取被代理对象的执行方法名
        log.info("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行之前做一些处理");
    }


    /**
     * 后置通知是无论目标方法执行过程中是否发生异常都会执行的
     *
     * @param joinPoint
     */
    @After("execution(* com.pl.dao.impl..*.*(..))")
    public void after(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getName();// 获取被代理对象的类名
        String targetMethodName = joinPoint.getSignature().getName();// 获取被代理对象的执行方法名
        log.info("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行（无论是否发生异常）之后做一些处理");
    }

    /**
     * 返回通知是在目标方法正常执行（无异常发生）并返回结果之后执行的
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "execution(* com.pl.dao.impl..*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String targetClassName = joinPoint.getTarget().getClass().getName();// 获取被代理对象的类名
        String targetMethodName = joinPoint.getSignature().getName();// 获取被代理对象的执行方法名
        log.info("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行（无异常发生）并返回结果之后做一些处理，返回的结果为：" + result);
    }

    /**
     * 异常通知是在目标方法执行过程中发生异常的时候执行的
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "execution(* com.pl.dao.impl..*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, NullPointerException ex) {// 注意这里捕获的异常可以是Throwable，也可以是指定类型的异常
        String targetClassName = joinPoint.getTarget().getClass().getName();// 获取被代理对象的类名
        String targetMethodName = joinPoint.getSignature().getName();// 获取被代理对象的执行方法名
        log.info("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行过程中发生异常的时候做一些处理，异常为：" + ex);
    }


    @Around(value = "execution(* com.pl.dao.impl..*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        String targetClassName = proceedingJoinPoint.getTarget().getClass().getName();
        String targetMethodName = proceedingJoinPoint.getSignature().getName();
        try {
            // 执行前置通知
            log.info("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行之前做一些处理");
            // 执行目标方法
            result = proceedingJoinPoint.proceed();
            // 执行返回通知
            log.info("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行（无异常发生）并返回结果之后做一些处理，返回的结果为：" + result);
        } catch (Throwable ex) {
            // 执行异常通知
            log.error("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行过程中发生异常的时候做一些处理，异常为：" + ex);
        }
        // 执行后置通知
        log.info("对目标对象" + targetClassName + "的" + targetMethodName + "方法执行（无论是否发生异常）之后做一些处理");
        return result;
    }

}
