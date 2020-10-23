package com.tust.school.res.domain.dto.course;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class InputScoreParam {

    /**
     * 学生id
     */
    @NotNull(message = "学生id不能为空")
    private Integer studentId;

    /**
     * 课程id
     */
    @NotNull(message = "课程id不能为空")
    private Integer courseId;

    /**
     * 成绩
     */
    @Range(min = 0, max = 1000, message = "分数范围0-1000")
    private Double score;
}
