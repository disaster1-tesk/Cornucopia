package com.disaster.jedis;


import redis.clients.jedis.Jedis;


public class JedisDemo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        String ping = jedis.ping();
        System.out.println("ping = " + ping);
    }
}
