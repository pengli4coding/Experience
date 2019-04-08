package com.pl.test;

import com.pl.dao.UserDao;
import com.pl.model.User;
import com.pl.service.OrderService;
import com.pl.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class MyTest {



    @Test
    public void testAnnotationAspect1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean(UserDao.class);
        User user = new User();
        user.setUsername("小立立");
        user.setPassword("123");
        boolean result = userDao.addUser(user);
        System.out.println(result);

    }

    @Test
    public void testAnnotationAspect2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean(UserService.class);
        User user = new User();
        user.setUsername("小立立");
        user.setPassword("123");
        boolean result = userService.addUser(user);
        System.out.println(result);
    }

    @Test
    public void testXMLAspect(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderService orderService = (OrderService)context.getBean("orderServiceImpl");
        orderService.placeOrder();
    }

    @Test
    public void testSchedule(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
}
