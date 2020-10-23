package com.tust.school.res.domain.dto;

import com.tust.school.res.domain.entity.ClaimVoucher;
import com.tust.school.res.domain.entity.ClaimVoucherItem;

import java.util.List;

public class ClaimVoucherInfo {
    private ClaimVoucher           claimVoucher;
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
