package com.pl.test;// 这行代码在vs code中会报错，不用管它

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解通常用在package、class、field、method上，目的是与其它的类、成员变量、方法区分开来或者说做一个标识
 * 它的声明形式跟接口很类似，通过@interface关键字进行定义
 *
 * 元注解是定义在注解上的特殊注解，用来定义注解的一些行为，有以下的元注解：
 * 	@Target:表示注解具体是作用在哪些地方（包、类、成员变量、方法）上
 * 	@Retention:表示注解在哪个阶段（源码阶段、编译阶段、运行阶段）是保留的
 * 	@Inherited:表示是否允许子类继承父类的注解
 * 
 * 注解可以有属性（注解是没有成员方法的），注解的属性以“无形参的方法”形式来声明，其方法名定义了该变量的名字，其返回值定义了该属性的类型
 * 注解属性的类型只能用基本数据类型（int、byte、short、long、float、double、char、boolean）、String、枚举、类、注解，以及这些数据类型的数组
 * 注解中属性可以有默认值，默认值需要用default关键字指定
 * 注解中的属性只能用public来修饰
 * 
 * 注解不能够继承其它注解
 */


/**
 * @Target可选属性的说明：
 * 	ElementType.TYPE              可以给一个类型进行注解，如类、接口、枚举
 * 	ElementType.FIELD             可以给成员变量进行注解
 * 	ElementType.METHOD            可以给方法进行注解
 * 	ElementType.PARAMETER         可以给方法的参数进行注解
 * 	ElementType.CONSTRUCTOR       可以给构造器进行注解
 * 	ElementType.LOCAL_VARIABLE    可以给局部变量进行注解
 * 	ElementType.ANNOTATION_TYPE   可以给注解进行注解
 * 	ElementType.PACKAGE           可以给包进行注解
 */

/**
 * @Retention可选属性的说明：
 * 	RetentionPolicy.SOURCE        注解只在源码阶段保留，在编译阶段就被丢弃
 * 	RetentionPolicy.CLASS         注解只保留到编译阶段，在运行阶段就被丢弃
 * 	RetentionPolicy.RUNTIME       注解会保留到运行阶段，也就是它会被加载到jvm中，在运行时可见，这是我们最常用的
 */

@Target({ElementType.TYPE,ElementType.METHOD})// 表明自定义的注解可以作用于类、接口、枚举、方法上
@Retention(RetentionPolicy.RUNTIME)// 表明自定义的注解在运行时是可见的
@Inherited// 表明自定义的注解如果作用在父类 上，那么子类会继承这个自定义的注解
public @interface MyAnnotation {
	String value();// 以“无形参的方法”形式来声明自定义注解的属性名为value，属性的类型为String
	int sex() default 1;// 以“无形参的方法”形式来声明自定义注解的属性名为sex，属性的类型为int，默认值为1
}
