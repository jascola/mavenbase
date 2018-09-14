package com.jascola.controller;

import com.alibaba.fastjson.JSON;
import com.jascola.entity.AdminEntity;
import com.jascola.entity.UserEntity;
import com.jascola.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(AdminController.class);
    private final AdminService service;
    private JedisPool jedisPool;

    @Autowired
    public AdminController(AdminService service, JedisPool jedisPool) {
        this.service = service;
        this.jedisPool = jedisPool;
    }

    @RequestMapping(value = "/login.html")
    public void login(HttpServletResponse response, AdminEntity entity) {
        List<String> messages = new ArrayList<String>();
        if (entity.getPassword() != null && !entity.getPassword().equals("")
                && entity.getPhone() != null && !entity.getPhone().equals("")) {

            /*先从redis中查*/
            Jedis jedis = jedisPool.getResource();
            jedis.select(15);
            String content = jedis.get(entity.getPhone());

            if (content != null) {
                AdminEntity jedisuser = JSON.parseObject(content, AdminEntity.class);
                String pwd = jedisuser.getPassword();
                try {
                    pwd = new String(base64Decoder.
                            decodeBuffer(pwd));
                    if (pwd.equals(entity.getPassword())) {
                        messages.add("登录成功！");
                        messages.add("欢迎！管理员");
                        Cookie cookie = new Cookie("admintoken", base64Encoder.encode(entity.getPhone().getBytes()));
                        cookie.setMaxAge(2 * 60 * 60);
                        response.addCookie(cookie);
                        super.ResponseSuccess(response, messages);
                        System.out.println("从redis里查");
                        return;
                    } else {
                        messages.add("账号或密码错误！");
                        super.ResponseError(response, messages);
                        return;
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                    return;
                } finally {
                    jedis.close();
                }
            } else {
                try {
                    AdminEntity result = service.selectByPhone(entity.getPhone());
                    String pwd = new String(base64Decoder.
                            decodeBuffer(result.getPassword()));
                    if (pwd.equals(entity.getPassword())) {
                        messages.add("登录成功！");
                        messages.add("欢迎！管理员");
                        /*签发token*/
                        jedis.set(result.getPhone(), JSON.toJSONString(result));
                        jedis.expire(result.getPhone(), 2 * 60 * 60);/*一天之内都可以访问，不用登录*/

                        Cookie cookie = new Cookie("admintoken", base64Encoder.encode(result.getPhone().getBytes()));
                        cookie.setMaxAge(2 * 60 * 60);
                        response.addCookie(cookie);


                        super.ResponseSuccess(response, messages);
                        System.out.println("从数据库中查");
                        return;
                    } else {
                        messages.add("账号或密码错误！");
                        super.ResponseError(response, messages);
                        return;
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                    return;
                } finally {
                    jedis.close();
                }
            }
        }
        messages.add("登录信息含有空值！");
        super.ResponseWarn(response, messages);
    }

    @RequestMapping(value = "/logout.html")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        List<String> messages = new ArrayList<String>();
        Jedis jedis = jedisPool.getResource();
        String content = super.tokenAdminCheck(response, request, messages, jedisPool);
        if (content == null) {
            return;
        }
        try {
            AdminEntity entity = JSON.parseObject(content, AdminEntity.class);
            jedis.del(entity.getPhone());
            messages.add("成功登出");
            jedis.close();
            super.ResponseSuccess(response, messages);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            jedis.close();
        }
    }

    @RequestMapping(value = "/check.html")
    public void check(HttpServletRequest request, HttpServletResponse response) {
        List<String> messages = new ArrayList<String>();
        String content = super.tokenAdminCheck(response, request, messages, jedisPool);
        if (content == null || content.equals("[]") || content.equals("")) {
            return;
        }
        messages.add("欢迎！管理员");
        super.ResponseSuccess(response, messages);
    }
}
