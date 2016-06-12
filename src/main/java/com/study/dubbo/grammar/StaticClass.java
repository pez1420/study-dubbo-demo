package com.study.dubbo.grammar;

/***
 * 当执行new Child()时，它首先去看父类里面有没有静态代码块，
 * 如果有，它先去执行父类里面静态代码块里面的内容，当父类的静态代码块里面的内容执行完毕之后，
 * 接着去执行子类(自己这个类)里面的静态代码块，当子类的静态代码块执行完毕之后，它接着又去看父类有没有非静态代码块，
 * 如果有就执行父类的非静态代码块，父类的非静态代码块执行完毕，接着执行父类的构造方法；
 * 父类的构造方法执行完毕之后，它接着去看子类有没有非静态代码块，如果有就执行子类的非静态代码块。
 * 子类的非静态代码块执行完毕再去执行子类的构造方法，这个就是一个对象的初始化顺序
 * 
 * @author Administrator
 *
 */

/*
 * 总之一句话，静态代码块内容先执行，接着执行父类非静态代码块和构造方法，然后执行子类非静态代码块和构造方法
 * 
 * 在Java中，类装载器把一个类装入Java虚拟机中，要经过三个步骤来完成：
 * 	装载、链接和初始化，其中链接又可以分成校验、准备和解析三步，除了解析外，其它步骤是严格按照顺序完成的，
 *  各个步骤的主要工作如下：

	装载：查找和导入类或接口的二进制数据；
	链接：执行下面的校验、准备和解析步骤，其中解析步骤是可以选择的；
	校验：检查导入类或接口的二进制数据的正确性；
	准备：给类的静态变量分配并初始化存储空间；
	解析：将符号引用转成直接引用；
	初始化：激活类的静态变量的初始化Java代码和静态Java代码块。
	初始化类中属性是静态代码块的常用用途，但只能使用一次。
 */

/***
 * 静态代码块，在虚拟机加载类的时候就会加载执行，而且只执行一次；
 * 非静态代码块，在创建对象的时候（即new一个对象的时候）执行，每次创建对象都会执行一次
 *
 * @author Administrator
 *
 */
class Parent {
	static String name = "hello";

	{
		System.out.println("parent block");
	}

	static {
		System.out.println("parent static block");
	}

	public Parent() {
		System.out.println("parent constructor");
	}
}

class Child extends Parent {
	static String childName = "hello";

	{
		System.out.println("child block");
	}

	static {
		System.out.println("child static block");
	}

	public Child() {
		System.out.println("child constructor");
	}
}

public class StaticClass {
	public static void main(String[] args) {
		new Child();
		//静态代码块，在虚拟机加载类的时候就会加载执行，而且只执行一次；
		System.out.println("-------------------------");
		new Child();
	}
}
