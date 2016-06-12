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
		// 下面的正则表达式中共有四个捕获组：(<textarea.*?>)、(.*?)、(</textarea>)和整个匹配到的内容
		String reg = "(<textarea.*?>)(.*?)(</textarea>)";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(text);
		while (m.find()) {
			System.out.println(m.group(0)); // 整个匹配到的内容
			System.out.println(m.group(1)); // (<textarea.*?>)
			System.out.println(m.group(2)); // (.*?)
			System.out.println(m.group(3)); // (</textarea>)
		}
	}

	@Test
	public void test_javaassit() {
		// 通过getDefault()返回的ClassPool会搜索系统的默认搜索路径
		// 静态方法ClassPool.getDefault()返回的默认ClassPool的类搜索路径和当前的JVM保持一致
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
			// 添加一个参数
			CtField idCtField = new CtField(CtClass.intType, "id", ctClass);
			idCtField.setModifiers(Modifier.PUBLIC);
			ctClass.addField(idCtField);

			// 把生成的class文件写入文件
			byte[] byteArr = ctClass.toBytecode();
			fos = new FileOutputStream(new File("D://Website.class"));
			fos.write(byteArr);

			ctClass.defrost();

			// 为了测试ctClass是否能够再修改，再添加一个域
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
		// 获取com.hankcs.A这个类
		CtClass cc = ClassPool.getDefault().get("com.study.dubbo.aop.javaassist.A");
		// 获取A类中的foo方法
		CtMethod method = cc.getDeclaredMethod("foo");
		// 重新取个名字，待会儿还要调用
		method.setName("foo$impl");
		// 将foo方法复制出一个新的方法，新方法与元方法一摸一样
		CtMethod newMethod = CtNewMethod.copy(method, "foo", cc, null);
		// 用字串写动态代码
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("    long start = System.currentTimeMillis();\n");
		sb.append("    foo$impl($$);\n");
		sb.append("    long end = System.currentTimeMillis();\n");
		sb.append("    System.out.println(\"Time interval = \" + (end - start) + \"ms\");\n");
		sb.append("}\n");
		// 将动态代码设为newMethod的Body
		newMethod.setBody(sb.toString());
		// 动态加入到类
		cc.addMethod(newMethod);
		// 生成实例
		A a = (A) cc.toClass().newInstance();
		// 输出Time interval =
		a.foo(10000000);
		// 依然输出Time interval =
		A b = new A();
		b.foo(10000000);
	}
}
