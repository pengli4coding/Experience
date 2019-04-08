package com.pl.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class ValidateAspect {

    private static final Logger log = LogManager.getLogger();


    public void before(JoinPoint joinPoint){
        log.info("下订单之前做一些验证处理");
    }

    public void after(JoinPoint joinPoint){
        log.info("下订单之后做一些验证处理");
    }

}
