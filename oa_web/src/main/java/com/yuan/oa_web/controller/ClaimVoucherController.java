package com.yuan.oa_web.controller;


import com.yuan.oa_biz.*;
import com.yuan.oa_dao.entity.*;
import com.yuan.oa_dao.global.Contant;
import com.yuan.oa_web.dto.ClaimVoucherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {

    @Autowired
    private ClaimVoucherBiz claimVoucherBiz;

    @GetMapping("/to_add")
    public String to_add(Map<String,Object> map){
        map.put("items", Contant.getItems());
        map.put("info",new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    @PostMapping("/add")
    public String add(HttpSession session,ClaimVoucherInfo info){
        Employee employee=(Employee)session.getAttribute("Employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherBiz.save(info.getClaimVoucher(),info.getItems());
        return "redirect:deal";
    }

    @GetMapping("/detail")
    public String detail(int id,Map<String,Object> map){
        map.put("claimVoucher",claimVoucherBiz.get(id));
        map.put("items",claimVoucherBiz.getItems(id));
        map.put("records",claimVoucherBiz.getRecords(id));
        return "claim_voucher_detail";
    }

    @GetMapping("/self")
    public String self(HttpSession session,Map<String,Object> map){
        Employee employee=(Employee)session.getAttribute("Employee");
        map.put("list",claimVoucherBiz.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }

    @PutMapping("/deal")
    public String deal(HttpSession session,Map<String,Object> map){
        Employee employee=(Employee)session.getAttribute("Employee");
        map.put("list",claimVoucherBiz.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }

    @GetMapping("/to_update")
    public String to_update(int id,Map<String,Object> map){
        map.put("items", Contant.getItems());
        ClaimVoucherInfo info=new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherBiz.get(id));
        info.setItems(claimVoucherBiz.getItems(id));
        map.put("info",info);
        return "claim_voucher_update";
    }

    @PutMapping("/update")
    public String update(HttpSession session,ClaimVoucherInfo info){
        Employee employee=(Employee)session.getAttribute("Employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherBiz.update(info.getClaimVoucher(),info.getItems());
        return "redirect:deal";
    }

    @PostMapping("/submit")
    public String submit(int id){
        claimVoucherBiz.submit(id);
        return "redirect:deal";
    }

    @GetMapping("/to_check")
    public String to_check(int id,Map<String,Object> map){
        map.put("claimVoucher",claimVoucherBiz.get(id));
        map.put("items",claimVoucherBiz.getItems(id));
        map.put("records",claimVoucherBiz.getRecords(id));
        DealRecord dealRecord=new DealRecord();
        dealRecord.setClaimVoucherId(id);
        map.put("record",dealRecord);
        return "claim_voucher_check";
    }

    @PutMapping("/check")
    public String check(HttpSession session, DealRecord dealRecord){
        Employee employee=(Employee)session.getAttribute("Employee");
        dealRecord.setDealSn(employee.getSn());
        claimVoucherBiz.deal(dealRecord);
        return "redirect:deal";
    }
}
