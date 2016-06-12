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
		// 获取默认类型池对象
		ClassPool classPool = ClassPool.getDefault();
		// 获取指定的类型
		CtClass ctClass = classPool.get("java.lang.String");

		System.out.println(ctClass.getName()); // 获取类名
		System.out.println("\tpackage " + ctClass.getPackageName()); // 获取包名
		System.out.print("\t" + Modifier.toString(ctClass.getModifiers()) + " class " + ctClass.getSimpleName()); // 获取限定符和简要类名
		System.out.print(" extends " + ctClass.getSuperclass().getName()); // 获取超类

		// 获取接口
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
