package com.yuan.oa_dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class Department extends Model<Department> {
    private static final long serialVersionUID=1L;
    @TableId
    private String sn;
    @TableField("NAME")
    private String name;
    private String address;

}
