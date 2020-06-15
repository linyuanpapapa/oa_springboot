package com.yuan.oa_biz.impl;


import com.yuan.oa_biz.DepartmentBiz;
import com.yuan.oa_dao.dao.DepartmentDao;
import com.yuan.oa_dao.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentBiz")
public class DepartmentBizImpl implements DepartmentBiz {

    @Qualifier("departmentDao")
    @Autowired
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void remove(String sn) {
        departmentDao.delete(sn);
    }

    public Department findOne(String sn) {
        Department department=departmentDao.selectOne(sn);
        return department;
    }

    public List<Department> findAll() {
        List<Department> list=departmentDao.selectAll();
        return list;
    }
}
