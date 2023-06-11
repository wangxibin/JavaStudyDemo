package com.jimmy.reflection;

public class Person {
	/**
	 * 定义私有变量
	 */
	public String name;
	private int age;

	/**
	 * 定义一个有参构造器
	 * 
	 * @param name
	 * @param age
	 */
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	/**
	 * 定义一个无参构造器
	 */
	public Person() {

	}

	/**
	 * 添加set、get方法
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 重写toString方法
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Person [name=" + name + ",age = " + age + "]";
	}

	public void display(String nation) {
		System.out.println("我的国籍是：" + nation);
	}

}
