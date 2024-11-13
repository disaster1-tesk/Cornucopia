package com.disaster.springbootproject.processor;

import com.disaster.springbootproject.util.PropertySourceUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class ExtendPropertyBeanPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        environment.getPropertySources().addFirst(PropertySourceUtils.getResourcePropertySource("classpath:/properties/ExtendProperties.properties"));
    }
}
