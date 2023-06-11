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
	 * java.lang.Class���Ƿ����Դͷ
	 * ���Ǵ�����һ���࣬ͨ������(javac.exe)�����ɶ�Ӧ��.class�ļ���֮������ʹ��java.exe�����������������
	 * ��.class�ļ�����.class�ļ����ص��ڴ��Ժ󣬾���һ������ʱ�࣬���������ʱ�ڴ�����ô�������ʱ�౾�����һ��Class��ʵ��
	 * 1.ÿһ������ʱ��ֻ����һ��
	 * 2.����Class��ʵ���Ժ����ǲſ��Խ������²�����
	 * 	1)*������Ӧ������ʱ��Ķ���
	 * 	2)��ȡ��Ӧ������ʱ��������ṹ(���ԡ����������������ڲ��ࡢ���ࡢ���ڵİ����쳣��ע��...) 
	 *  3)*���ö�Ӧ������ʱ���ָ���Ľṹ(���ԡ�������������)
	 *  4)�����Ӧ�ã���̬����
	 */
	
	@Test
	public void test(){
		Person p = new Person();
		p.setAge(25);
		p.setName("������");
		System.out.println(p);
		p.display("CHINA");
	}
	
	@Test
	public void test1() throws Exception{
		//��������д����==Person person = new Persion();
		Class<Person> clazz = Person.class;
		//1.����clazz��Ӧ������ʱ��Person��Ķ���
//		Person p = clazz.newInstance();
//		System.out.println(p);
		//2.��ȡ��ĳ�������е�ĳ������
		//2.1���������public����Ի�ȡ���ñ��������Ϊprotect/private�ķ����ᱨ��
		Field name = clazz.getField("name");
//		name.set(p, "LiuDehua");
//		System.out.println(p);
		//2.2
		Field age = clazz.getDeclaredField("age");
//		age.setAccessible(true);
//		age.set(p, 50);
//		System.out.println(p);
		//3.ͨ�������������ʱ���ָ������
		Method display = clazz.getMethod("display",String.class);
//		display.invoke(p,"America");
 	}
	
	@Test
	public void test2() throws Exception{
		Person p = new Person();
		//1.ͨ������ʱ��Ķ��󣬵�����getClass(),����������ʱ��
		Class clazz = p.getClass();
		//1.����clazz��Ӧ������ʱ��Person��Ķ���
		System.out.println(p);
		//2.��ȡ��ĳ�������е�ĳ������
		//2.1���������public����Ի�ȡ���ñ��������Ϊprotect/private�ķ����ᱨ��
		Field name = clazz.getField("name");
		name.set(p, "LiuDehua");
		System.out.println(p);
		//2.2
		Field age = clazz.getDeclaredField("age");
		age.setAccessible(true);
		age.set(p, 50);
		System.out.println(p);
		//3.ͨ�������������ʱ���ָ������
		Method display = clazz.getMethod("display",String.class);
		display.invoke(p,"America");
		Object obj = new Object();
	}
	
	@Test
	public void test3() throws Exception{
		//1.��������ʱ�౾���.class����
		Class clazz = Person.class;
		System.out.println("Clazzʵ����" + clazz);
		System.out.println(clazz.getName());
		
		Class clazz1 = String.class;
		System.out.println("Clazz1ʵ����" + clazz1);
		System.out.println(clazz1.getName());
		//2.ͨ������ʱ��Ķ����ȡ
		Person person = new Person("Jimmy", 25);
		Class clazz2 = person.getClass();
		System.out.println("Clazz2ʵ����" + clazz2);
		System.out.println(clazz2.getName());
		//3.ͨ��Class.forName()����ȡClassʵ��
		//�����Ժ����ͨ����������������������̬��ȡ�࣬���Ҵ�����Բ��øĶ���
		String className = "com.jimmy.reflection.Person";
		Class clazz3 = Class.forName(className);
		System.out.println("Clazz3ʵ����" + clazz3);
		System.out.println(clazz3.getName());
		//4.(�˽�)ͨ���������
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class clazz4 = classLoader.loadClass(className);
		System.out.println("Clazz4ʵ����" + clazz4);
		System.out.println(clazz4.getName());
	}
	
	/**
	 * ���������Ϊ��
	 *  1)�����������������C++��д����JVM�Դ�������������������غ�����⣬�޷�ֱ�ӻ�ȡ��ʵ��
	 *  2)��չ�������������jre/lib/ext��-D java.ext.dirsָ��Ŀ¼�µ�jar��װ�빤����
	 *  3)ϵͳ�������������java -classpath�� -D java.class.path��ָ��Ŀ¼�µ�����jar���ļ��أ�����õ��������
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception{
		//1.��ȡϵͳ�������
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		//��������ڣ�sun.misc.Launcher$AppClassLoader@6d06d69c
		System.out.println(classLoader);
		System.out.println(classLoader.getClass().getName());
		//2.��ȡ��չ�����������������Կ���ϵͳ�����������һ�������չ�������
		ClassLoader classLoader2 = classLoader.getParent();
		System.out.println(classLoader2);
		System.out.println(classLoader2.getClass().getName());
		//3.��ȡ�������������������������������ܱ���ȡ��ʵ�������Խ��Ϊnull
		ClassLoader classLoader3 = classLoader2.getParent();
		System.out.println(classLoader3);
		//4.�Զ������ȡϵͳ������������Կ��������ϵͳ���������1.�е���ͬһ��ʵ��
		Class clazz = Person.class;
		ClassLoader classLoader4 = clazz.getClassLoader();
		System.out.println(classLoader4);
		System.out.println(classLoader4.getClass().getName());
		//5.�������ȡ�����������������������������Խ��Ϊnull
		Class clazz1 = ArrayList.class;
		ClassLoader classLoader5 = clazz1.getClassLoader();
		System.out.println(classLoader5);
		//6.ʹ��ClassLoader��ȡproperties�����ļ�
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream inputStream = loader.getResourceAsStream("com/jimmy/reflection/jdbc.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String name = properties.getProperty("name");
		String password = properties.getProperty("password");
		System.out.println("name:" + name + "\n" + "password:" + password);
		//7.ʹ��FileInputStream��ȥ��jdbc.properties�ļ�
		FileInputStream fileInputStream = new FileInputStream(new File("res/jdbc.properties"));
		Properties properties1= new Properties();
		properties1.load(fileInputStream);
		String name1 = properties.getProperty("name");
		String password1= properties.getProperty("password");
		System.out.println("name:" + name1 + "\n" + "password:" + password1);
	}
	
	
}
