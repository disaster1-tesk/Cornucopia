package com.disaster.controller;

import com.disaster.producer.SpringBootProducer;
import org.junit.runners.Parameterized;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/MQTest")
public class MQTestController {

    private final String topic = "First_Topic";
    @Resource
    private SpringBootProducer producer;

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam("msg") String message) {
        producer.sendMessage(topic, message);
        return "消息发送完成";
    }

    //这个发送事务消息的例子中有很多问题，需要注意下。
    @RequestMapping("/sendTransactionMessage")
    public String sendTransactionMessage(String message) throws InterruptedException {
        producer.sendMessageInTransaction(topic, message);
        return "消息发送完成";
    }
}

