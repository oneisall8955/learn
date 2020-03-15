package com.oneisall.learn.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 前端传递参数绑定到后端日期对象的解析格式注解
 * <p>
 * 或
 * <p>
 * 后端序列化到前端格式化注解
 *
 * @author liam
 * @version V1.0
 * @date 2019/12/6
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DateFormatter {
  String pattern() default "yyyy-MM-dd";
}
