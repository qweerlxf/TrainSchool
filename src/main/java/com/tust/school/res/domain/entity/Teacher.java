package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("teacher")
@Data
public class Teacher {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("no")
    private String no;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("specialty")
    private String specialty;

    @TableField("password")
    private String password;

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
