package com.jcl.burpspread.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Faker
 * @version 1.0
 * @since 2020-01-03 15:46:24
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class LogAspect {
    @Around(value = "@annotation(com.jcl.burpspread.annotation.LogAnnotation)")
    public Object aroundServiceMethodCache(ProceedingJoinPoint pjp) throws Throwable {

        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        String className = pjp.getTarget().getClass().getName();
        String methodName = signature.getName();
        String[] paramNames = methodSignature.getParameterNames();
        Object[] args = pjp.getArgs();

        List<String> paramList = new ArrayList<>();
        for (int i = 0; i < paramNames.length; i++) {
            String paramName = paramNames[i];
            Object arg = args[i];
            if (arg != null) {
                paramList.add(String.format("%s: %s", paramName, arg.toString()));
            }
        }

        log.info("className: {}, methodName: {}, param: {}", className, methodName, String.join(", ", paramList));
        long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.info("className: {}, methodName: {}, return: {},耗时: {}", className, methodName, JSON.toJSON(proceed),endTime-startTime);
        return proceed;
    }
}
