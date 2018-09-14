package com.jascola.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数请求乱码问题：（1）tomcat配置文件下修改成如下
 * <Connector port="80" protocol="HTTP/1.1"
 * connectionTimeout="20000"
 * redirectPort="8443" URIEncoding="-UTF8"/>
 * （2）web.xml中加入编码拦截器
 * （3）所有页面，ide编码改为utf-8
 *
 * @EnableWebMvc注解可以将@RequestBody有效化
 * @Requestbody可以将json字符串自动绑定实体对象
 */
public class BaseController {
    private static final Logger log = Logger.getLogger(BaseController.class);
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String WARN = "warn";
    protected BASE64Encoder base64Encoder = new BASE64Encoder();
    protected BASE64Decoder base64Decoder = new BASE64Decoder();

    public BaseController() {
    }

    private void ajaxResponse(HttpServletResponse response, String content, String type) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0L);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    public void ResponseJson(HttpServletResponse response, String jsonString) {
        this.ajaxResponse(response, jsonString, "text/html");
    }

    public void ResponseSuccess(HttpServletResponse response, List<String> messages) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", SUCCESS);
        map.put("messages", messages);
        this.ajaxResponse(response, JSON.toJSONString(map), "text/html");
    }

    public void ResponseError(HttpServletResponse response, List<String> messages) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", ERROR);
        map.put("messages", messages);
        this.ajaxResponse(response, JSON.toJSONString(map), "text/html");
    }

    public void ResponseWarn(HttpServletResponse response, List<String> messages) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", WARN);
        map.put("messages", messages);
        this.ajaxResponse(response, JSON.toJSONString(map), "text/html");
    }

    public String tokenCheck(HttpServletResponse response, HttpServletRequest request, List<String> messages, JedisPool jedisPool) {
        String value = null;
        String content = null;
        for (Cookie c :
                request.getCookies()) {
            if (c.getName().equals("token")) {
                value = c.getValue();
            }
            if (value != null) {
                Jedis jedis = jedisPool.getResource();
                jedis.select(15);
                try {
                    content = jedis.get(new String(base64Decoder.
                            decodeBuffer(value)));
                    if (content == null) {
                        messages.add("时间失效");
                        ResponseError(response, messages);
                        return null;
                    }
                } catch (Exception e) {
                    log.error(e.getLocalizedMessage(), e);
                } finally {
                    jedis.close();
                }
            }
        }
        if (value == null) {
            messages.add("时间失效");
            ResponseError(response, messages);
            return null;
        }
        return content;
    }

    public String tokenAdminCheck(HttpServletResponse response, HttpServletRequest request, List<String> messages, JedisPool jedisPool) {
        String value = null;
        String content = null;
        for (Cookie c :
                request.getCookies()) {
            if (c.getName().equals("admintoken")) {
                value = c.getValue();
            }
            if (value != null) {
                Jedis jedis = jedisPool.getResource();
                jedis.select(15);
                try {
                    content = jedis.get(new String(base64Decoder.
                            decodeBuffer(value)));
                    if (content == null) {
                        messages.add("登录过期，请重新登录！");
                        ResponseError(response, messages);
                        return null;
                    }
                } catch (Exception e) {
                    log.error(e.getLocalizedMessage(), e);
                } finally {
                    jedis.close();
                }
            }
        }
        if (value == null) {
            messages.add("登录过期，请重新登录！");
            ResponseError(response, messages);
            return null;
        }
        return content;
    }
}
