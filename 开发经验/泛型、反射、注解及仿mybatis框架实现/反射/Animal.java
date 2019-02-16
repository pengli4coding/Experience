package com.pl.test;// 这行代码在vs code中会报错，不用管它

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Animal {

	private String name;
	private Integer sex;
	private Integer age;

	public Animal() {
		System.out.println("调用了无参构造器");
	}

	public Animal(String name, Integer sex, Integer age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		System.out.println("调用了有参构造器");
	}
	
	public void sayHello() {
		System.out.println("我的名字是：" + this.name + "，今年" + this.age + "岁了");
	}
	
	public void sayHello(String masterName) {
		System.out.println("我的名字是：" + this.name + "，今年" + this.age + "岁了，我的主人是：" + masterName);
	}
	
	@Override
	public String toString() {
		return "Animal [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
	
	
	// ----------华---丽---的---分---割---线----------
	// ----------华---丽---的---分---割---线----------
	// ----------华---丽---的---分---割---线----------
	
	public static void main(String[] args) throws Exception{
		/**
		 * 反射机制是在“运行时”: 
		 * 	对于任意一个类，都能够获取该类的所有属性和方法 
		 * 	更重要的是，对于任意一个对象，都可以设置该对象的属性值和调用该对象的方法
		 */

		// 获取Class对象的三种方法：
		// 第一种：通过实例对象.getClass()的方式获取
		Animal animal = new Animal();
		Class<?> clazz1 = animal.getClass();
		System.out.println(clazz1);
		// 第二种：通过类.class的方式获取
		Class<?> clazz2 = Animal.class;
		System.out.println(clazz2);
		// 第三种：通过Class.forName(全类名)的方式获取
		Class<?> clazz3 = Class.forName("com.pl.test.Animal");
		System.out.println(clazz3);
		
		// 通过反射获取类的所有属性
		Class<?> clazz = Class.forName("com.pl.test.Animal");
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			System.out.println("属性名：" + field.getName() + "  属性类型：" + field.getType());
		}
		
		// 通过反射获取类的所有方法  
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods) {
			System.out.println("方法名：" + method.getName() + " 方法参数个数：" + method.getParameterCount() + "  方法签名：" + method);
		}
		
		// 通过反射获取类的构造方法
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for(Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
		
		System.out.println("====================================================================");
		// 通过反射实例化对象
		Object obj = clazz.newInstance();
		System.out.println(obj);
		
		// 通过反射给对象属性赋值
		Field[] objFields = clazz.getDeclaredFields();
		for(Field field : objFields) {
			if(!field.isAccessible()) {// 判断属性是否是私有的
				field.setAccessible(true);// 设值私有属性值是可以被赋值的
			}
			String fieldName = field.getName();
			// 给属性设值
			switch (fieldName) {
			case "name":
				field.set(obj, "大肥猫");
				break;
			case "sex":
				field.set(obj, 1);
				break;
			case "age":
				field.set(obj, 3);
				break;
			default:
				break;
			}
		}
		System.out.println(obj);
		
		// 通过反射调用对象的方法
		Method sayHelloMethod1 = clazz.getDeclaredMethod("sayHello");
		sayHelloMethod1.invoke(obj);
		Method sayHelloMethod2 = clazz.getDeclaredMethod("sayHello",String.class);
		sayHelloMethod2.invoke(obj,"小立立");
	}
}
