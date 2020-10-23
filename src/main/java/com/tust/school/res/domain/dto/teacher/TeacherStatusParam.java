package com.tust.school.res.domain.dto.teacher;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class TeacherStatusParam {

    @NotNull(message = "教师id不能为空")
    private Integer id;

    @Range(min = 0, max = 1, message = "状态范围0-1")
    private Integer status;
}
