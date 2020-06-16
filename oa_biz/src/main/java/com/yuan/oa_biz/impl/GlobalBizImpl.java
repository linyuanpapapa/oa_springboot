package com.yuan.oa_biz.impl;



import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yuan.oa_biz.GlobalBiz;
import com.yuan.oa_dao.dao.EmployeeDao;
import com.yuan.oa_dao.dto.EmployeeWithDepartment;
import com.yuan.oa_dao.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("globalBiz")
public class GlobalBizImpl implements GlobalBiz {
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeWithDepartment login(String sn, String password) {
        EmployeeWithDepartment employee = employeeDao.getOne(sn);
        if(employee!=null&&employee.getPassword().equals(password)){
            return employee;
        }
        return null;
    }

    public void changePassword(Employee employee) {
        employeeDao.update(employee,new UpdateWrapper<>());
    }
}
