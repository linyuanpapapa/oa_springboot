package com.yuan.oa_biz.impl;

import com.yuan.oa_biz.ClaimVoucherBiz;
import com.yuan.oa_dao.dao.ClaimVoucherDao;
import com.yuan.oa_dao.dao.DealRecordDao;
import com.yuan.oa_dao.dao.EmployeeDao;
import com.yuan.oa_dao.dao.ClaimVoucherItemsDao;
import com.yuan.oa_dao.entity.ClaimVoucher;
import com.yuan.oa_dao.entity.DealRecord;
import com.yuan.oa_dao.entity.Employee;
import com.yuan.oa_dao.entity.ClaimVoucherItems;
import com.yuan.oa_dao.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("claimVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {

    @Autowired
    private ClaimVoucherDao claimVoucherDao;
    @Autowired
    private ClaimVoucherItemsDao claimVoucherItemsDao;
    @Qualifier("dealRecordDao")
    @Autowired
    private DealRecordDao dealRecordDao;
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItems> items) {
        claimVoucher.setCreateTime(new Date());
        System.out.println(claimVoucher.getCreateTime());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);

        claimVoucherDao.insert(claimVoucher);

        for (ClaimVoucherItems item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemsDao.insert(item);
        }
    }

    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    public List<ClaimVoucherItems> getItems(int cvid) {
        return claimVoucherItemsDao.selectByClaimVoucher(cvid);
    }

    public List<DealRecord> getRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }

    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItems> items) {
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.update(claimVoucher);

        List<ClaimVoucherItems> olds = claimVoucherItemsDao.selectByClaimVoucher(claimVoucher.getId());
        for (ClaimVoucherItems old : olds) {
            boolean isHave = false;
            for (ClaimVoucherItems item : items) {
                if (item.getId() == old.getId()) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                claimVoucherItemsDao.delete(old.getId());
            }
        }
            for(ClaimVoucherItems item:items){
                item.setClaimVoucherId(claimVoucher.getId());
                if(item.getId()!=null&&item.getId()>0){
                    System.out.println(item);
                    claimVoucherItemsDao.update(item);
                }else{
                    claimVoucherItemsDao.insert(item);
                }
            }
    }

    public void submit(int id){
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        Employee employee=employeeDao.selectOne(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(), Contant.POST_FM).get(0).getSn());
        claimVoucherDao.update(claimVoucher);

        DealRecord dealRecord=new DealRecord();
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealTime(new Date());
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setComment("无");
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecordDao.insert(dealRecord);
    }

    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        Employee employee=employeeDao.selectOne(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());
        if(dealRecord.getDealWay().equals(Contant.DEAL_PASS)){
            if(claimVoucher.getTotalCount()<= Contant.LIMIT_CHECK||employee.getPost().equals(Contant.POST_GM)){
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_CASHIER).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
            }else{
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_GM).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            }
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_REJECT)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_BACK)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_BACK);
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_PAID)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
        }

        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }


}



