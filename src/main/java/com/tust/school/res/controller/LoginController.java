package com.tust.school.res.controller;

import com.tust.school.res.consts.UserConstants;
import com.tust.school.res.domain.dto.login.LoginParam;
import com.tust.school.res.domain.dto.login.LoginResult;
import com.tust.school.res.domain.enums.AccountTypeEnum;
import com.tust.school.res.domain.result.ResultWrap;
import com.tust.school.res.service.LoginService;
import com.tust.school.res.utils.Md5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/login/index", method = RequestMethod.GET)
    public String loginIndex() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrap<LoginResult> login(@Validated @RequestBody LoginParam loginParam, HttpServletResponse response) throws Exception {
        ResultWrap<LoginResult> resultWrap = new ResultWrap<>();

        loginParam.setPassword(Md5Utils.md5Sign(loginParam.getPassword()));

        LoginResult loginResult = loginService.login(loginParam);

        if (Objects.equals(loginResult.getAccountType(), AccountTypeEnum.STUDENT.getType())) {
            loginResult.setViewName("/index/student");
        }
        if (Objects.equals(loginResult.getAccountType(), AccountTypeEnum.TEACHER.getType())) {
            loginResult.setViewName("/index/teacher");
        }

        if (Objects.equals(loginResult.getAccountType(), AccountTypeEnum.ADMIN.getType())) {
            loginResult.setViewName("/index/admin");
        }

        Cookie cookie = new Cookie(UserConstants.COOKIE_NAME, loginResult.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);

        loginResult.setToken("");
        resultWrap.setSuccess(true);
        resultWrap.setResult(loginResult);
        return resultWrap;
    }
}
