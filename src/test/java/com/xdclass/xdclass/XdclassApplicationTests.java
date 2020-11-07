package com.xdclass.xdclass;

import com.xdclass.xdclass.model.entity.User;
import com.xdclass.xdclass.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XdclassApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void testGenerateJWT() {
        User user = new User();
        user.setId(66);
        user.setHeadImg("png");
        user.setName("chenliang");
        String s = JWTUtil.geneJsonWebToken(user);
        System.out.println(s);
        Claims claims = JWTUtil.checkJWT(s);
        System.out.println(claims.get("name"));
    }




}
