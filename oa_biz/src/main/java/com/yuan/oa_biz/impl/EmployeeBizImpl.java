package com.yuan.oa_biz.impl;

import com.yuan.oa_biz.EmployeeBiz;
import com.yuan.oa_dao.dao.EmployeeDao;
import com.yuan.oa_dao.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeBiz")
public class EmployeeBizImpl implements EmployeeBiz {

    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    public void add(Employee employee) {
        employee.setPassword("000000");
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    public Employee findOne(String sn) {
        Employee employee = employeeDao.selectOne(sn);
        return employee;
    }

    public List<Employee> findAll() {
        List<Employee> list = employeeDao.selectAll();
        return list;
    }
}
