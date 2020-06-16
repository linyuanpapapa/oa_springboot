package com.yuan.oa_dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.oa_dao.entity.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dealRecordDao")
public interface DealRecordDao extends BaseMapper<DealRecord> {
    List<DealRecord> selectByClaimVoucher(int cvid);
}
