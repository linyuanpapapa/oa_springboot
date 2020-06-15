package com.yuan.oa_dao.dao;

import com.yuan.oa_dao.entity.ClaimVoucherItems;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherItems")
public interface ClaimVoucherItemsDao {
    void insert(ClaimVoucherItems claimVoucherItems);
    void update(ClaimVoucherItems claimVoucherItems);
    void delete(int id);
    List<ClaimVoucherItems> selectByClaimVoucher(int cvid);
}
