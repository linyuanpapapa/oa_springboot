package com.yuan.oa_dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DealRecord {
    private static final long serialVersionUID=1L;
    @TableId
    private Integer id;
    @TableField("claimvoucher_id")
    private Integer claimVoucherId;
    @TableField("deal_sn")
    private String dealSn;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField("deal_time")
    private Date dealTime;
    @TableField("deal_way")
    private String dealWay;
    @TableField("deal_result")
    private String dealResult;
    @TableField("COMMENT")
    private String comment;
}
