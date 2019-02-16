package com.pl.test;// 这行代码在vs code中会报错，不用管它

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String tbName();
	int increment() default 1;
}
