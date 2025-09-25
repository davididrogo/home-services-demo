package com.example.homesvc.observability;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimingAspect {
    @Around("@annotation(com.example.homesvc.observability.Monitored)")
    public Object time(ProceedingJoinPoint pjp, Monitored monitored) throws Throwable {
        long t0 = System.nanoTime();
        try{
            return pjp.proceed();
        }finally {
            long ms = (System.nanoTime() - t0) / 1_000_000;
            Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
            log.info("[MON] {}.{} took {} ms",
                    pjp.getSignature().getDeclaringType().getSimpleName(),
                    pjp.getSignature().getName(), ms);
        }
    }

}
