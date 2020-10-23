package com.tust.school.res.config.interceptor;

import com.tust.school.res.consts.UserConstants;
import com.tust.school.res.utils.UserContextHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Objects;

/**
 * 身份验证工具
 */
@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private TokenHelper tokenHelper;

    public static final String BEARER = "Bearer ";

    /**
     * 身份过滤验证
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), UserConstants.COOKIE_NAME)) {
                cookieValue = cookie.getValue();
                break;
            }
        }

        if (StringUtils.isEmpty(cookieValue)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        try {
            Claims claims = tokenHelper.parser(cookieValue);

            HashMap<String, Object> map = new HashMap<>(4);

            map.put(UserConstants.ACCOUNT_TYPE, claims.get(UserConstants.ACCOUNT_TYPE));
            map.put(UserConstants.USER_ID, claims.get(UserConstants.USER_ID));
            map.put(UserConstants.NO, claims.get(UserConstants.NO));
            map.put(UserConstants.NAME, claims.get(UserConstants.NAME));

            UserContextHelper.putUser(map);
        } catch (ExpiredJwtException e) {
            log.error("Auth code expired.authHeader:{}", cookieValue);
            response.setStatus(HttpStatus.PAYMENT_REQUIRED.value());
            return false;
        } catch (UnsupportedJwtException | IllegalArgumentException | MalformedJwtException e) {
            log.error("Auth token :{} is error", cookieValue, e);
            response.setStatus(HttpStatus.PAYMENT_REQUIRED.value());
            return false;
        }

        return true;
    }
}
