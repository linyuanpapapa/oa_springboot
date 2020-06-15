package com.yuan.oa_dao.dao;

import com.yuan.oa_dao.entity.claimVoucher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Repository("claimVoucherDao")
@Mapper
public interface ClaimVoucherDao {
    void insert(claimVoucher claimVoucher);
    void update(claimVoucher claimVoucher);
    void delete(int id);
    claimVoucher select(int id);
    List<claimVoucher> selectByCreateSn(String csn);
    List<claimVoucher> selectByNextDealSn(String ndsn);
}
