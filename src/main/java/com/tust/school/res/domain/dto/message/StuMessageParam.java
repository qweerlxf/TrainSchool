package com.tust.school.res.domain.dto.message;

import lombok.Data;

/**
 * @author luoxiaofang
 * @create 2020-05-24 17:07
 */
@Data
public class StuMessageParam {

    /**
     * 留言id
     */
    private Integer id;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 学生id
     */
    private Integer stuId;

    /**
     * 状态
     */
    private Integer status;
}
