package com.tust.school.res.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tust.school.res.domain.entity.DealRecord;

import java.util.List;

/**
 * @author luoxiaofang
 * @create 2020-06-03 17:11
 */
public interface DealRecordMapper extends BaseMapper<DealRecord> {
    int insert(DealRecord dealRecord);
    List<DealRecord> selectByClaimVoucher(int cvid);
}
