package com.tust.school.res.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tust.school.res.domain.entity.ClaimVoucherItem;

import java.util.List;

/**
 * @author luoxiaofang
 * @create 2020-06-03 16:10
 */
public interface ClaimVoucherItemMapper extends BaseMapper<ClaimVoucherItem> {

    void update(ClaimVoucherItem claimVoucherItem);
    void delete(int id);
    List<ClaimVoucherItem> selectByClaimVoucher(int cvid);

}
