package com.xdclass.xdclass.service;

import com.xdclass.xdclass.model.entity.User;
import com.xdclass.xdclass.mapper.UserMapper;
import com.xdclass.xdclass.util.CommonUtil;
import com.xdclass.xdclass.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(Map<String, String> map) {
        User user = parseUser(map);
        if (null != user) {
            return userMapper.save(user);
        }
        return -1;
    }

    /**
     * 获取随机头像
     *
     * @return
     */
    private String getRandom() {
        int length = headImg.length;
        Random random = new Random();
        int i = random.nextInt(length);
        return headImg[i];
    }

    private static final String[] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private User parseUser(Map<String, String> map) {
        if (map.containsKey("phone") && map.containsKey("pwd") && map.containsKey("name")) {
            User user = new User();
            user.setPhone(map.get("phone"));
            user.setName(map.get("name"));
            user.setCreateTime(new Date());
            //MD5加密
            String pwd = map.get("pwd");
            user.setPwd(CommonUtil.MD5(pwd));
            user.setHeadImg(getRandom());
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User findByPhone(String phone) {
        return null;
    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone, CommonUtil.MD5(pwd));
        return user == null ? null : JWTUtil.geneJsonWebToken(user);
    }

    @Override
    public User findByUserId(Integer id) {
        User user = userMapper.findByUserId(id);
        return user;
    }
}
