package com.study.dubbo.aop.javaassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

//https://github.com/jboss-javassist/javassist

public class JavassistTest {

	public static void test() throws NotFoundException {
		// ��ȡĬ�����ͳض���
		ClassPool classPool = ClassPool.getDefault();
		// ��ȡָ��������
		CtClass ctClass = classPool.get("java.lang.String");

		System.out.println(ctClass.getName()); // ��ȡ����
		System.out.println("\tpackage " + ctClass.getPackageName()); // ��ȡ����
		System.out.print("\t" + Modifier.toString(ctClass.getModifiers()) + " class " + ctClass.getSimpleName()); // ��ȡ�޶����ͼ�Ҫ����
		System.out.print(" extends " + ctClass.getSuperclass().getName()); // ��ȡ����

		// ��ȡ�ӿ�
		if (ctClass.getInterfaces() != null) {
			System.out.print(" implements ");
			boolean first = true;
			for (CtClass c : ctClass.getInterfaces()) {
				if (first) {
					first = false;
				} else {
					System.out.print(", ");
				}
				System.out.print(c.getName());
			}
		}
		System.out.println();
	}

	public static void main(String[] args) throws NotFoundException {
		test();
	}

}
