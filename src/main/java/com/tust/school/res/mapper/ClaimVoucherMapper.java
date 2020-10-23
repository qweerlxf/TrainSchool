package com.tust.school.res.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tust.school.res.domain.entity.ClaimVoucher;

import java.util.List;

/**
 * @author luoxiaofang
 * @create 2020-06-03 16:09
 */
public interface ClaimVoucherMapper extends BaseMapper<ClaimVoucher> {

    void update(ClaimVoucher claimVoucher);
    void delete(int id);
    ClaimVoucher select(int id);
    List<ClaimVoucher> selectByCreateSn(String csn);
    List<ClaimVoucher> selectByNextDealSn(String ndsn);

}
