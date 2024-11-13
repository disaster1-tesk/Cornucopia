package com.disaster.ioc.config;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource("classpath:spring-bean.xml")
@EnableAspectJAutoProxy
//@Import(CDPlayerConfig.class)
public class ClassConfig {

//    @Bean
//    public MediaPlayer cdPlayer(CompactDisc compactDisc){
//       return new CDPlayer(compactDisc);
//       return new CDPlayer(compactDisc());
//    }

//    @Bean
//    public CompactDisc compactDisc(){
//        return new SgtPeppers();
//    }
}
