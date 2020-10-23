package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author luoxiaofang
 * @create 2020-05-24 16:29
 */
@Data
@TableName("stu_message")
public class StuMessage {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("creator_id")
    private Integer creatorId;

    @TableField("create_date")
    private Date createDate;

    @TableField("update_id")
    private Integer updateId;

    @TableField("update_date")
    private Date updateDate;

    @TableField("status")
    private Integer status;

    @TableField("has_delete")
    private Integer hasDelete;

    @TableField("stu_id")
    private Integer stuId;

    @TableField("course_id")
    private Integer courseId;

    @TableField("content")
    private String content;
}
