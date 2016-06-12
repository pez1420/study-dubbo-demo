package com.study.dubbo.aop.javaassist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

//http://youaremoon.iteye.com/blog/2279785
public class JavaassistCompiler {

	private static final Pattern DIGIT_PATTERN = Pattern.compile("(.*)(\\d+)(.*)");

	@Test
	public void test_grep() {
		String text = "<textarea rows=\"20\" cols=\"70\">nexus maven repository index properties updating index central</textarea>";
		// �����������ʽ�й����ĸ������飺(<textarea.*?>)��(.*?)��(</textarea>)������ƥ�䵽������
		String reg = "(<textarea.*?>)(.*?)(</textarea>)";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(text);
		while (m.find()) {
			System.out.println(m.group(0)); // ����ƥ�䵽������
			System.out.println(m.group(1)); // (<textarea.*?>)
			System.out.println(m.group(2)); // (.*?)
			System.out.println(m.group(3)); // (</textarea>)
		}
	}

	@Test
	public void test_javaassit() {
		// ͨ��getDefault()���ص�ClassPool������ϵͳ��Ĭ������·��
		// ��̬����ClassPool.getDefault()���ص�Ĭ��ClassPool��������·���͵�ǰ��JVM����һ��
		// ClassPool pool = ClassPool.getDefault();
		ClassPool pool = new ClassPool(true);
		CtClass cc;
		try {
			cc = pool.get("com.study.dubbo.aop.javaassist.Rectangle");
			cc.setSuperclass(pool.get("com.study.dubbo.aop.javaassist.Point"));
			Class<?> clazz = cc.toClass();
			Rectangle r = (Rectangle) clazz.newInstance();
			Assert.assertTrue(r.getClass().getGenericSuperclass() == Point.class);
			// System.out.println(r.getClass().getGenericSuperclass().hashCode());
			// System.out.println(Point.class.hashCode());
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_makeClass() {
		ClassPool pool = new ClassPool(true);
		CtClass ctClass = pool.makeClass("com.study.dubbo.aop.javaassist.Website");
		FileOutputStream fos = null;

		try {
			// ���һ������
			CtField idCtField = new CtField(CtClass.intType, "id", ctClass);
			idCtField.setModifiers(Modifier.PUBLIC);
			ctClass.addField(idCtField);

			// �����ɵ�class�ļ�д���ļ�
			byte[] byteArr = ctClass.toBytecode();
			fos = new FileOutputStream(new File("D://Website.class"));
			fos.write(byteArr);

			ctClass.defrost();

			// Ϊ�˲���ctClass�Ƿ��ܹ����޸ģ������һ����
			CtField namecCtField = new CtField(pool.get("java.lang.String"), "name", ctClass);
			namecCtField.setModifiers(Modifier.PUBLIC);
			ctClass.addField(namecCtField);

			byteArr = ctClass.toBytecode();
			fos = new FileOutputStream(new File("D://Website.class"));
			fos.write(byteArr);

			Assert.assertTrue("com.study.dubbo.aop.javaassist.Website".equals(ctClass.getName()));

		} catch (CannotCompileException | IOException | NotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test
	public void test_classForName() {
		try {
			Class<?> clazz = Class.forName("com.study.dubbo.aop.javaassist.Point", true,
					Thread.currentThread().getContextClassLoader());
			System.out.println(clazz.getName());
			System.out.println(clazz.getPackage().getName());
			// new
			// LoaderClassPath(Thread.currentThread().getContextClassLoader())
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		;
	}

	@Test
	public void test_change_method_name() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
		//http://blog.sina.com.cn/s/blog_62ef2f140102v5o7.html
		// ��ȡcom.hankcs.A�����
		CtClass cc = ClassPool.getDefault().get("com.study.dubbo.aop.javaassist.A");
		// ��ȡA���е�foo����
		CtMethod method = cc.getDeclaredMethod("foo");
		// ����ȡ�����֣��������Ҫ����
		method.setName("foo$impl");
		// ��foo�������Ƴ�һ���µķ������·�����Ԫ����һ��һ��
		CtMethod newMethod = CtNewMethod.copy(method, "foo", cc, null);
		// ���ִ�д��̬����
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("    long start = System.currentTimeMillis();\n");
		sb.append("    foo$impl($$);\n");
		sb.append("    long end = System.currentTimeMillis();\n");
		sb.append("    System.out.println(\"Time interval = \" + (end - start) + \"ms\");\n");
		sb.append("}\n");
		// ����̬������ΪnewMethod��Body
		newMethod.setBody(sb.toString());
		// ��̬���뵽��
		cc.addMethod(newMethod);
		// ����ʵ��
		A a = (A) cc.toClass().newInstance();
		// ���Time interval =
		a.foo(10000000);
		// ��Ȼ���Time interval =
		A b = new A();
		b.foo(10000000);
	}
}
