package com.tust.school.res.domain.dto.classroom;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class ClassroomSaveParam {

    /**
     * 教室id
     */
    private Integer id;

    /**
     * 教室名称
     */
    @NotBlank(message = "教室名称不能为空")
    private String name;

    /**
     * 最大学生数
     */
    @Range(min = 0, max = 2000, message = "教室范围0-2000")
    private Integer maxStuNum;

    /**
     * 是否多媒体
     */
    @Range(min = 0, max = 1, message = "是否多媒体0-1")
    private Integer hasVideo;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;
}
