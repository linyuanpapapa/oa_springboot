package com.yuan.oa_dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.oa_dao.dto.CVWithCreatorAndDealer;
import com.yuan.oa_dao.entity.ClaimVoucher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherDao")
public interface ClaimVoucherDao extends BaseMapper<ClaimVoucher> {

    List<ClaimVoucher> selectByCreateSn(String csn);
    List<ClaimVoucher> selectByNextDealSn(String ndsn);
}
