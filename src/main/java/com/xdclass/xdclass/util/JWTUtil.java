package com.xdclass.xdclass.util;

import com.xdclass.xdclass.model.entity.User;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 *
 */
public class JWTUtil {
    private static final long EXPIRE = 60000 * 60 * 24 * 7; //过期时间1周
    /**
     * 加密密钥
     */
    private static final String SECRET = "xdclass.net168";
    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "xdclass";
    /**
     * 主题
     */
    private static final String SUBJECT = "xdclass";


    /**
     * 根据用户信息生成令牌
     *
     * @param user
     * @retur
     */
    public static String geneJsonWebToken(User user) {
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img", user.getHeadImg())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();

        token = TOKEN_PREFIX + token;
        return token;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
}
