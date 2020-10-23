package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.domain.dto.classes.ClassResult;
import com.tust.school.res.domain.entity.Class;
import com.tust.school.res.domain.enums.BaseStatusEnum;
import com.tust.school.res.domain.enums.HasDeleteEnum;
import com.tust.school.res.mapper.ClassMapper;
import com.tust.school.res.service.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Resource
    private ClassMapper classMapper;

    @Override
    public List<ClassResult> listAllClass() {
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", BaseStatusEnum.ONLINE.getStatus());
        queryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());

        List<Class> classes = classMapper.selectList(queryWrapper);
        List<ClassResult> classResults = new ArrayList<>();
        if (CollectionUtils.isEmpty(classes)) {
            return classResults;
        }

        for (Class cl : classes) {
            ClassResult classResult = new ClassResult();
            BeanUtils.copyProperties(cl, classResult);
            classResults.add(classResult);
        }
        return classResults;
    }

}
