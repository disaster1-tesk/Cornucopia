package com.disaster.springbootproject.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IApplicationRunnerListener implements SpringApplicationRunListener {

    public IApplicationRunnerListener(SpringApplication springApplication, String[] args) {

    }

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println(String.format("%s开始执行了starting方法...",this.getClass().getName()));
        SpringApplicationRunListener.super.starting(bootstrapContext);
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        System.out.println(String.format("%s开始执行了environmentPrepared方法...",this.getClass().getName()));
        SpringApplicationRunListener.super.environmentPrepared(bootstrapContext, environment);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println(String.format("%s开始执行了contextPrepared方法...",this.getClass().getName()));
        SpringApplicationRunListener.super.contextPrepared(context);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println(String.format("%s开始执行了contextLoaded方法...",this.getClass().getName()));
        SpringApplicationRunListener.super.contextLoaded(context);
    }


    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println(String.format("%s开始执行了started(ConfigurableApplicationContext context)方法...",this.getClass().getName()));
        SpringApplicationRunListener.super.started(context);
    }



    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println(String.format("%s开始执行了running方法...",this.getClass().getName()));
        SpringApplicationRunListener.super.running(context);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println(String.format("%s开始执行了environmentPrepared方法...",this.getClass().getName()));
        SpringApplicationRunListener.super.failed(context, exception);
    }
}
