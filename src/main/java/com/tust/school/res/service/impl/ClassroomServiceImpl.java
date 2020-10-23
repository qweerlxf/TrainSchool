package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.domain.dto.classroom.ClassroomResult;
import com.tust.school.res.domain.dto.classroom.ClassroomSaveParam;
import com.tust.school.res.domain.dto.classroom.ClassroomStatusParam;
import com.tust.school.res.domain.entity.Classroom;
import com.tust.school.res.domain.enums.BaseStatusEnum;
import com.tust.school.res.domain.enums.HasDeleteEnum;
import com.tust.school.res.mapper.ClassroomMapper;
import com.tust.school.res.service.ClassroomService;
import com.tust.school.res.utils.RandomUtils;
import com.tust.school.res.utils.UserContextHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Resource
    private ClassroomMapper classroomMapper;

    @Override
    public List<ClassroomResult> listAllClassroom() {
        QueryWrapper<Classroom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<Classroom> classrooms = classroomMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(classrooms)) {
            return new ArrayList<>();
        }

        List<ClassroomResult> classroomResults = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            ClassroomResult classroomResult = new ClassroomResult();
            BeanUtils.copyProperties(classroom, classroomResult);

            classroomResult.setHasVideoStr(Objects.equals(0, classroom.getHasVideo()) ? "否" : "是");
            classroomResult.setStatusStr(BaseStatusEnum.getDescByStatus(classroom.getStatus()));
            classroomResults.add(classroomResult);
        }

        return classroomResults;
    }

    @Override
    public ClassroomResult getClassroomById(Integer classroomId) {
        Classroom classroom = classroomMapper.selectById(classroomId);
        if (Objects.isNull(classroom)) {
            return new ClassroomResult();
        }

        ClassroomResult classroomResult = new ClassroomResult();
        BeanUtils.copyProperties(classroom, classroomResult);

        classroomResult.setHasVideoStr(Objects.equals(0, classroom.getHasVideo()) ? "否" : "是");
        classroomResult.setStatusStr(BaseStatusEnum.getDescByStatus(classroom.getStatus()));

        return classroomResult;
    }

    @Override
    public void save(ClassroomSaveParam classroomSaveParam) {
        Classroom classroom = new Classroom();
        BeanUtils.copyProperties(classroomSaveParam, classroom);

        Integer userId = UserContextHelper.getUserId();
        if (Objects.isNull(classroom.getId())) {
            classroom.setUpdateId(userId);
            classroom.setCreateDate(new Date());
            classroom.setCreatorId(userId);
            classroom.setNo(RandomUtils.getRandomReqNo());

            classroomMapper.insert(classroom);
        } else {
            classroom.setUpdateId(userId);
            classroomMapper.updateById(classroom);
        }
    }

    @Override
    public void updateStatus(ClassroomStatusParam classroomStatusParam) {
        Classroom classroom = new Classroom();
        classroom.setId(classroomStatusParam.getId());
        classroom.setStatus(classroomStatusParam.getStatus());

        classroomMapper.updateById(classroom);
    }

    @Override
    public void delete(Integer id) {
        Classroom classroom = new Classroom();
        classroom.setId(id);
        classroom.setHasDelete(HasDeleteEnum.DELETED.getValue());

        classroomMapper.updateById(classroom);
    }
}
