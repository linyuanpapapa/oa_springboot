package com.yuan.oa_web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yuan.oa_biz.*;
import com.yuan.oa_dao.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {


    @Autowired
    DepartmentBiz departmentBiz;

    @GetMapping("/list")
    public String list(Map<String,Object> map){
        map.put("list",departmentBiz.list());
        return "department_list";
    }

    @GetMapping("/to_add")
    public String to_add(Map<String,Object> map){
        map.put("department",new Department());
        return "department_add";
    }

    /**
     * 此处SpringMVC会自动把表单里的数据封装进department对象
     * @param department
     * @return
     */
    @PostMapping("/add")
    public String add(Department department){
        departmentBiz.save(department);
        return "redirect:list";
    }

    @GetMapping(value = "/to_update",params = "sn")
    public String to_update(String sn,Map<String,Object> map){
        map.put("department",departmentBiz.getOne(new QueryWrapper<Department>().eq("sn",sn)));
        return "department_update";
    }

    @PutMapping("/update")
    public String update(Department department){
        departmentBiz.update(department,new UpdateWrapper<>());
        return "redirect:list";
    }

    @DeleteMapping(value = "/remove",params = "sn")
    public String remove(String sn){
        departmentBiz.removeById(sn);
        return "redirect:list";
    }
}
