package com.jascola.controller;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletResponse;

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
 *
 */
public class BaseController {
    private static final Logger log = Logger.getLogger(BaseController.class);
    public BaseController() {
    }

    public String ajaxResponse(HttpServletResponse response, String content, String type) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0L);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public String ResponseJson(HttpServletResponse response, String jsonString) {
        return this.ajaxResponse(response, jsonString, "text/html");
    }
}
