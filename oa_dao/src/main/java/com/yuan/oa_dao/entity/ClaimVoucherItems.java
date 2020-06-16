package com.yuan.oa_dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ClaimVoucherItems {
    private static final long serialVersionUID=1L;
    @TableId
    private Integer id;

    @TableField("claimvoucher_id")
    private Integer claimVoucherId;
    private String item;
    private Double amount;

    @TableField("COMMENT")
    private String comment;
}
