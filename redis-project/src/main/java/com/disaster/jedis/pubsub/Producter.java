package com.disaster.jedis.pubsub;

import com.disaster.jedis.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Scanner;

/**
 * redis消息多播的功能（生产者）
 *
 * @author disaster
 * @version 1.0
 **/
public class Producter {
    private static Jedis jedis = JedisUtil.getJedis();

    public static void sendMsg(){
        Scanner sc = new Scanner(System.in);
        while (true){
            String msg = sc.next();
            jedis.publish("codehole",msg);
        }
    }

    public static void main(String[] args) {
     /*   new Thread(() -> {
            Comsumer.receiveMsg();
        }).start();*/
        sendMsg();
    }
}
