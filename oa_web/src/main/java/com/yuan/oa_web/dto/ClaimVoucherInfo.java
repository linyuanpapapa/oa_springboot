package com.yuan.oa_web.dto;

import com.yuan.oa_dao.entity.ClaimVoucher;
import com.yuan.oa_dao.entity.ClaimVoucherItems;

import java.util.List;

public class ClaimVoucherInfo {
    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItems> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItems> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItems> items) {
        this.items = items;
    }
}
