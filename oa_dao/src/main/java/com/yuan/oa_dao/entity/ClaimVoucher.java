package com.yuan.oa_dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ClaimVoucher extends Model<ClaimVoucher> {
    private static final long serialVersionUID=1L;

    @TableId
    private Integer id;
    private String cause;
    @TableField("create_sn")
    private String createSn;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField("create_time")
    private Date createTime;

    @TableField("next_deal_sn")
    private String nextDealSn;
    @TableField("total_count")
    private Double totalCount;
    private String status;


}
