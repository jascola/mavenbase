package com.jascola.controller;


import com.alibaba.fastjson.JSON;
import com.jascola.dto.PicQueryDto;
import com.jascola.entity.PicturesEntity;
import com.jascola.entity.UserEntity;
import com.jascola.service.Pictureservice;
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
import java.util.*;

@Controller
@RequestMapping(value = "/user")

public class UserController extends BaseController {
    /*无警告写法*/
    private final UserService userService;
    private final Pictureservice pictureservice;
    private JedisPool jedisPool;
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, JedisPool jedisPool, Pictureservice pictureservice) {
        this.userService = userService;
        this.jedisPool = jedisPool;
        this.pictureservice = pictureservice;
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
            jedis.select(15);
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
                        Cookie cookie = new Cookie("token", base64Encoder.encode(entity.getPhone().getBytes()));
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
                    UserEntity result = userService.selectByPhone(entity.getPhone());
                    if (result != null) {
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
                    } else {
                        messages.add("账号不存在，请注册！");
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

    @RequestMapping(value = "/getpic.html")
    public void getPic(PicQueryDto dto, HttpServletResponse response, HttpServletRequest request) {
        Jedis jedis = jedisPool.getResource();
        String param = dto.getParam();
        String pageSize = String.valueOf(dto.getPageSize());
        String pageNo = String.valueOf(dto.getPageNo());
        String result;
        Integer count;
        List<PicturesEntity> entityList;
        Map<String, Object> map = new HashMap<String, Object>();
        /*判断查询条件*/
        if (param == null || param.equals("")) {
            /*判断redis是否有值，有则从redis中取*/
            result = jedis.get(pageNo + "null" + pageSize);
            if (result != null && !result.equals("[]")) {
                jedis.close();
                super.ResponseJson(response, result);
                return;
            }
            /*没有值去查数据库，并将数据存入redis*/
            else {
                count = pictureservice.selectCount();
                dto.setTotalCount(count);
                entityList = pictureservice.selectAll(dto);
                map.put("size", count);
                map.put("list", entityList);
                String json = JSON.toJSONString(map);
                jedis.set(pageNo + "null" + pageSize, json);
                jedis.expire(pageNo + "null" + pageSize, 2 * 60 * 60);
                jedis.close();
                super.ResponseJson(response, json);
                return;
            }
        } else {
            /*判断查询条件是作者名还是相册名*/
            if ((count = pictureservice.selectCountByAuName(param)) != 0) {
                result = jedis.get(pageNo + param + pageSize);
                if (result != null && !result.equals("[]")) {
                    jedis.close();
                    super.ResponseJson(response, result);
                    return;
                }
                dto.setTotalCount(count);
                dto.setAuthorname(param);
                entityList = pictureservice.selectByAuName(dto);
                map.put("size", count);
                map.put("list", entityList);
                String json = JSON.toJSONString(map);
                jedis.set(pageNo + param + pageSize, json);
                jedis.expire(pageNo + param + pageSize, 2 * 60 * 60);
                jedis.close();
                super.ResponseJson(response, json);
                return;
            } else {
                result = jedis.get(pageNo + param + pageSize);
                if (result != null && !result.equals("[]")) {
                    jedis.close();
                    super.ResponseJson(response, result);
                    return;
                }
                entityList = pictureservice.selectByPicName(param);
                map.put("size", count);
                map.put("list", entityList);
                String json = JSON.toJSONString(map);
                jedis.set(pageNo + param + pageSize, json);
                jedis.expire(pageNo + param + pageSize, 2 * 60 * 60);
                jedis.close();
                super.ResponseJson(response, json);
                return;
            }
        }
    }

    /*测试*/
    @RequestMapping(value = "/check.html")
    public void check(HttpServletRequest request, HttpServletResponse response) {
        List<String> messages = new ArrayList<String>();
        String content = super.tokenCheck(response, request, messages, jedisPool);
        if (content == null || content.equals("[]") || content.equals("")) {
            return;
        }
        UserEntity entity = JSON.parseObject(content, UserEntity.class);
        messages.add("欢迎！" + entity.getName());
        super.ResponseSuccess(response, messages);
    }

    @RequestMapping(value = "/tagquery.html")
    public void querytag(HttpServletResponse response, String tag, PicQueryDto dto) {
        Jedis jedis = jedisPool.getResource();
        String pageSize = String.valueOf(dto.getPageSize());
        String pageNo = String.valueOf(dto.getPageNo());
        String result;
        Integer count;
        List<PicturesEntity> entityList;
        Map<String, Object> map = new HashMap<String, Object>();
        /*判断redis是否有值，有则从redis中取*/
        result = jedis.get(pageNo + tag + pageSize);
        if (result != null && !result.equals("[]")) {
            jedis.close();
            super.ResponseJson(response, result);
            return;
        }
        /*没有值去查数据库，并将数据存入redis*/
        else {
            count = pictureservice.selectCount();
            dto.setTotalCount(count);
            entityList = pictureservice.selectAll(dto);
            List<PicturesEntity> lists = this.getList(entityList, tag);
            map.put("size", lists.size());
            map.put("list", lists);
            String json = JSON.toJSONString(map);
            if (lists.size() > 0) {
                jedis.set(pageNo + tag + pageSize, json);
                jedis.expire(pageNo + tag + pageSize, 2 * 60 * 60);
            }
            jedis.close();
            super.ResponseJson(response, json);
            return;
        }
    }

    @RequestMapping(value = "/selectid.html")
    public void selectId(String id, HttpServletResponse response) {
        Jedis jedis = jedisPool.getResource();
        String result;
        List<PicturesEntity> entityList;
        /*判断redis是否有值，有则从redis中取*/
        result = jedis.get(id);
        if (result != null && !result.equals("[]")) {
            jedis.close();
            super.ResponseJson(response, result);
            return;
        }
        /*没有值去查数据库，并将数据存入redis*/
        else {
            entityList = pictureservice.selectById(id);
            String json = JSON.toJSONString(entityList);
            jedis.set(id, json);
            jedis.expire(id, 2 * 60 * 60);
            jedis.close();
            super.ResponseJson(response, json);
            return;
        }
    }


    private List<PicturesEntity> getList(List<PicturesEntity> entities, String tag) {
        List<PicturesEntity> result = new ArrayList<PicturesEntity>();
        for (PicturesEntity entity : entities) {
            if (entity.getTag().contains(tag)) {
                result.add(entity);
            }
        }
        return result;
    }
}
