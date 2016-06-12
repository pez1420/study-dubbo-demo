package com.study.spring.moni;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME) // ָ��ע�Ᵽ���ķ�Χ (������)
@Target({ ElementType.FIELD, ElementType.METHOD }) // ����ע���ע��λ�� (����, ����)
public @interface MyResource {
	public String name() default ""; // �ṩname����
}