package com.disaster.aop.aspectj;

import com.disaster.ioc.config.ClassConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Audience implements Performance {

    @Override
    public void perform() {
        System.out.println(String.format("%s 正在执行perform方法ing...", this.getClass().getSimpleName()));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClassConfig.class);
        Performance bean = applicationContext.getBean(Performance.class);
        bean.perform();
    }
}
