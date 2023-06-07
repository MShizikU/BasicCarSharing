package ru.mirea.SidorovSD;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class AOPAspect {

    @Around("allServiceMethods()")
    public Object logParamsAndTime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature().toShortString());
        log.info("Method: " + joinPoint.getSignature().getName() +" from "+joinPoint.getTarget().getClass()+ joinPoint.getArgs()+
                " persist for "+(end-start)+" ms"+" with parametrs{}: "+ Arrays.toString(joinPoint.getArgs()));
        return  result;
    }
    @Pointcut("within(ru.mirea.SidorovSD.services.*)")
    public void allServiceMethods() {}

}
