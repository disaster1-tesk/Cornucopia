package com.disaster.springbootproject.autoconfiguration;

import com.disaster.springbootproject.config.ApplicationConfig;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(ApplicationConfig.class)//此配置文件将在ApplicationConfig配置文件前执行
public class IAutoConfigureBefore {
}
