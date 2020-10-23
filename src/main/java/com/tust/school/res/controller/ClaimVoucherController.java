package com.tust.school.res.controller;

import com.tust.school.res.consts.Contant;
import com.tust.school.res.domain.dto.ClaimVoucherInfo;
import com.tust.school.res.domain.entity.DealRecord;
import com.tust.school.res.domain.entity.Teacher;
import com.tust.school.res.service.ClaimVoucherService;
import com.tust.school.res.utils.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller("claimVoucherController")
@RequestMapping("/api/auth")
public class ClaimVoucherController {
    @Autowired
    private ClaimVoucherService claimVoucherService;

    @RequestMapping("/claim_voucher/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("items", Contant.getItems());
        map.put("info",new ClaimVoucherInfo());
        return "claim_voucher_add";
    }
    @RequestMapping("/claim_voucher/add")
    public String add(HttpSession session, ClaimVoucherInfo info){
        info.getClaimVoucher().setCreateSn(UserContextHelper.getNo());
        claimVoucherService.save(info.getClaimVoucher(),info.getItems());
        return "redirect:deal";
    }
    @RequestMapping("/claim_voucher/detail")
    public String detail(int id, Map<String,Object> map){
        map.put("claimVoucher",claimVoucherService.get(id));
        map.put("items",claimVoucherService.getItems(id));
        map.put("records",claimVoucherService.getRecords(id));
        return "claim_voucher_detail";
    }
    @RequestMapping("/claim_voucher/self")
    public String self(HttpSession session, Map<String,Object> map){
        map.put("list",claimVoucherService.getForSelf(UserContextHelper.getUserId()));
        return "claim_voucher_self";
    }
    @RequestMapping("/claim_voucher/deal")
    public String deal(Map<String,Object> map){
        map.put("list",claimVoucherService.getForDeal(UserContextHelper.getUserId()));
        return "claim_voucher_deal";
    }

    @RequestMapping("/claim_voucher/to_update")
    public String toUpdate(int id,Map<String,Object> map){
        map.put("items", Contant.getItems());
        ClaimVoucherInfo info =new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherService.get(id));
        info.setItems(claimVoucherService.getItems(id));
        map.put("info",info);
        return "claim_voucher_update";
    }
    @RequestMapping("/claim_voucher/update")
    public String update(HttpSession session, ClaimVoucherInfo info){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        info.getClaimVoucher().setCreateSn(teacher.getNo());
        claimVoucherService.update(info.getClaimVoucher(),info.getItems());
        return "redirect:deal";
    }
    @RequestMapping("/claim_voucher/submit")
    public String submit(int id){
        claimVoucherService.submit(id);
        return "redirect:deal";
    }

    @RequestMapping("/claim_voucher/to_check")
    public String toCheck(int id,Map<String,Object> map){
        map.put("claimVoucher",claimVoucherService.get(id));
        map.put("items",claimVoucherService.getItems(id));
        map.put("records",claimVoucherService.getRecords(id));
        DealRecord dealRecord =new DealRecord();
        dealRecord.setClaimVoucherId(id);
        map.put("record",dealRecord);
        return "claim_voucher_check";
    }
    @RequestMapping("/claim_voucher/check")
    public String check(HttpSession session, DealRecord dealRecord){
        dealRecord.setDealSn(UserContextHelper.getUserId());
        claimVoucherService.deal(dealRecord);
        return "redirect:deal";
    }
}
