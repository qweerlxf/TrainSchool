package com.tust.school.res.service;

import com.tust.school.res.domain.entity.ClaimVoucher;
import com.tust.school.res.domain.entity.ClaimVoucherItem;
import com.tust.school.res.domain.entity.DealRecord;

import java.util.List;

public interface ClaimVoucherService {

    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    ClaimVoucher get(int id);   //报销单编号
    List<ClaimVoucherItem> getItems(int cvid);  //审核记录
    List<DealRecord> getRecords(int cvid);  //传入的报销单编号

    List<ClaimVoucher> getForSelf(Integer sn);
    List<ClaimVoucher> getForDeal(Integer teacherId);

    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);
    void submit(int id);
    void deal(DealRecord dealRecord);
}
