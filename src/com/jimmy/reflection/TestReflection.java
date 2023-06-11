package com.jimmy.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;


public class TestReflection {
	/**
	 * java.lang.Class：是反射的源头
	 * 我们创建了一个类，通过编译(javac.exe)，生成对应的.class文件。之后我们使用java.exe（类加载器）来加载
	 * 此.class文件，此.class文件加载到内存以后，就是一个运行时类，存放在运行时内存区那么这个运行时类本身就是一个Class的实例
	 * 1.每一个运行时类只加载一次
	 * 2.有了Class的实例以后，我们才可以进行如下操作：
	 * 	1)*创建对应的运行时类的对象
	 * 	2)获取对应的运行时类的完整结构(属性、方法、构造器、内部类、父类、所在的包、异常、注解...) 
	 *  3)*调用对应的运行时类的指定的结构(属性、方法、构造器)
	 *  4)反射的应用：动态代理
	 */
	
	@Test
	public void test(){
		Person p = new Person();
		p.setAge(25);
		p.setName("王熙斌");
		System.out.println(p);
		p.display("CHINA");
	}
	
	@Test
	public void test1() throws Exception{
		//下面的两行代码就==Person person = new Persion();
		Class<Person> clazz = Person.class;
		//1.创建clazz对应的运行时类Person类的对象
//		Person p = clazz.newInstance();
//		System.out.println(p);
		//2.获取到某个对象中的某个变量
		//2.1如果变量是public则可以获取到该变量，如果为protect/private改方法会报错
		Field name = clazz.getField("name");
//		name.set(p, "LiuDehua");
//		System.out.println(p);
		//2.2
		Field age = clazz.getDeclaredField("age");
//		age.setAccessible(true);
//		age.set(p, 50);
//		System.out.println(p);
		//3.通过反射调用运行时类的指定方法
		Method display = clazz.getMethod("display",String.class);
//		display.invoke(p,"America");
 	}
	
	@Test
	public void test2() throws Exception{
		Person p = new Person();
		//1.通过运行时类的对象，调用其getClass(),返回其运行时类
		Class clazz = p.getClass();
		//1.创建clazz对应的运行时类Person类的对象
		System.out.println(p);
		//2.获取到某个对象中的某个变量
		//2.1如果变量是public则可以获取到该变量，如果为protect/private改方法会报错
		Field name = clazz.getField("name");
		name.set(p, "LiuDehua");
		System.out.println(p);
		//2.2
		Field age = clazz.getDeclaredField("age");
		age.setAccessible(true);
		age.set(p, 50);
		System.out.println(p);
		//3.通过反射调用运行时类的指定方法
		Method display = clazz.getMethod("display",String.class);
		display.invoke(p,"America");
		Object obj = new Object();
	}
	
	@Test
	public void test3() throws Exception{
		//1.调用运行时类本身的.class属性
		Class clazz = Person.class;
		System.out.println("Clazz实例：" + clazz);
		System.out.println(clazz.getName());
		
		Class clazz1 = String.class;
		System.out.println("Clazz1实例：" + clazz1);
		System.out.println(clazz1.getName());
		//2.通过运行时类的对象获取
		Person person = new Person("Jimmy", 25);
		Class clazz2 = person.getClass();
		System.out.println("Clazz2实例：" + clazz2);
		System.out.println(clazz2.getName());
		//3.通过Class.forName()来获取Class实例
		//我们以后可以通过传入的类的完整包名，动态获取类，并且代码可以不用改动。
		String className = "com.jimmy.reflection.Person";
		Class clazz3 = Class.forName(className);
		System.out.println("Clazz3实例：" + clazz3);
		System.out.println(clazz3.getName());
		//4.(了解)通过类加载器
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class clazz4 = classLoader.loadClass(className);
		System.out.println("Clazz4实例：" + clazz4);
		System.out.println(clazz4.getName());
	}
	
	/**
	 * 类加载器分为：
	 *  1)引导类类加载器：用C++编写，是JVM自带的类加载器，用来加载核心类库，无法直接获取到实例
	 *  2)扩展类加载器：负责jre/lib/ext或-D java.ext.dirs指定目录下的jar包装入工作库
	 *  3)系统类加载器：负责java -classpath或 -D java.class.path所指的目录下的类与jar包的加载，是最常用的类加载器
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception{
		//1.获取系统类加载器
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		//结果类似于：sun.misc.Launcher$AppClassLoader@6d06d69c
		System.out.println(classLoader);
		System.out.println(classLoader.getClass().getName());
		//2.获取扩展类加载器。从这儿可以看出系统类加载器的上一层就是扩展类加载器
		ClassLoader classLoader2 = classLoader.getParent();
		System.out.println(classLoader2);
		System.out.println(classLoader2.getClass().getName());
		//3.获取引导类加载器。由于引导类加载器不能被获取到实例，所以结果为null
		ClassLoader classLoader3 = classLoader2.getParent();
		System.out.println(classLoader3);
		//4.自定义类获取系统类加载器。可以看到这里的系统类加载器跟1.中的是同一个实例
		Class clazz = Person.class;
		ClassLoader classLoader4 = clazz.getClassLoader();
		System.out.println(classLoader4);
		System.out.println(classLoader4.getClass().getName());
		//5.核心类获取到的类加载器是引导类加载器，所以结果为null
		Class clazz1 = ArrayList.class;
		ClassLoader classLoader5 = clazz1.getClassLoader();
		System.out.println(classLoader5);
		//6.使用ClassLoader读取properties配置文件
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream inputStream = loader.getResourceAsStream("com/jimmy/reflection/jdbc.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String name = properties.getProperty("name");
		String password = properties.getProperty("password");
		System.out.println("name:" + name + "\n" + "password:" + password);
		//7.使用FileInputStream来去读jdbc.properties文件
		FileInputStream fileInputStream = new FileInputStream(new File("res/jdbc.properties"));
		Properties properties1= new Properties();
		properties1.load(fileInputStream);
		String name1 = properties.getProperty("name");
		String password1= properties.getProperty("password");
		System.out.println("name:" + name1 + "\n" + "password:" + password1);
	}
	
	
}
