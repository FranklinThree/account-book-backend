package com.github.franklinthree.main.config;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * 复述,配置测试
 *
 * @author FranklinThree
 * @date 2023/04/08
 * @className RedisConfigTests
 * @see
 * @since 1.0.0
 */
@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class RedisConfigTests {
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    /**
     * 存值
     */
    @Test
    @Order(1)
    public void testSetValue(){
        redisCacheTemplate.opsForValue().set("name", "FranklinThree");
    }


    /**
     * 取值
     */
    @Test
    @Order(2)
    public void testGetValue(){
        System.out.println(redisCacheTemplate.opsForValue().get("name"));
    }

    /**
     * 删除值
     */
    @Test
    @Order(3)
    public void testDeleteValue(){
        System.out.println(redisCacheTemplate.delete("name"));
    }
}
