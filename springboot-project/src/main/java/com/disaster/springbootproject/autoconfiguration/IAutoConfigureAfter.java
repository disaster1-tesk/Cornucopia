package com.disaster.springbootproject.autoconfiguration;

import com.disaster.springbootproject.config.ApplicationConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(ApplicationConfig.class)//此配置文件将在ApplicationConfig配置文件后执行
public class IAutoConfigureAfter {
}
