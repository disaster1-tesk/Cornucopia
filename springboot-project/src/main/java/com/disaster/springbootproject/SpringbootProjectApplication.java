package com.disaster.springbootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProjectApplication.class, args);
        // 启动Reporter
//        ConsoleReporter reporter = ctx.getBean(ConsoleReporter.class);
//        reporter.start(1, TimeUnit.SECONDS);
    }

}
