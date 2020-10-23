package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@TableName("course")
@Data
public class Course {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("no")
    private String no;

    @TableField("course_start")
    private Time courseStart;

    @TableField("course_end")
    private Time courseEnd;

    @TableField("course_day")
    private Integer courseDay;

    @TableField("max_stu_num")
    private Integer maxStuNum;

    @TableField("current_stu_num")
    private Integer currentStuNum;

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

}
