package com.yuan.oa_biz;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.oa_dao.dto.EmployeeWithDepartment;
import com.yuan.oa_dao.entity.Employee;

import java.util.List;


public interface EmployeeBiz extends IService<Employee> {
    EmployeeWithDepartment getOne(String sn);
    List<EmployeeWithDepartment> selectAll();
}
