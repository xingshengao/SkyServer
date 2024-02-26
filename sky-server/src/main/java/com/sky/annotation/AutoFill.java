package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 用来表示方法需要功能字段的自动填充处理
 */
@Target(ElementType.METHOD) // 注解只能加在方法上
@Retention(RetentionPolicy.RUNTIME)

public @interface AutoFill {
    // 指定当前数据库的操作类型, Update和Insert
    OperationType value();
}