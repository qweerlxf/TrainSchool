package com.tust.school.res.domain.dto.message;

import lombok.Data;

/**
 * @author luoxiaofang
 * @create 2020-05-24 16:40
 */
@Data
public class StuMessageAddParam {

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 留言内容
     */
    private String content;
}
