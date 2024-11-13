package com.disaster.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.disaster.assembly"})
public class CDPlayerConfig {
}
