package com.disaster.ioc.qualifier;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class QualifierConfig {

//    @Bean
//    public Move move(){
//        return new Ship();
//    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        Invoker bean = classPathXmlApplicationContext.getBean(Invoker.class);
        bean.move();
    }
}
