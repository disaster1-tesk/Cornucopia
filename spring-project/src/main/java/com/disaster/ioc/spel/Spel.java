package com.disaster.ioc.spel;

import com.disaster.ioc.assembly.MediaPlayer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Spel {
    @Value("#{20}")
    private String age;
    @Value("#{CDPlayer}")
    private MediaPlayer mediaPlayer;
    @Value("#{T(Math).PI * 20 ^ 2}")
    private Long area;
    @Value("#{10 eq 10}")
    private Boolean isTrue;
    @Value("#{10 == 20}")
    private Boolean isFalse;
    @Value("#{10 eq 10 ? 'yes' : 'no'}")
    private String ternary;
    @Value("#{'hello word' matches '/[-a-z]/'}")
    private String regular;

    public void showAge(){
        System.out.println(age);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        Spel bean = applicationContext.getBean(Spel.class);
        bean.showAge();
        bean.mediaPlayer.play();
        System.out.println(bean.area);
        System.out.println(bean.isTrue + "-----" + bean.isFalse);
        System.out.println(bean.ternary);
        System.out.println(bean.regular);
    }
}
