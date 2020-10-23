package com.tust.school.res.config.interceptor;

import com.tust.school.res.consts.UserConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
@Component
public class TokenHelper {
    /**
     * 加密秘钥
     */
    private static String SECRET = "IukWSfBLtHVEFY8fUveRie5ZEsSTK4aZ";

    /**
     * 有效时间
     */
    private static long EXPIRE = 60 * 60 * 3;

    public static final String BEARER = "Bearer ";

    /**
     * 生成Token签名
     *
     * @param userId 用户ID
     * @return 签名
     */
    public String generate(Integer accountType, Integer userId, String no, String name) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(UserConstants.ACCOUNT_TYPE, accountType);
        claims.put(UserConstants.USER_ID, userId);
        claims.put(UserConstants.NO, no);
        claims.put(UserConstants.NAME, name);

        long nowMillis = System.currentTimeMillis();

        String token = Jwts.builder()
                .setId(String.valueOf(userId))
                .setClaims(claims)
                .setSubject("auth")
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + EXPIRE * 1000))
                .compact();

        return token;
    }

    /**
     * 解析Token
     *
     * @param token 签名
     * @return 签名信息
     */
    public Claims parser(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token).getBody();
    }
}


