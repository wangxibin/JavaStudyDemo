package com.jimmy.reflection;

public class Person {
	/**
	 * ����˽�б���
	 */
	public String name;
	private int age;

	/**
	 * ����һ���вι�����
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
	 * ����һ���޲ι�����
	 */
	public Person() {

	}

	/**
	 * ���set��get����
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
	 * ��дtoString����
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Person [name=" + name + ",age = " + age + "]";
	}

	public void display(String nation) {
		System.out.println("�ҵĹ����ǣ�" + nation);
	}

}
