package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("deal_record")
public class DealRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("claim_voucher_id")
    private Integer claimVoucherId;

    @TableField("deal_sn")
    private Integer dealSn;

    @TableField("deal_time")
    private Date dealTime;

    @TableField("deal_way")
    private String dealWay;

    @TableField("deal_result")
    private String dealResult;

    @TableField("comment")
    private String comment;

}