package com.xdclass.xdclass.intercepter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdclass.xdclass.util.JWTUtil;
import com.xdclass.xdclass.util.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginIntercepter implements HandlerInterceptor {
    /**
     * 进入controller之前的方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            if (null == token) {
                token = request.getParameter("token");
            }
            if (StringUtils.isNotBlank(token)) {
                Claims claims = JWTUtil.checkJWT(token);
                if (null == claims) {
                    //告诉登陆过期, 重新登陆
                    sendJsonMessage(response, JsonData.buildError("登陆过期,请重新登陆"));
                    return false;
                }
                //用户的id
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                request.setAttribute("user_id", id);
                request.setAttribute("name", name);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendJsonMessage(response, JsonData.buildError("登陆异常,请联系管理员!!"));
            return false;
        }
        //登陆失败
        sendJsonMessage(response, JsonData.buildError("登陆过期,请重新登陆"));
        return false;
    }

    /**
     * 响应json数据给前端
     *
     * @param response
     * @param o
     */
    public static void sendJsonMessage(HttpServletResponse response, Object o) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(o));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
