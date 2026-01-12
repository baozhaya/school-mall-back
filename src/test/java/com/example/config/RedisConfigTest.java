package com.example.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisConfigTest {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("baozhaya", "xiaohong");
    }
}
