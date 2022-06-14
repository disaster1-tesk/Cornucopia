package com.disaster.jedis.delayqueue;


import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

/**
 * redis实现的延时队列（消息的可靠性不如rockmq等正牌消息队列）
 *
 * @author disaster
 * @version 1.0
 */
public class RedisDelayQueue<T> {
    static class TaskItem<T> {
        public String id;
        public T msg;
    }

    // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference
    private Type TaskType;
    private Jedis jedis;
    private String queueKey;

    public RedisDelayQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
        this.TaskType= new TypeReference<TaskItem<T>>() {
        }.getType();
    }

    public void delay(T msg) {
        TaskItem task = new TaskItem();
        task.id = UUID.randomUUID().toString(); // 分配唯一的 uuid
        task.msg = msg;
        String s = JSON.toJSONString(task); // fastjson 序列化
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s); // 塞入延时队列 ,5s 后再试
    }

    public void loop() {
        while (!Thread.interrupted()) {
            // 只取一条
            List values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500); // 歇会继续
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = (String) values.iterator().next();
            if (jedis.zrem(queueKey, s) > 0) { // 抢到了
                TaskItem task = JSON.parseObject(s, TaskType); // fastjson 反序列化
                this.handleMsg((T) task.msg);
            }
        }
    }

    public void handleMsg(T msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        RedisDelayQueue queue = new RedisDelayQueue<>(jedis, "q-demo");
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.delay("codehole" + i);
            }
        });
        Thread consumer = new Thread(() -> queue.loop());
        producer.start();
        consumer.start();
        try {
            producer.join();
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
        }
    }
}
