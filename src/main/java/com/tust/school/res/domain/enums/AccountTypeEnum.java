package com.tust.school.res.domain.enums;

import lombok.Getter;

@Getter
public enum AccountTypeEnum {

    STUDENT(1, "学生账号"), TEACHER(2, "老师账号"), ADMIN(3, "管理员账号");

    /**
     * 账号类型
     */
    private Integer type;

    /**
     * 描述
     */
    private String desc;

    private AccountTypeEnum(Integer type, String desc) {
        this.desc = desc;
        this.type = type;
    }
}
