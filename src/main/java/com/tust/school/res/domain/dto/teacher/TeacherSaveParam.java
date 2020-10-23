package com.tust.school.res.domain.dto.teacher;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TeacherSaveParam {

    /**
     * 教师id，更新必传
     */
    private Integer id;

    /**
     * 教师名称
      */
    @NotEmpty(message = "教师名称不能为空")
    private String name;

    /**
     * 教师手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotEmpty(message = "专业不能为空")
    private String specialty;

}
