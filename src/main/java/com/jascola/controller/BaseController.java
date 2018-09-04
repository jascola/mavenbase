package com.jascola.controller;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletResponse;


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
