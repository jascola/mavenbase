package com.jascola.controller;


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

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JedisPool jedisPool;

    @Autowired(required = false)
    private HttpServletResponse response;
    @RequestMapping(value = "/test.html")
    public void goLogin(){
        List<User> users= userService.selectAll();
        for (User user:users){
            System.out.println(user.toString());
        }
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("name"));
    }

}
