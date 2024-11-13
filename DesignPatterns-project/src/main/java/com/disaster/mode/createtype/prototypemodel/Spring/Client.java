package com.disaster.mode.createtype.prototypemodel.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 19:49
 * @Version 1.0
 **/
public class Client {
    @Autowired
    private Person person;

    public Client() {
    }

    public static void main(String[] args) {
        System.out.println(new File("D:\\IDEA\\DesignPattern\\src\\main\\resources\\beans.xml").exists());
        ClassPathXmlApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Object id01 = ApplicationContext.getBean("id01");
        System.out.println("id01 = " + id01);
        Object id011 = ApplicationContext.getBean("id01");
        System.out.println(id01==id011);
    }
}
