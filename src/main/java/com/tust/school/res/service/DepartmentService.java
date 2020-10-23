package com.tust.school.res.service;

import com.tust.school.res.domain.entity.Department;

import java.util.List;

public interface DepartmentService {

    void insert(Department department);

    void updateById(Department department);

    void deleteById(Integer id);

    Department getById(Integer id);

    List<Department> listAll();
}
