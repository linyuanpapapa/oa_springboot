package com.yuan.oa_biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.oa_biz.EmployeeBiz;
import com.yuan.oa_dao.dao.EmployeeDao;
import com.yuan.oa_dao.dto.EmployeeWithDepartment;
import com.yuan.oa_dao.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("employeeBiz")
public class EmployeeBizImpl extends ServiceImpl<EmployeeDao,Employee> implements EmployeeBiz {
    @Autowired
    EmployeeDao employeeDao;

    @Override
    public EmployeeWithDepartment getOne(String sn) {
        return employeeDao.getOne(sn);
    }

    @Override
    public List<EmployeeWithDepartment> selectAll() {
        return employeeDao.selectAll();
    }


}
