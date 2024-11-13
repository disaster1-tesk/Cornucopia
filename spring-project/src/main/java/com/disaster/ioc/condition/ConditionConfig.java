package com.disaster.ioc.condition;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(MyCondition.class)
    public String dataSource(){
        return "测试Condition";
    }
}
