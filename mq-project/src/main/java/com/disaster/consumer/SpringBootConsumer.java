package com.disaster.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "disaster-producer-group", topic = "First_Topic")
public class SpringBootConsumer implements RocketMQListener {

    @Override
    public void onMessage(Object message) {
        System.out.println("Received message : "+ message);
    }
}

