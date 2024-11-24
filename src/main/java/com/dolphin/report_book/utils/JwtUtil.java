package com.dolphin.report_book.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;
public class JwtUtil {

    private static final String KEY = "dolphin";

    //接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> T) {
        return JWT.create()
                .withClaim("claims", T)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 48))//48小时过期
                .sign(Algorithm.HMAC256(KEY));
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}