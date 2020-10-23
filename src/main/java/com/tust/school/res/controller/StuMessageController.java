package com.tust.school.res.controller;

import com.tust.school.res.domain.dto.message.StuMessageAddParam;
import com.tust.school.res.service.StuMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author luoxiaofang
 * @create 2020-05-24 16:32
 */
@Controller
@RequestMapping("/api/auth")
public class StuMessageController {

    @Resource
    private StuMessageService stuMessageService;

//    @RequestMapping("/stu/message/add")
//    public ModelAndView stuMessageAdd(StuMessageAddParam stuMessageAddParam) {
//        stuMessageService.addStuMessage(stuMessageAddParam);
//
//    }
}
