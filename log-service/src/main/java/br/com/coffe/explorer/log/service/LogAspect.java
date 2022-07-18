package br.com.coffe.explorer.log.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Around("within(br.com.coffe.explorer..*)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info(proceedingJoinPoint.getSignature().getName() + " called");
        return proceedingJoinPoint.proceed();
    }


}
