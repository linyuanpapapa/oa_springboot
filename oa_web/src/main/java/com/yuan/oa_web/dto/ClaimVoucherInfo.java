package com.yuan.oa_web.dto;

import com.yuan.oa_dao.entity.claimVoucher;
import com.yuan.oa_dao.entity.claimVoucherItems;

import java.util.List;

public class ClaimVoucherInfo {
    private claimVoucher claimVoucher;
    private List<claimVoucherItems> items;

    public claimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(claimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<claimVoucherItems> getItems() {
        return items;
    }

    public void setItems(List<claimVoucherItems> items) {
        this.items = items;
    }
}
