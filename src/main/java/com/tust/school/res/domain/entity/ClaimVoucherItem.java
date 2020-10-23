package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("claim_voucher_item")
public class ClaimVoucherItem {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("claim_voucher_id")
    private Integer claimVoucherId;

    @TableField("item")
    private String item;

    @TableField("amount")
    private Double amount;

    @TableField("comment")
    private String comment;

}