package com.disaster.jedis.pubsub;

import com.disaster.jedis.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * redis消息多播功能（消费者）
 *
 * @author disaster
 * @version 1.0
 **/
public class Comsumer {
    private static Jedis jedis = JedisUtil.getJedis();

    public static void receiveMsg() {
        jedis.subscribe(new JedisPubSub() {
                            @Override
                            public void onMessage(String channel, String message) {
                                System.out.println(channel + ":" + message);
                            }

                        }
                , "codehole", "codehole1");
    }

    public static void receiveMsgForPattern() {
        jedis.psubscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(channel + ":" + message);
            }
        }, "codehole*");
    }

    public static void main(String[] args) {
        while (true) {
            receiveMsg();
        }
    }
}
