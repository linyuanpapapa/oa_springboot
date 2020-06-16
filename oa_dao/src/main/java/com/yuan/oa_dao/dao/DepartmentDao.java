package com.yuan.oa_dao.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.oa_dao.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDao")
public interface DepartmentDao extends BaseMapper<Department> {
}
