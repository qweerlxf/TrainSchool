package com.tust.school.res.domain.dto.message;

import lombok.Data;

/**
 * @author luoxiaofang
 * @create 2020-05-24 17:05
 */
@Data
public class StuMessageResult {

    /**
     * 学生信息
     */
    private Integer stuId;
    private String stuName;

    /**
     * 课程信息
     */
    private Integer courseId;
    private String courseName;

    /**
     * 留言内容
     */
    private String content;

}
