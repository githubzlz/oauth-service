package com.zlz.oauth.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zlz.oauth.common.dos.user.UserDO;
import com.zlz.oauth.common.dto.UserDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhulinzhong
 * @date 2022-04-02 16:22:38
 */
public class TokenUtil {

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    /**
     * 私钥
     */
    private static final String TOKEN_SECRET = "eyJUeXBlIjoiSnd0IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE2NDg4OTAwNDcsInVzZXJJZCI6MTAwODZ9.w4T8SOQigwLngATETMXaPfEwOKR6Ayuxmrink2FNG2c";

    /**
     * 生成签名，15分钟过期
     *
     * @param **username**
     * @param **password**
     * @return
     */
    public static String sign(UserDTO user) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", user.getUserId())
                    .withClaim("nickname", user.getNickname())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     *
     * @param **token**
     * @return
     */
    public static UserDTO verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            if(jwt == null){
                return null;
            }
            Long userId = jwt.getClaim("userId").asLong();
            UserDTO user = new UserDTO();
            user.setUserId(userId);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}
