package com.disaster.springbootproject.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class IApplicationListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(String.format("%s开始执行了onApplicationEvent方法...",this.getClass().getName()));
    }
}
