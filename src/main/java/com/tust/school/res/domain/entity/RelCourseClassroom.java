package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("rel_course_classroom")
@Data
public class RelCourseClassroom {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("classroom_id")
    private Integer classroomId;

    @TableField("course_id")
    private Integer courseId;

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
