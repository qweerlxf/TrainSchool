package com.tust.school.res.domain.dto.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginParam {

    /**
     * 账号，可能是学号，也可能是老师号，或者admin账号
     */
    @NotBlank(message = "用户账号不能为空")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String password;
}
