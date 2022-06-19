package com.disaster.jedis;

import com.alibaba.fastjson.JSON;
import io.lettuce.core.RedisClient;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.args.FlushMode;
import redis.clients.jedis.args.GeoUnit;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.XAddParams;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JedisDemo {
    static Jedis jedis = JedisUtil.getJedis();

    public static void main(String[] args) throws InterruptedException {
        //strTest();
        //listTest();
        //hashTest();
        //setTest();
        //zsetTest();
        //hyperLogLogTest();
        //geoHashTest();
        //scanTest();
    }

    /**
     *  String数据类型的操作
     *
     * @author disaster
     * @version 1.0
     */
    public static void strTest() throws InterruptedException {
        /*
        键值对
         */
        jedis.flushDB();
        jedis.set("str_test","hello");//等同于[@User]#: set key value
        jedis.set("str_test1","world");
        jedis.del("str_test");//等同于[@User]#: del key
        System.out.println(jedis.get("str_test"));//等同于[@User]#: get key
        System.out.println(jedis.exists("str_test"));//等同于[@User]#: exists key
        /*
        批量键值对
         */
        jedis.flushDB();
        jedis.mset("str_test","hello1","str_test1","world1");//等同于[@User]#: mset key value key1 value1 ...
        System.out.println(jedis.mget("str_test","str_test1"));//等同于[@User]#: mget key key1 ...
        System.out.println(jedis.get("str_test"));
        /*
        过期和set命令扩展
         */
        jedis.expire("str_test1",5); //等同于[@User]#: expire key time
        System.out.println(jedis.ttl("str_test1"));
        System.out.println(jedis.get("str_test1"));
        jedis.setex("str_ex",1,"我是setex的内容");
        System.out.println(jedis.get("str_ex"));
        Thread.sleep(5000);
        System.out.println(jedis.get("str_test1"));
        System.out.println(jedis.get("str_ex"));
        jedis.setnx("str_nx","分布式内容"); //等同于[@User]#: setnx key time
        jedis.set("str_nx","分布式锁的组合命令", SetParams.setParams().ex(5).nx());
        System.out.println(jedis.get("str_nx"));

        /*
        计数
         */
        jedis.set("int_set", String.valueOf(Long.MAX_VALUE));//最大小为Long类型最大小值
        System.out.println(jedis.get("int_set"));
        jedis.incr("int_set");//等同于[@User]#: incr key
        System.out.println(jedis.get("int_set"));
        jedis.decr("int_set");//等同于[@User]#: decr key
        System.out.println(jedis.get("int_set"));
        jedis.close();

    }

    /**
     * list： Redis 的列表相当于 Java 语言里面的 LinkedList，注意它是链表而不是数组。这意味着
     * list 的插入和删除操作非常快，时间复杂度为 O(1)，但是索引定位很慢，时间复杂度为
     * O(n)，这点让人非常意外。
     *
     * @author disaster
     * @version 1.0
     */
    public static void listTest(){
        /*
        当做队列使用
         */
        jedis.flushDB();
        jedis.lpush("list_queue","hello","world");//等同于[@User]#: lpush key value value1...
        jedis.rpop("list_queue");//等同于[@User]#: rpop key
        System.out.println(jedis.lrange("list_queue", 0, -1));//等同于[@User]#: lrange key 0 -1

        /*
        当做栈使用
         */
        jedis.flushDB();
        jedis.lpush("list_stack","hello","world");//等同于[@User]#: lpush key value value1...
        jedis.lpop("list_stack");//等同于[@User]#: rpop key
        System.out.println(jedis.lrange("list_stack", 0, -1));//等同于[@User]#: lrange key 0 -1

        /*
        慢操作
         */
        jedis.lpush("list_slow","hello","world");
        System.out.println(jedis.lindex("list_slow", 1));//O(n)谨慎使用
        System.out.println(jedis.lrange("list_slow", 0, -1));//O(n)谨慎使用
        jedis.ltrim("list_slow",1,2);//等同于[@User]#: ltrim key 1 2
        System.out.println(jedis.llen("list_slow"));//等同于[@User]#: llen key
        jedis.close();
    }

    /**
     * Hash: Redis 的字典相当于 Java 语言里面的 HashMap，它是无序字典。内部实现结构上同
     * Java 的 HashMap 也是一致的，同样的数组 + 链表二维结构。第一维 hash 的数组位置碰撞
     * 时，就会将碰撞的元素使用链表串接起来
     *
     * @autho disaster
     * @version
     */
    public static void hashTest(){
        /*
        单个操作
         */
        User disaster = User.builder()
                .age(20)
                .name("disaster")
                .build();
        jedis.hset("hash_test","disaster",JSON.toJSONString(disaster));//等同于[@User]#: hset key key value
        jedis.hset("hash_test","hello","hello world");
        jedis.hdel("hash_test","10");
        System.out.println(jedis.hget("hash_test", "disaster"));
        System.out.println(jedis.hgetAll("hash_test"));//等同于[@User]#: hgetall key
        System.out.println(jedis.hlen("hash_test"));//等同于[@User]#: hlen key
        /*
        批量操作
         */
        HashMap<String, String> params = new HashMap<>();
        params.put("disaster",JSON.toJSONString(disaster));
        params.put("hello","hello world");
        jedis.hmset("hash_mset",params);;//等同于[@User]#: hmset key field key field
        System.out.println(jedis.hmget("hash_mset","disaster","hello"));//等同于[@User]#: hmget key field...

        /*
        计算
         */
        jedis.hset("caculate_test","caculate","10");
        System.out.println(jedis.hget("caculate_test","caculate"));
        jedis.hincrBy("caculate_test","caculate",10);;//等同于[@User]#: hincrby key field 10
        System.out.println(jedis.hget("caculate_test","caculate"));
        jedis.close();
    }

    /**
     * Set : Redis 的集合相当于 Java 语言里面的 HashSet，它内部的键值对是无序的唯一的。它的
     * 内部实现相当于一个特殊的字典，字典中所有的 value 都是一个值 NULL。
     *
     *
     * @author disaster
     * @version 1.0
     */
    public static void setTest(){
        jedis.sadd("set_test","java","python","c++");//等同于[@User]#: sadd key value
        System.out.println(jedis.smembers("set_test"));//等同于[@User]#: smembers key
        System.out.println(jedis.sismember("set_test","java"));//等同于[@User]#: sismember key value
        jedis.spop("set_test");
        System.out.println(jedis.scard("set_test"));//计算长度，等同于[@User]#: scart key
        System.out.println(jedis.smembers("set_test"));//等同于[@User]#: smembers key
        jedis.close();
    }

    /**
     * Zset : zset 可能是 Redis 提供的最为特色的数据结构，它也是在面试中面试官最爱问的数据结
     * 构。它类似于 Java 的 SortedSet 和 HashMap 的结合体，一方面它是一个 set，保证了内部
     * value 的唯一性，另一方面它可以给每个 value 赋予一个 score，代表这个 value 的排序权
     * 重。它的内部实现用的是一种叫着「跳跃列表」的数据结构。
     *
     * @author disaster
     * @version 1.0
     */
    public static void zsetTest(){
        jedis.zadd("book",80,"python");
        jedis.zadd("book",90,"java");
        jedis.zadd("book",100,"c");
        System.out.println(jedis.zrange("book",0,-1));//按照score顺序列出值，等同于[@User]#: zrange key
        System.out.println(jedis.zrevrange("book",0,-1));//按照score逆序列出值，等同于[@User]#: zrange key
        System.out.println(jedis.zscore("book","java"));//获取指定value的score，等同于[@User]#: zsocre key
        System.out.println(jedis.zrank("book","java"));//获取指定value的排名,等同于[@User]#: zrank key
        System.out.println(jedis.zrangeByScore("book",60,90));//获取指定score区间的value，等同于[@User]#: zrangebyscore key 60 90
        jedis.zrem("book","python","java","c");//移除value，等同于[@User]#: zrem key1 key2 key3
        System.out.println(jedis.zrange("book",0,-1));
        jedis.close();
    }

    /**
     * HyperLogLog:redis的高级数据结构，具有去重的作用，常用于统计页面用户的访问量（UV）
     *
     * @author disaster
     * @version 1.0
     **/
    public static void hyperLogLogTest(){
        jedis.pfadd("hyper_test","hello","world");
        System.out.println(jedis.pfcount("hyper_test"));//，等同于[@User]#: pfcount key

        for (int i = 0; i < 100000; i++) {
            jedis.pfadd("codehole", "user" + i);
        }
        long total = jedis.pfcount("codehole");
        System.out.printf("%d %d\n", 100000, total);

        jedis.pfmerge("sum","codehole","hyper_test");
        System.out.println(jedis.pfcount("sum"));//，等同于[@User]#: pfcount key
        jedis.close();
    }

    /**
     * bimap
     *
     * @author disaster
     * @version 1.0
     */
    public static void boolTest(){
        RedisClient client =  RedisClient.create();
    }

    /**
     * GeoHash:redis的高级数据结构，常用于经纬度的计算（附近的人）
     *
     * @author disaster
     * @version 1.0
     */
    public static void geoHashTest(){
        jedis.geoadd("geo_hash",116.48105,39.996794,"juejing");
        jedis.geoadd("geo_hash",116.514203,39.905409,"ireader");
        jedis.geoadd("geo_hash",116.489033,40.007669,"meituan");
        jedis.geoadd("geo_hash",116.334255,40.027400,"juejing");
        jedis.geoadd("geo_hash",116.562108,39.787602,"jd");
        System.out.println(jedis.geodist("geo_hash", "juejing", "ireader", GeoUnit.KM));
        System.out.println(jedis.geopos("geo_hash","juejing","ireader"));
        System.out.println(jedis.geohash("geo_hash","juejing"));
        List<GeoRadiusResponse> geoRadiusResponses = jedis.georadiusByMember("geo_hash", "ireader", 20, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().sortAscending());
        geoRadiusResponses.stream().forEach((s)->{
            System.out.println(String.format("名称：%s,距离：%f KM",s.getMemberByString(),s.getDistance()));
        });
        List<GeoRadiusResponse> geo_hash = jedis.georadius("geo_hash", 116.48105, 39.996794, 20, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().sortAscending().withDist().count(3));
        geo_hash.stream().forEach((s)->{
            System.out.println(String.format("名称：%s,距离：%f KM",s.getMemberByString(),s.getDistance()));
        });
    }

    /**
     * scan：大海捞针  scan 参数提供了三个参数，第一个是 cursor 整数值，第二个是 key 的正则模式，第三个是遍历的 limit hint
     *
     * @author disaster
     * @version 1.0
     */
    @Test
    public void scanTest(){
        Set<String> keys = jedis.keys("*");
        keys.stream().forEach((s)->{
            System.out.println("s = " + s);
        });
        Set<String> keys1 = jedis.keys("codehole*");
        keys1.stream().forEach((s)->{
            System.out.println("s1 = " + s);
        });
        List<String> result = jedis.scan("0", new ScanParams().match("code*").count(100)).getResult();
        result.stream().forEach((s)->{
            System.out.println("result = " + s);
        });
    }

    /**
     * 存储界限 当集合对象的元素不断增加，或者某个 value 值过大，这种小对象存储也会
     * 被升级为标准结构。Redis 规定在小对象存储结构的限制条件如下:
     *
     * hash-max-zipmap-entries 512 # hash 的元素个数超过 512 就必须用标准结构存储
     * hash-max-zipmap-value 64 # hash 的任意元素的 key/value 的长度超过 64 就必须用标准结构存储
     * list-max-ziplist-entries 512 # list 的元素个数超过 512 就必须用标准结构存储
     * list-max-ziplist-value 64 # list 的任意元素的长度超过 64 就必须用标准结构存储
     * zset-max-ziplist-entries 128 # zset 的元素个数超过 128 就必须用标准结构存储
     * zset-max-ziplist-value 64 # zset 的任意元素的长度超过 64 就必须用标准结构存储
     * set-max-intset-entries 512 # set 的整数元素个数超过 512 就必须用标准结构存储
     */
    @Test
    public void  zipTest(){
        for (int i = 0; i < 512 ; i++) {
            jedis.hset("hello",String.valueOf(i),String.valueOf(i));
        }
        System.out.println(jedis.objectEncoding("hello"));
        jedis.hset("hello",String.valueOf(512),String.valueOf(512));
        System.out.println(jedis.objectEncoding("hello"));
    }

    /**
     * Redis5.0 最大的新特性就是多出了一个数据结构 Stream，它是一个新的强大的支持多播的可持久化的消息队列，作者坦言 Redis Stream 狠狠地借鉴了 Kafka 的设计。
     *
     * @author disaster
     * @version 1.0
     */
    @Test
    public void streamTest(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name","disaster");
        map.put("age","30");
        System.out.println(jedis.xadd("codehole*", map, XAddParams.xAddParams()));
        System.out.println(jedis.xlen("codehole*"));
        System.out.println(jedis.xrange("codehole*","-","1655383635791-0"));
        System.out.println(jedis.xdel("codehole*", StreamEntryID.UNRECEIVED_ENTRY));
    }

    /**
     * info执行的使用
     *
     * @author disaster
     * @version 1.0
     */
    @Test
    public void infoTest(){
        //System.out.println(jedis.info());
        System.out.println(jedis.info("redis-cli info stats |grep ops"));//Redis 每秒执行多少次指令  redis-cli info stats |grep ops
        System.out.println(jedis.info("redis-cli info clients"));  //Redis 连接了多少客户端  redis-cli info clients
        System.out.println(jedis.info("redis-cli info memory | grep used | grep human")); //Redis 内存占用多大 ?  redis-cli info memory | grep used | grep human
        System.out.println(jedis.info("redis-cli info replication |grep backlog")); //复制积压缓冲区多大？redis-cli info replication |grep backlog

    }

    /**
     *  懒惰删除
     *
     * @author disaster
     * @version 1.0
     */
    @Test
    public void lazyFreeTest(){
        System.out.println(jedis.keys("*"));
        jedis.unlink("");//后台线程来异步回收内存
        jedis.flushAll(FlushMode.ASYNC);//后台线程来异步回收内存
    }


}
