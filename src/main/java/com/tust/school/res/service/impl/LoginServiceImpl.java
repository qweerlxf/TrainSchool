package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.config.handler.ServiceException;
import com.tust.school.res.config.handler.ServiceExceptionEnum;
import com.tust.school.res.config.interceptor.TokenHelper;
import com.tust.school.res.domain.dto.login.LoginParam;
import com.tust.school.res.domain.dto.login.LoginResult;
import com.tust.school.res.domain.entity.Admin;
import com.tust.school.res.domain.entity.Student;
import com.tust.school.res.domain.entity.Teacher;
import com.tust.school.res.domain.enums.AccountTypeEnum;
import com.tust.school.res.domain.enums.BaseStatusEnum;
import com.tust.school.res.domain.enums.HasDeleteEnum;
import com.tust.school.res.mapper.AdminMapper;
import com.tust.school.res.mapper.StudentMapper;
import com.tust.school.res.mapper.TeacherMapper;
import com.tust.school.res.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private TokenHelper tokenHelper;

    @Override
    public LoginResult login(LoginParam loginParam) {
        LoginResult loginResult = new LoginResult();


        studentLogin(loginParam, loginResult);
        if (!StringUtils.isEmpty(loginResult.getToken())) {
            return loginResult;
        }

        teacherLogin(loginParam, loginResult);
        if (!StringUtils.isEmpty(loginResult.getToken())) {
            return loginResult;
        }

        adminLogin(loginParam, loginResult);
        if (StringUtils.isEmpty(loginResult.getToken())) {
            throw new ServiceException(ServiceExceptionEnum.USER_LOGIN_ERROR);
        }

        return loginResult;
    }

    private void adminLogin(LoginParam loginParam, LoginResult loginResult) {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("account", loginParam.getAccount());
        adminQueryWrapper.eq("status", BaseStatusEnum.ONLINE.getStatus());
        adminQueryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());

        Admin admin = adminMapper.selectOne(adminQueryWrapper);
        if (Objects.isNull(admin) || !Objects.equals(admin.getPassword(), loginParam.getPassword())) {
            return;
        }

        loginResult.setAccountType(AccountTypeEnum.ADMIN.getType());
        loginResult.setToken(tokenHelper.generate(AccountTypeEnum.ADMIN.getType(), admin.getId(), "", admin.getName()));
    }

    private void teacherLogin(LoginParam loginParam, LoginResult loginResult) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("no", loginParam.getAccount());
        teacherQueryWrapper.eq("status", BaseStatusEnum.ONLINE.getStatus());
        teacherQueryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());

        Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);
        if (Objects.isNull(teacher) || !Objects.equals(teacher.getPassword(), loginParam.getPassword())) {
            return;
        }

        loginResult.setAccountType(AccountTypeEnum.TEACHER.getType());
        loginResult.setToken(tokenHelper.generate(AccountTypeEnum.TEACHER.getType(), teacher.getId(), teacher.getNo(), teacher.getName()));
    }


    private void studentLogin(LoginParam loginParam, LoginResult loginResult) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("no", loginParam.getAccount());
        studentQueryWrapper.eq("status", BaseStatusEnum.ONLINE.getStatus());
        studentQueryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());

        Student student = studentMapper.selectOne(studentQueryWrapper);
        if (Objects.isNull(student) || !Objects.equals(student.getPassword(), loginParam.getPassword())) {
            return;
        }

        loginResult.setAccountType(AccountTypeEnum.STUDENT.getType());
        loginResult.setToken(tokenHelper.generate(AccountTypeEnum.STUDENT.getType(), student.getId(), student.getNo(), student.getName()));
    }
}
