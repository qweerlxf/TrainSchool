package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.domain.entity.Department;
import com.tust.school.res.mapper.DepartmentMapper;
import com.tust.school.res.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentBiz")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public void insert(Department department) {
        departmentMapper.insert(department);
    }

    public void updateById(Department department) {
        departmentMapper.updateById(department);
    }

    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }

    public Department getById(Integer id) {
        return departmentMapper.selectById(id);
    }

    public List<Department> listAll() {
        Wrapper<Department> wrapper = new QueryWrapper<>();
        return departmentMapper.selectList(wrapper);
    }
}
