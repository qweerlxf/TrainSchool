package com.tust.school.res.service;

import com.tust.school.res.domain.dto.login.LoginParam;
import com.tust.school.res.domain.dto.login.LoginResult;

public interface LoginService {

    /**
     * 用户登录
     *
     * @param loginParam
     * @return
     */
    LoginResult login(LoginParam loginParam);
}
