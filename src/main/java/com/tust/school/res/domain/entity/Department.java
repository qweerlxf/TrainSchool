package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("department")
public class Department {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("sn")
    private String sn;

    @TableField("name")
    private String name;

    @TableField("address")
    private String address;

}