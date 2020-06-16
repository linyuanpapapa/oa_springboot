package com.yuan.oa_biz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.oa_dao.entity.ClaimVoucher;
import com.yuan.oa_dao.entity.ClaimVoucherItems;
import com.yuan.oa_dao.entity.DealRecord;

import java.util.List;

public interface ClaimVoucherBiz extends IService<ClaimVoucher> {

    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItems> items);

    ClaimVoucher get(int id);
    List<ClaimVoucherItems> getItems(int cvid);
    List<DealRecord> getRecords(int cvid);

    List<ClaimVoucher> getForSelf(String sn);
    List<ClaimVoucher> getForDeal(String sn);

    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItems> items);

    void submit(int id);
    void deal(DealRecord dealRecord);
}
