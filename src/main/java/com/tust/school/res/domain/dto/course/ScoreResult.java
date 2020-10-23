package com.tust.school.res.domain.dto.course;

import lombok.Data;

@Data
public class ScoreResult {

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 学生名称
     */
    private String studentName;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 成绩
     */
    private Double score;
}
