package com.tust.school.res.domain.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum BaseStatusEnum {

    ONLINE(1, "在线"), DOWN_LINE(0, "下线");

    private Integer status;

    private String desc;

    private BaseStatusEnum(Integer status, String desc) {
        this.desc = desc;
        this.status = status;
    }

    public static final BaseStatusEnum getByStatus(Integer status) {
        if (Objects.isNull(status)) {
            return null;
        }

        for (BaseStatusEnum baseStatusEnum : BaseStatusEnum.values()) {
            if (Objects.equals(baseStatusEnum.getStatus(), status)) {
                return baseStatusEnum;
            }
        }

        return null;
    }

    public static final String getDescByStatus(Integer status) {
        if (Objects.isNull(status)) {
            return "";
        }

        for (BaseStatusEnum baseStatusEnum : BaseStatusEnum.values()) {
            if (Objects.equals(baseStatusEnum.getStatus(), status)) {
                return baseStatusEnum.getDesc();
            }
        }

        return "";
    }
}
