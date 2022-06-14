package com.disaster.jedis.lock;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * redis分布式锁的实现方式（可重入锁）
 *
 * @author wangwei
 * @version 1.0
 */
public class RedisWithReentrantLock {
    private ThreadLocal<Map> lockers = new ThreadLocal<>();
    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    private boolean _lock(String key) {
        return Objects.nonNull(jedis.set(key, "", SetParams.setParams().ex(5L).nx()));
    }

    private void _unlock(String key) {
        jedis.del(key);
    }

    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public boolean lock(String key) {
        Map refs = currentLockers();
        Integer refCnt = (Integer) refs.get(key);
        if (refCnt != null) {
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    public boolean unlock(String key) {
        Map refs = currentLockers();
        Integer refCnt = (Integer) refs.get(key);
        if (refCnt == null) {
            return false;
        }
        refCnt -= 1;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis);
        System.out.println(redis.lock("codehole"));
        new Thread(() -> {
            System.out.println(redis.lock("codehole"));
        }).start();
        System.out.println(redis.lock("codehole"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(redis.unlock("codehole"));
        System.out.println(redis.unlock("codehole"));
    }
}
