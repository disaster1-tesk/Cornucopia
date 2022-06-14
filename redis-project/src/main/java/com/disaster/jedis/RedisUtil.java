package com.disaster.jedis;

import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class RedisUtil {
    private static String url = null;
    private static Integer port = null;
    private static String password = null;
    private static Jedis jedis = null;

    static {
        FileReader fileReader = null;
        Properties properties = null;
        try {
            fileReader = new FileReader(new File(Thread.currentThread().getContextClassLoader().getResource("redisdb.properties").getPath()));
            properties = new Properties();
            properties.load(fileReader);
            url = properties.getProperty("redis.db.url");
            port = Integer.parseInt(properties.getProperty("redis.db.port"));
            password = properties.getProperty("redis.db.password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jedis = new Jedis(url,port);
    }
    public static Jedis getJedis(){
        return jedis;
    }


    public static void main(String[] args) {
        System.out.println(getJedis().ping());
    }
}
