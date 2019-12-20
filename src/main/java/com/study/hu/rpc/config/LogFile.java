package com.study.hu.rpc.config;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogFile {

    // 调用的类
    Class<?> beanClass();
    // 传入的参数
    String params();
    // 调用类中的方法
    String method();

}
