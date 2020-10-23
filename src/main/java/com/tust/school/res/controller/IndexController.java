package com.tust.school.res.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", "index"})
    public String index() {
        return "login/login";
    }

    @RequestMapping("/index/admin")
    public String adminIndex(){
        return "index/admin";
    }

    @RequestMapping("/index/student")
    public String studentIndex(){
        return "index/student";
    }

    @RequestMapping("/index/teacher")
    public String teacherIndex(){
        return "index/teacher";
    }


}
