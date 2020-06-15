package com.yuan.oa_dao.dao;

import com.yuan.oa_dao.entity.claimVoucherItems;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherItems")
public interface claimVoucherItemsDao {
    void insert(claimVoucherItems claimVoucherItems);
    void update(claimVoucherItems claimVoucherItems);
    void delete(int id);
    List<claimVoucherItems> selectByClaimVoucher(int cvid);
}
