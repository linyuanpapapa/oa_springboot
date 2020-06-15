package com.yuan.oa_biz.impl;

import com.yuan.oa_biz.ClaimVoucherBiz;
import com.yuan.oa_dao.dao.ClaimVoucherDao;
import com.yuan.oa_dao.dao.DealRecordDao;
import com.yuan.oa_dao.dao.EmployeeDao;
import com.yuan.oa_dao.dao.claimVoucherItemsDao;
import com.yuan.oa_dao.entity.Employee;
import com.yuan.oa_dao.entity.claimVoucher;
import com.yuan.oa_dao.entity.claimVoucherItems;
import com.yuan.oa_dao.entity.dealRecord;
import com.yuan.oa_dao.global.contant;
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
    private claimVoucherItemsDao claimVoucherItemsDao;
    @Qualifier("dealRecordDao")
    @Autowired
    private DealRecordDao dealRecordDao;
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    public void save(claimVoucher claimVoucher, List<claimVoucherItems> items) {
        claimVoucher.setCreateTime(new Date());
        System.out.println(claimVoucher.getCreateTime());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(contant.CLAIMVOUCHER_CREATED);

        claimVoucherDao.insert(claimVoucher);

        for (claimVoucherItems item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemsDao.insert(item);
        }
    }

    public claimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    public List<claimVoucherItems> getItems(int cvid) {
        return claimVoucherItemsDao.selectByClaimVoucher(cvid);
    }

    public List<dealRecord> getRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    public List<claimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    public List<claimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }

    public void update(claimVoucher claimVoucher, List<claimVoucherItems> items) {
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(contant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.update(claimVoucher);

        List<claimVoucherItems> olds = claimVoucherItemsDao.selectByClaimVoucher(claimVoucher.getId());
        for (claimVoucherItems old : olds) {
            boolean isHave = false;
            for (claimVoucherItems item : items) {
                if (item.getId() == old.getId()) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                claimVoucherItemsDao.delete(old.getId());
            }
        }
            for(claimVoucherItems item:items){
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
        claimVoucher claimVoucher = claimVoucherDao.select(id);
        Employee employee=employeeDao.selectOne(claimVoucher.getCreateSn());
        claimVoucher.setStatus(contant.CLAIMVOUCHER_SUBMIT);
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(),contant.POST_FM).get(0).getSn());
        claimVoucherDao.update(claimVoucher);

        dealRecord dealRecord=new dealRecord();
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealTime(new Date());
        dealRecord.setDealWay(contant.DEAL_SUBMIT);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setComment("无");
        dealRecord.setDealResult(contant.CLAIMVOUCHER_SUBMIT);
        dealRecordDao.insert(dealRecord);
    }

    public void deal(dealRecord dealRecord) {
        claimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        Employee employee=employeeDao.selectOne(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());
        if(dealRecord.getDealWay().equals(contant.DEAL_PASS)){
            if(claimVoucher.getTotalCount()<=contant.LIMIT_CHECK||employee.getPost().equals(contant.POST_GM)){
                claimVoucher.setStatus(contant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,contant.POST_CASHIER).get(0).getSn());

                dealRecord.setDealResult(contant.CLAIMVOUCHER_APPROVED);
            }else{
                claimVoucher.setStatus(contant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,contant.POST_GM).get(0).getSn());

                dealRecord.setDealResult(contant.CLAIMVOUCHER_RECHECK);
            }
        }else if(dealRecord.getDealWay().equals(contant.DEAL_REJECT)){
            claimVoucher.setStatus(contant.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(contant.CLAIMVOUCHER_TERMINATED);
        }else if(dealRecord.getDealWay().equals(contant.DEAL_BACK)){
            claimVoucher.setStatus(contant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealResult(contant.CLAIMVOUCHER_BACK);
        }else if(dealRecord.getDealWay().equals(contant.DEAL_PAID)){
            claimVoucher.setStatus(contant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(contant.CLAIMVOUCHER_PAID);
        }

        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }


}



