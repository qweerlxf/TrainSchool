package com.tust.school.res.domain.enums;

import lombok.Getter;

@Getter
public enum  HasDeleteEnum {

    UN_DELETE(0, "未删除"), DELETED(1, "已删除");

    private Integer value;

    private String desc;

    private HasDeleteEnum(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }
}
