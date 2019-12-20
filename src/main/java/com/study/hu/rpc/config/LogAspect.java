package com.study.hu.rpc.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @Author hudongdong
 * @Date 2019/12/20 15:51
 */
@Component
@Aspect
public class LogAspect {

    @Before("@annotion(com.study.hu.rpc.config.LogFile)")
    public void doLog(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();
        Class<?> aClass = proceed.getClass();
        LogFile annotation = aClass.getAnnotation(LogFile.class);
        if (annotation == null) {
            return;
        }
        String method = annotation.method();
        Class<?> aClass1 = annotation.beanClass();
        String params = annotation.params();
        System.out.println("作用的方法-----" + method);
        System.out.println("参数------" + params);


    }
}
