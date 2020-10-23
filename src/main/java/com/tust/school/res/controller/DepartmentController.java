package com.tust.school.res.controller;

import com.tust.school.res.domain.entity.Department;
import com.tust.school.res.domain.result.ResultWrap;
import com.tust.school.res.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/api/auth/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/list")
    public ModelAndView listDepartment() {
        ModelAndView modelAndView = new ModelAndView("department/department_list");

        List<Department> departmentList = departmentService.listAll();
        modelAndView.addObject("departmentList", departmentList);

        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultWrap<String> departmentSave(@Validated @RequestBody Department department) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        departmentService.insert(department);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/department/list");
        return resultWrap;
    }

    @RequestMapping("/to_add")
    public ModelAndView toAdd(){
        ModelAndView modelAndView = new ModelAndView("department/department_add");
        modelAndView.addObject("department", new Department());

        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(Department department){
        departmentService.insert(department);

        return listDepartment();
    }

    @RequestMapping("/to_update/{id}")
    public ModelAndView toUpdate(@PathVariable("id") Integer id){
        Department department = departmentService.getById(id);
        ModelAndView modelAndView = new ModelAndView("department/department_update");
        modelAndView.addObject("department", department);

        return modelAndView;
    }

    @RequestMapping("/update")
    public ModelAndView update(Department department){
        departmentService.updateById(department);
        return listDepartment();
    }


    @RequestMapping("/remove/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        departmentService.deleteById(id);
        return listDepartment();
    }

}
