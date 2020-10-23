package com.tust.school.res.domain.dto.course;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class CourseStatusParam {

    @NotNull(message = "id不能为空")
    private Integer id;

    @Range(min = 0, max = 1, message = "状态范围0-1")
    private Integer status;

}
