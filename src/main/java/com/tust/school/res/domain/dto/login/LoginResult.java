package com.tust.school.res.domain.dto.login;

import lombok.Data;

@Data
public class LoginResult {

    /**
     * 登录后的token
     */
    private String token;

    /**
     * 账号类型，99 admin，1学生，2老师
     */
    private Integer accountType;

    /**
     * 跳转视图名称
     */
    private String viewName;
}
