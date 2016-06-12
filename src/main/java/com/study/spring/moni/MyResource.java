package com.study.spring.moni;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME) // 指定注解保留的范围 (运行期)
@Target({ ElementType.FIELD, ElementType.METHOD }) // 允许注解标注的位置 (属性, 方法)
public @interface MyResource {
	public String name() default ""; // 提供name属性
}