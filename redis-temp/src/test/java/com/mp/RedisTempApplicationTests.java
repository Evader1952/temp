package com.mp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTempApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
        redisTemplate.boundValueOps("new_key").set("new_value");
    }


}
