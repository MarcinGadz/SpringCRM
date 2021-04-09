package com.marcingadz.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // pointcuts
    @Pointcut("execution(* com.marcingadz.controller.*.*(..))")
    private void controllerPackage() {
    }

    @Pointcut("execution(* com.marcingadz.service.*.*(..))")
    private void servicePackage() {
    }

    @Pointcut("execution(* com.marcingadz.dao.*.*(..))")
    private void DAOPackage() {
    }

    @Pointcut("controllerPackage() || servicePackage() || DAOPackage()")
    private void appFlow() {
    }

    // advices
    @Before("appFlow()")
    private void before(JoinPoint point) {

        // log method name
        logger.info("@Before -> Calling: " + point.getSignature().toShortString());

        // log arguments
        for (Object o : point.getArgs()) {
            logger.info("Argument -> " + o);
        }
    }

    @AfterReturning(
            pointcut = "appFlow()",
            returning = "res")
    private void after(JoinPoint point, Object res) {

        // method name
        logger.info("@After -> " + point.getSignature().toShortString());

        //returned data
        logger.info("Returning -> " + res);
    }
}
