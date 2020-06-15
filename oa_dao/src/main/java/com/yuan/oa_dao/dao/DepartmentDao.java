package com.yuan.oa_dao.dao;

import com.yuan.oa_dao.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDao")
public interface DepartmentDao {
    void insert(Department department);
    void update(Department department);
    void delete(String sn);
    Department selectOne(String sn);
    List<Department> selectAll();
}
