package com.disaster.ioc.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DevProfileConfig {

    @Bean
    @Profile("dev")
    public DataSource dataDevSource(){
        return null;
    }

    @Bean
    @Profile("test")
    public DataSource dataTestSource(){
        return null;
    }

    @Bean
    @Profile("release")
    public DataSource dataReleaseSource(){
        return null;
    }

}
