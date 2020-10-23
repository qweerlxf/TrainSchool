package com.tust.school.res.config.handler;

import lombok.Getter;

@Getter
public enum ServiceExceptionEnum {
    /**
     * 系统错误
     */
    SYS_ERROR(5000, "系统错误"),

    /**
     * 用户错误
     */
    USER_LOGIN_ERROR(5100, "用户名或密码错误"),
    TWICE_PASSWORD_NOT_EQUALS(5101, "两次密码不相等"),

    COURSE_NOT_EXISTS(5200, "课程不存在"),
    COURSE_MAX_STUDENT(5201, "选课学生数已达上限"),
    ALREADY_SELECTED_COURSE(5202, "你已选择该课程,请勿重复选择"),
    COURSE_CONFLICT(5202, "课程时间冲突"),
    STUDENT_NOT_SELECT_COURSE(5203, "学生未选择该课程"),


    ;

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String message;

    private ServiceExceptionEnum(Integer code, String message) {
        this.errorCode = code;
        this.message = message;
    }
}
