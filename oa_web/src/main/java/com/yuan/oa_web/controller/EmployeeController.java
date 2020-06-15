package com.yuan.oa_web.controller;

import com.yuan.oa_biz.*;
import com.yuan.oa_dao.entity.*;
import com.yuan.oa_dao.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private DepartmentBiz departmentBiz;

    @Autowired
    private EmployeeBiz employeeBiz;

    @GetMapping("/list")
    public String list(Map<String,Object> map){
        map.put("list",employeeBiz.findAll());
        return "employee_list";
    }

    @GetMapping("/to_add")
    public String to_add(Map<String,Object> map){
        map.put("employee",new Employee());
        return "employee_add";
    }

    /**
     * 此处SpringMVC会自动把表单里的数据封装进department对象
     * @param employee
     * @return
     */
    @PostMapping("/add")
    public String add(Employee employee){
        employeeBiz.add(employee);
        return "redirect:list";
    }

    @GetMapping(value = "/to_update",params = "sn")
    public String to_update(String sn,Map<String,Object> map){
        map.put("employee",employeeBiz.findOne(sn));
        map.put("dlist",departmentBiz.findAll());
        map.put("plist", Contant.getPosts());
        return "employee_update";
    }

    @PutMapping("/update")
    public String update(Employee employee){
        employeeBiz.edit(employee);
        return "redirect:list";
    }

    @DeleteMapping(value = "/remove",params = "sn")
    public String remove(String sn){
        employeeBiz.remove(sn);
        return "redirect:list";
    }
}
