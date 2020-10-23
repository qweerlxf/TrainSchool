package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("rel_stu_course")
public class RelStuCourse {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("stu_id")
    private Integer stuId;

    @TableField("course_id")
    private Integer courseId;

    @TableField("score")
    private Double score;

    @TableField("creator_id")
    private Integer creatorId;

    @TableField("create_date")
    private Date createDate;

    @TableField("update_id")
    private Integer updateId;

    @TableField("update_date")
    private Date updateDate;

    @TableField("has_delete")
    private Integer hasDelete;
}
