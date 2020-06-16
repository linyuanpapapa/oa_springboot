package com.yuan.oa_dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.oa_dao.dto.EmployeeWithDepartment;
import com.yuan.oa_dao.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public interface EmployeeDao extends BaseMapper<Employee> {
    EmployeeWithDepartment getOne(String sn);
    List<EmployeeWithDepartment> selectAll();
    List<EmployeeWithDepartment> selectByDepartmentAndPost(@Param("dsn") String dsn, @Param("post") String post);
}
