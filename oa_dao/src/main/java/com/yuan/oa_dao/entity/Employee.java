package com.yuan.oa_dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;



@Data
public class Employee extends Model<Employee>{
    private static final long serialVersionUID=1L;

    @TableId
    private String sn;
    private String password;
    @TableField("NAME")
    private String name;
    @TableField("department_sn")
    private String departmentSn;
    private String post;

}
