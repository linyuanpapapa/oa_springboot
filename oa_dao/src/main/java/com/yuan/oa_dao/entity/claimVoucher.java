package com.yuan.oa_dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class claimVoucher {
    private Integer id;
    private String cause;
    private String createSn;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    private String nextDealSn;
    private Double totalCount;
    private String status;
    //创建者和处理者
    private Employee creater;
    private Employee dealer;

    public Employee getCreater() {
        return creater;
    }

    public void setCreater(Employee creater) {
        this.creater = creater;
    }

    public Employee getDealer() {
        return dealer;
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCreateSn() {
        return createSn;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNextDealSn() {
        return nextDealSn;
    }

    public void setNextDealSn(String nextDealSn) {
        this.nextDealSn = nextDealSn;
    }

    public Double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Double totalCount) {
        this.totalCount = totalCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "claimVoucher{" +
                "id=" + id +
                ", cause='" + cause + '\'' +
                ", createSn='" + createSn + '\'' +
                ", createTime=" + createTime +
                ", nextDealSn='" + nextDealSn + '\'' +
                ", totalCount=" + totalCount +
                ", status='" + status + '\'' +
                '}';
    }
}
