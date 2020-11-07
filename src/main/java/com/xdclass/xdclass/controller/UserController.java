package com.xdclass.xdclass.controller;

import com.xdclass.xdclass.model.entity.User;
import com.xdclass.xdclass.model.request.LoginRequest;
import com.xdclass.xdclass.service.UserService;
import com.xdclass.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pri/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/register")
    public JsonData register(@RequestBody Map<String, String> userInfo) {
        int rows = userService.save(userInfo);
        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildError("注册失败,请重试");
    }

    /**
     * 登陆
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public JsonData login(@RequestBody LoginRequest loginRequest) {
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());
        return token == null ? JsonData.buildError("登陆失败,账号密码错误") : JsonData.buildSuccess(token);
    }

    @GetMapping("/findByToken")
    public JsonData findUserByToken(HttpServletRequest request) {
        Integer id = (Integer)request.getAttribute("user_id");
        if(null == id) {
            return JsonData.buildError("查询失败");
        }
        User user = userService.findByUserId(id);
        return JsonData.buildSuccess(user);
    }
}
