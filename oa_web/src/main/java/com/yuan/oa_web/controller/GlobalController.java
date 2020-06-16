package com.yuan.oa_web.controller;

import com.yuan.oa_biz.*;
import com.yuan.oa_dao.dto.EmployeeWithDepartment;
import com.yuan.oa_dao.entity.*;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api()
@RestController("globalController")
public class GlobalController {
    @Autowired
    private GlobalBiz globalBiz;

    @GetMapping("/to_login")
    //@ResponseBody
    public String toLogin(){
        return "login";
    }


    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    public String login(HttpServletRequest request,@RequestParam String sn,@RequestParam String password){
        EmployeeWithDepartment employee = globalBiz.login(sn,password);
        if(employee==null){
            return "redirect:to_login";
        }
        HttpSession session=request.getSession();
        session.setAttribute("Employee",employee);
        return "redirect:self";
    }

    @GetMapping("/self")
    public String self(){
        return "self";
    }

    @GetMapping("/quit")
    public String logout(HttpSession session){
        session.setAttribute("Employee",null);
        return "redirect:to_login";
    }

    @GetMapping("/to_change_password")
    public String toChangePassword() {
        return "change_password";
    }

    @PutMapping("/change_password")
    public String changePassword(HttpSession session,@RequestParam String old,@RequestParam String new1,@RequestParam String new2){
        EmployeeWithDepartment ewd = (EmployeeWithDepartment) session.getAttribute("Employee");
        Employee employee=new Employee();
        BeanUtils.copyProperties(ewd,employee);
        if(employee.getPassword().equals(old)){
            if(new1.equals(new2)){
                employee.setPassword(new1);
                globalBiz.changePassword(employee);
                return "redirect:self";
            }
        }
        return "redirect:to_change_password";
    }
}
