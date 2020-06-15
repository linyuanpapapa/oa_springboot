package com.yuan.oa_biz;



import com.yuan.oa_dao.entity.Department;

import java.util.List;

public interface DepartmentBiz {
    void add(Department department);
    void edit(Department department);
    void remove(String sn);
    Department findOne(String sn);
    List<Department> findAll();
}
