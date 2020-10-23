package com.tust.school.res.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("claim_voucher")
public class ClaimVoucher {
    //编号
    @TableId(type = IdType.AUTO)
    private Integer id;

    //事由
    @TableField("cause")
    private String cause;

    //创建者
    @TableField("create_sn")
    private String createSn;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @TableField("create_time")
    private Date createTime;

//    //待处理人
//    @TableField("next_deal_sn")
//    private String nextDealSn;

    //总金额
    @TableField("total_amount")
    private Double totalAmount;

    @TableField("status")
    private String status;

}