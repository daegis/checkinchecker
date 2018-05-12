package cn.aegisa.project.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Using IntelliJ IDEA.
 *
 * @author HNAyd.xian at 2018/3/23 10:42
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("@annotation(SystemLog)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕通知触发");
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        String message = systemLog.message();
        int type = systemLog.type();
        log.info("message:{},type:{}", message, type);
        Object proceed = joinPoint.proceed();
        Object[] args1 = joinPoint.getArgs();
        System.out.println(proceed);
        return proceed;
    }

}
