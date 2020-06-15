package com.yuan.oa_biz;


import com.yuan.oa_dao.entity.Employee;

import java.util.List;

public interface EmployeeBiz {
    void add(Employee employee);
    void edit(Employee employee);
    void remove(String sn);
    Employee findOne(String sn);
    List<Employee> findAll();
}
