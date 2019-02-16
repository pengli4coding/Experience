package com.pl.test;// 这行代码在vs code中会报错，不用管它

import java.lang.reflect.Field;

@Table(tbName = "t_user", increment = 2)
public class MyClass {
	
	@Column(columnName="USERNAME")
	private String name;
	@Column(columnName="PASSWORD")
	private String pwd;

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.pl.test.MyClass");
		Table annotation = clazz.getAnnotation(Table.class);
		System.out.println("注解为:" + annotation);
		System.out.println("注解的类型为：" + annotation.annotationType());
		System.out.println("注解的tbName属性为：" + annotation.tbName());
		System.out.println("注解的increment属性为：" + annotation.increment());
		
		System.out.println("=================================================");
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			System.out.println("成员变量名为：" + field.getName() + "   注解的columnName属性名（即对应数据库的字段名）为：" + column.columnName());
		}
	}
}
