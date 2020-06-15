package com.yuan.oa_biz;

import com.yuan.oa_dao.entity.claimVoucher;
import com.yuan.oa_dao.entity.claimVoucherItems;
import com.yuan.oa_dao.entity.dealRecord;

import java.util.List;

public interface ClaimVoucherBiz {

    void save(claimVoucher claimVoucher, List<claimVoucherItems> items);

    claimVoucher get(int id);
    List<claimVoucherItems> getItems(int cvid);
    List<dealRecord> getRecords(int cvid);

    List<claimVoucher> getForSelf(String sn);
    List<claimVoucher> getForDeal(String sn);

    void update(claimVoucher claimVoucher, List<claimVoucherItems> items);

    void submit(int id);
    void deal(dealRecord dealRecord);
}
