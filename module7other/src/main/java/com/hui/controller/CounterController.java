package com.hui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/28 17:31
 */
@RestController
public class CounterController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String count(){
        Long increment = redisTemplate.opsForValue().increment("count-people");
        return "有多少人访问 ：" +increment;
    }
}
