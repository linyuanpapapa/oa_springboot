package com.yuan.oa_biz.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.oa_biz.DepartmentBiz;
import com.yuan.oa_dao.dao.DepartmentDao;
import com.yuan.oa_dao.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentBiz")
public class DepartmentBizImpl extends ServiceImpl<DepartmentDao,Department> implements DepartmentBiz {
}
