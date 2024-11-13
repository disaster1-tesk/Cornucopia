package com.disaster.springbootredis;

import com.alibaba.fastjson.JSON;
import com.disaster.BaseTest;
import com.disaster.springbootredis.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Field;
import java.util.HashMap;

public class EntityInputRedisTest extends BaseTest {
    @Autowired
    private StringRedisTemplate redisTemplate;
    private User user;

    @Before
    public void before() {
        user = User.builder()
                .username("disaster")
                .password("123456")
                .weight(140)
                .height(180)
                .build();
    }

    @Test
    public void entityByStringTest() {
        String json = JSON.toJSONString(user);
        redisTemplate.opsForValue().set("userInfo", json);
    }

    @Test
    public void entityByHashTest() throws IllegalAccessException {
        HashMap<String, String> params = new HashMap<>();
        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            params.put(field.getName(), field.get(user)+"");
        }
        redisTemplate.opsForHash().putAll("userInfoByHash", params);
    }


}
