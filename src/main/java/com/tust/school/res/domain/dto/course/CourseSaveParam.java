package com.tust.school.res.domain.dto.course;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Data
public class CourseSaveParam {

    private Integer id;

    /**
     * 课程名称
     */
    @NotBlank(message = "课程名称不能为空")
    private String name;

    /**
     * 课程开始时间
     */
    @NotNull(message = "课程开始时间不能为空")
    private Time courseStart;

    /**
     * 课程结束时间
     */
    @NotNull(message = "课程结束时间不能为空")
    private Time courseEnd;

    /**
     * 星期几上课
     */
    @Range(min = 1, max = 7, message = "星期范围1-7")
    @NotNull(message = "星期不能为空")
    private Integer courseDay;

    /**
     * 限选人数
     */
    @Range(min = 0, max = 1000, message = "限选人数范围为0-1000")
    @NotNull(message = "人数不能为空")
    private Integer maxStuNum;

    /**
     * 任教教师id
     */
    @NotNull(message = "任教教师不能为空")
    @Min(value = 1, message = "任教教师不能为空")
    private Integer teacherId;

    /**
     * 教室
     */
    @NotNull(message = "教室不能为空")
    @Min(value = 1, message = "教室不能为空")
    private Integer classroomId;
}
