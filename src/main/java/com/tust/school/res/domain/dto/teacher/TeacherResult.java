package com.tust.school.res.domain.dto.teacher;

import lombok.Data;

@Data
public class TeacherResult {

    /**
     * 教师id
     */
    private Integer id;

    /**
     * 教师编号
     */
    private String no;

    /**
     * 教师名称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 专业
     */
    private String specialty;

    /**
     * 状态
     */
    private Integer status;
    private String statusStr;

}
