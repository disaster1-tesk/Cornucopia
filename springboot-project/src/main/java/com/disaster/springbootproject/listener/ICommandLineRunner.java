package com.disaster.springbootproject.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(-1000)
public class ICommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(String.format("%s开始执行了run方法...",this.getClass().getName()));
    }
}
