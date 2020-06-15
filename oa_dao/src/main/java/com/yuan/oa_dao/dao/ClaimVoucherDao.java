package com.yuan.oa_dao.dao;

import com.yuan.oa_dao.entity.ClaimVoucher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Repository("claimVoucherDao")
@Mapper
public interface ClaimVoucherDao {
    void insert(ClaimVoucher claimVoucher);
    void update(ClaimVoucher claimVoucher);
    void delete(int id);
    ClaimVoucher select(int id);
    List<ClaimVoucher> selectByCreateSn(String csn);
    List<ClaimVoucher> selectByNextDealSn(String ndsn);
}
