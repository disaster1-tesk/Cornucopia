package com.disaster.jedis.Limiter;

import com.disaster.jedis.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * redis实现简单限流
 *
 * @author disaster
 * @version 1.0
 */
public class SimpleRateLimiter {
    private Jedis jedis;
    public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }
    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = String.format("hist:%s:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();
        Transaction multi = jedis.multi();
        multi.zadd(key, nowTs, "" + nowTs);
        multi.zremrangeByScore(key, 0, nowTs - period * 1000);
        Response<Long> count = multi.zcard(key);
        multi.expire(key, period + 1);
        multi.exec();
        multi.close();
        return count.get() <= maxCount;
    }
    public static void main(String[] args) {
        SimpleRateLimiter limiter = new SimpleRateLimiter(JedisUtil.getJedis());
        for(int i=0;i<20;i++) {
            System.out.println(limiter.isActionAllowed("disaster", "reply", 60, 5));
        }
    }
}
