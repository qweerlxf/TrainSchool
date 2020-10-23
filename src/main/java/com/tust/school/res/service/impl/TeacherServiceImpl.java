package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.config.handler.ServiceException;
import com.tust.school.res.config.handler.ServiceExceptionEnum;
import com.tust.school.res.domain.dto.course.InputScoreParam;
import com.tust.school.res.domain.dto.teacher.TeacherResult;
import com.tust.school.res.domain.dto.teacher.TeacherSaveParam;
import com.tust.school.res.domain.dto.teacher.TeacherStatusParam;
import com.tust.school.res.domain.entity.RelStuCourse;
import com.tust.school.res.domain.entity.Teacher;
import com.tust.school.res.domain.enums.BaseStatusEnum;
import com.tust.school.res.domain.enums.HasDeleteEnum;
import com.tust.school.res.mapper.RelStuCourseMapper;
import com.tust.school.res.mapper.TeacherMapper;
import com.tust.school.res.service.TeacherService;
import com.tust.school.res.utils.Md5Utils;
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
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private RelStuCourseMapper relStuCourseMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public void inputStudentScore(InputScoreParam inputScoreParam) {
        QueryWrapper<RelStuCourse> relStuCourseQueryWrapper = new QueryWrapper<>();
        relStuCourseQueryWrapper.eq("stu_id", inputScoreParam.getStudentId())
                .eq("course_id", inputScoreParam.getCourseId())
                .eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());

        RelStuCourse relStuCourse = relStuCourseMapper.selectOne(relStuCourseQueryWrapper);
        if (Objects.isNull(relStuCourse)) {
            throw new ServiceException(ServiceExceptionEnum.STUDENT_NOT_SELECT_COURSE);
        }

        relStuCourse.setScore(inputScoreParam.getScore());

        relStuCourseMapper.updateById(relStuCourse);
    }

    @Override
    public List<TeacherResult> listAllTeacher() {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<Teacher> teachers = teacherMapper.selectList(teacherQueryWrapper);
        if (CollectionUtils.isEmpty(teachers)) {
            return new ArrayList<>();
        }

        List<TeacherResult> teacherResults = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherResult teacherResult = new TeacherResult();
            BeanUtils.copyProperties(teacher, teacherResult);

            teacherResult.setStatusStr(BaseStatusEnum.getDescByStatus(teacher.getStatus()));
            teacherResults.add(teacherResult);
        }

        return teacherResults;
    }

    @Override
    public TeacherResult getTeacherResultById(Integer teacherId) {
        Teacher teacher = teacherMapper.selectById(teacherId);
        if (Objects.isNull(teacher)) {
            return new TeacherResult();
        }

        TeacherResult teacherResult = new TeacherResult();
        BeanUtils.copyProperties(teacher, teacherResult);

        return teacherResult;
    }

    @Override
    public void save(TeacherSaveParam teacherSaveParam) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherSaveParam, teacher);

        Integer userId = UserContextHelper.getUserId();
        if (Objects.isNull(teacher.getId())) {
            // 新增
            teacher.setCreateDate(new Date());
            teacher.setCreatorId(userId);
            teacher.setPassword(Md5Utils.md5Sign("123456"));
            teacher.setNo(RandomUtils.getRandomReqNo());
            teacher.setUpdateId(userId);

            teacherMapper.insert(teacher);
        } else {
            teacherMapper.updateById(teacher);
        }
    }

    @Override
    public void updateStatus(TeacherStatusParam teacherSaveParam) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherSaveParam.getId());
        teacher.setStatus(teacherSaveParam.getStatus());

        teacherMapper.updateById(teacher);
    }

    @Override
    public void delete(Integer id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setHasDelete(HasDeleteEnum.DELETED.getValue());

        teacherMapper.updateById(teacher);
    }
}
