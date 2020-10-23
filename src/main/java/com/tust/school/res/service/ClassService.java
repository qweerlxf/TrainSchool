package com.tust.school.res.service;

import com.tust.school.res.domain.dto.classes.ClassResult;

import java.util.List;

public interface ClassService {

    /**
     * 班级列表
     *
     * @return
     */
    List<ClassResult> listAllClass();

}
