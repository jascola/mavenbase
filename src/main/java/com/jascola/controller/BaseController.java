package com.jascola.controller;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    public static final String VIEW = "view";
    public static final String LIST = "list";
    public static final String STATUS = "status";
    public static final String WARN = "warn";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String MESSAGES = "messages";
    public static final String CONTENT = "content";

    public BaseController() {

    }

    public String ajax(HttpServletResponse response, String content, String type) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0L);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException var5) {
        }

        return null;
    }
    public void ajaxData(HttpServletResponse response, Object obj) {
        ajax(response, JSON.toJSONString(obj), "text/html");
    }
    public String ajaxText(HttpServletResponse response, String text) {
        return this.ajax(response, text, "text/plain");
    }

    public String ajaxHtml(HttpServletResponse response, String html) {
        return this.ajax(response, html, "text/html");
    }

    public String ajaxXml(HttpServletResponse response, String xml) {
        return this.ajax(response, xml, "text/xml");
    }

    public String ajaxJson(HttpServletResponse response, String jsonString) {
        return this.ajax(response, jsonString, "text/html");
    }

    public String ajaxJson(HttpServletResponse response, Map<String, String> jsonMap) {
        return this.ajax(response, JSON.toJSONString(jsonMap), "text/html");
    }

    public String ajaxJsonWarnMessage(HttpServletResponse response, String message) {
        Map<String, String> jsonMap = new HashMap();
        jsonMap.put("status", "warn");
        jsonMap.put("message", message);
        return this.ajax(response, JSON.toJSONString(jsonMap), "text/html");
    }

    public String ajaxJsonWarnMessages(HttpServletResponse response, List<String> messages) {
        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("status", "warn");
        jsonMap.put("messages", messages);
        return this.ajax(response, JSON.toJSONString(jsonMap), "text/html");
    }

    public String ajaxJsonSuccessMessage(HttpServletResponse response, String message) {
        Map<String, String> jsonMap = new HashMap();
        jsonMap.put("status", "success");
        jsonMap.put("message", message);
        return this.ajax(response, JSON.toJSONString(jsonMap), "text/html");
    }

    public String ajaxJsonSuccessMessages(HttpServletResponse response, List<String> messages) {
        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("status", "success");
        jsonMap.put("messages", messages);
        return this.ajax(response, JSON.toJSONString(jsonMap), "text/html");
    }

    public String ajaxJsonErrorMessage(HttpServletResponse response, String message) {
        Map<String, String> jsonMap = new HashMap();
        jsonMap.put("status", "error");
        jsonMap.put("message", message);
        return this.ajax(response, JSON.toJSONString(jsonMap), "text/html");
    }

    public String ajaxJsonErrorMessages(HttpServletResponse response, List<String> messages) {
        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("status", "error");
        jsonMap.put("messages", messages);
        return this.ajax(response, JSON.toJSONString(jsonMap), "text/html");
    }

    public void setResponseNoCache(HttpServletResponse response) {
        response.setHeader("progma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0L);
    }
}