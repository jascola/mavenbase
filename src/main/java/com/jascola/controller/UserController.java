package com.jascola.controller;


import com.alibaba.fastjson.JSON;
import com.jascola.entity.User;
import com.jascola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/test")

public class UserController extends BaseController {
    /*无警告写法*/
    private final UserService userService;
    private JedisPool jedisPool;

    @Autowired
    public UserController(UserService userService,JedisPool jedisPool) {
        this.userService = userService;
        this.jedisPool = jedisPool;
    }

    @RequestMapping(value = "/test.html")
    public void goLogin(HttpServletResponse response) {
        List<User> users = userService.selectAll();
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("name"));
        System.out.println("my server is start!");
        super.ResponseJson(response, JSON.toJSONString(users));

    }

}
