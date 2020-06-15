package com.yuan.oa_biz;

import com.yuan.oa_dao.entity.Employee;

public interface GlobalBiz {
    Employee login(String sn, String password);
    void changePassword(Employee employee);
}
