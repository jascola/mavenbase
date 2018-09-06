package com.jascola.controller;


import com.alibaba.fastjson.JSON;
import com.jascola.dto.PicQueryDto;
import com.jascola.entity.UserEntity;
import com.jascola.service.UserService;
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
@RequestMapping(value = "/user")

public class UserController extends BaseController {
    /*无警告写法*/
    private final UserService userService;
    private JedisPool jedisPool;
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, JedisPool jedisPool) {
        this.userService = userService;
        this.jedisPool = jedisPool;
    }


    @RequestMapping(value = "/regist.html")
    public void regist(UserEntity entity, HttpServletResponse response) {
        List<String> messages = new ArrayList<String>();
        if (entity.getEmail() != null && !entity.getEmail().equals("")
                && entity.getName() != null && !entity.getName().equals("")
                && !entity.getPassword().equals("") && entity.getPassword() != null
                && entity.getPhone() != null && !entity.getPhone().equals("")) {
            if (userService.selectByPhone(entity.getPhone()) == null) {
                try {
                    entity.setPassword(base64Encoder.encode(entity.getPassword().getBytes()));
                    /*System.out.println(new String(base64Decoder.
                            decodeBuffer(base64Encoder.encode("123vfdgsfdgfdhgfj423534262346".getBytes()))));*/
                    userService.insert(entity);
                    messages.add("注册成功！");
                    super.ResponseSuccess(response, messages);
                    return;
                } catch (Exception e) {
                    messages.add("注册失败！");
                    super.ResponseError(response, messages);
                    LOGGER.error(e.getLocalizedMessage(), e);
                    return;
                }
            } else {
                messages.add("该号码已经被注册！");
                super.ResponseError(response, messages);
                return;
            }
        }
        messages.add("注册信息含有空值！");
        super.ResponseWarn(response, messages);
    }


    @RequestMapping(value = "/login.html")
    public void login(UserEntity entity, HttpServletResponse response) {
        List<String> messages = new ArrayList<String>();
        if (entity.getPassword() != null && !entity.getPassword().equals("")
                && entity.getPhone() != null && !entity.getPhone().equals("")) {

            /*先从redis中查*/
            Jedis jedis = jedisPool.getResource();
            String content = jedis.get(entity.getPhone());

            if (content != null) {
                UserEntity jedisuser = JSON.parseObject(content, UserEntity.class);
                String pwd = jedisuser.getPassword();
                try {
                    pwd = new String(base64Decoder.
                            decodeBuffer(pwd));
                    if (pwd.equals(entity.getPassword())) {
                        messages.add("登录成功！");
                        messages.add("欢迎！" + jedisuser.getName());
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
                }
            } else {
                try {
                    UserEntity result = userService.selectByPhone(entity.getPhone());
                    String pwd = new String(base64Decoder.
                            decodeBuffer(result.getPassword()));
                    if (pwd.equals(entity.getPassword())) {
                        messages.add("登录成功！");
                        messages.add("欢迎！" + result.getName());
                        /*签发token*/
                        jedis.set(result.getPhone(), JSON.toJSONString(result));
                        jedis.expire(result.getPhone(), 24 * 60 * 60);/*一天之内都可以访问，不用登录*/

                        Cookie cookie = new Cookie("token", base64Encoder.encode(result.getPhone().getBytes()));
                        cookie.setMaxAge(24 * 60 * 60);
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
                }
            }
        }
        messages.add("登录信息含有空值！");
        super.ResponseWarn(response, messages);
    }

    @RequestMapping(value = "/getpic.html")
    public void getPic(PicQueryDto dto, HttpServletResponse response, HttpServletRequest request) {
        /*List<String> messages = new ArrayList<String>();
        Jedis jedis = jedisPool.getResource();
        String content = super.tokenAdminCheck(response, request, messages, jedisPool);
        if (content == null) {
            return;
        }*/
        String param = dto.getParam();
        if(param==null||param.equals("")){
            System.out.println(dto.getPageSize());
        }
    }

    /*测试*/
    @RequestMapping(value = "/test.html")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        List<String> messages = new ArrayList<String>();

        String content = super.tokenCheck(response, request, messages, jedisPool);
        if (content == null) {
            return;
        }
        messages.add("成功访问");
        UserEntity entity = JSON.parseObject(content, UserEntity.class);
        messages.add("欢迎！" + entity.getName());
        super.ResponseSuccess(response, messages);
    }

}
