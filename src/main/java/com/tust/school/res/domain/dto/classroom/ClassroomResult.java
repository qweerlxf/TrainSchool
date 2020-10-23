package com.tust.school.res.domain.dto.classroom;

import lombok.Data;

@Data
public class ClassroomResult {

    /**
     * 教室id
     */
    private Integer id;

    /**
     * 教室编号
     */
    private String no;

    /**
     * 教室名称
     */
    private String name;

    /**
     * 最大学生数
     */
    private Integer maxStuNum;

    /**
     * 是否多媒体
     */
    private Integer hasVideo;
    private String hasVideoStr;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态
     */
    private Integer status;
    private String statusStr;
}
