package com.tust.school.res.domain.dto.course;

import lombok.Data;

import java.sql.Time;

@Data
public class CourseResult {

    /**
     * 课程id
     */
    private Integer id;

    /**
     * 课程编号
     */
    private String no;

    /**
     * 课程开始时间
     */
    private Time courseStart;

    /**
     * 课程结束时间
     */
    private Time courseEnd;

    /**
     * 课程结束时间
     */
    private Integer courseDay;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 限选人数
     */
    private Integer maxStuNum;

    /**
     * 当前选课人数
     */
    private Integer currentStuNum;

    /**
     * 任教教师
     */
    private Integer teacherId;
    private String teacherName;

    /**
     * 状态
     */
    private Integer status;
    private String statusStr;

    /**
     * 教室
     */
    private Integer classroomId;
    private String classroomName;
}
