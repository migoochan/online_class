package com.xdclass.xdclass.service;

import com.xdclass.xdclass.model.entity.User;

import java.util.Map;

public interface UserService {
    int save(Map<String, String> map);

    User findByPhone(String phone);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer id);
}
