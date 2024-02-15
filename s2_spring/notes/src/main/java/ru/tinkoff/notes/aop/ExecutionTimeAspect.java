package ru.tinkoff.notes.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ExecutionTimeAspect {
    @Pointcut("@annotation(ExecutionTime)")
    public void executionTimePointcut(){
    }

    @Around("@annotation(ExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endTime = System.currentTimeMillis();
        log.info(point.getSignature().getDeclaringTypeName() +"."+ point.getSignature().getName() + " " + (endTime-startTime) +"ms");
        return object;
    }
}

