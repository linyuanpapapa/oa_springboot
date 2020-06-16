package com.yuan.oa_dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.oa_dao.entity.ClaimVoucherItems;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherItems")
public interface ClaimVoucherItemsDao extends BaseMapper<ClaimVoucherItems> {
    List<ClaimVoucherItems> selectByClaimVoucher(int cvid);
}
