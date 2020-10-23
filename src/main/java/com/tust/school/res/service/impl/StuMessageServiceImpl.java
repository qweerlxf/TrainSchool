package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.domain.dto.message.StuMessageAddParam;
import com.tust.school.res.domain.dto.message.StuMessageParam;
import com.tust.school.res.domain.dto.message.StuMessageResult;
import com.tust.school.res.domain.entity.Course;
import com.tust.school.res.domain.entity.StuMessage;
import com.tust.school.res.domain.entity.Student;
import com.tust.school.res.domain.enums.HasDeleteEnum;
import com.tust.school.res.mapper.CourseMapper;
import com.tust.school.res.mapper.StuMessageMapper;
import com.tust.school.res.mapper.StudentMapper;
import com.tust.school.res.service.StuMessageService;
import com.tust.school.res.utils.UserContextHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luoxiaofang
 * @create 2020-05-24 16:44
 */
@Service
public class StuMessageServiceImpl implements StuMessageService {

    @Resource
    private StuMessageMapper stuMessageMapper;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Integer addStuMessage(StuMessageAddParam stuMessageAddParam) {
        StuMessage stuMessage = new StuMessage();
        stuMessage.setContent(stuMessageAddParam.getContent());
        stuMessage.setCourseId(stuMessageAddParam.getCourseId());
        stuMessage.setStuId(UserContextHelper.getUserId());
        stuMessage.setCreateDate(new Date());
        stuMessage.setCreatorId(UserContextHelper.getUserId());

        stuMessageMapper.insert(stuMessage);

        return stuMessage.getId();
    }

    @Override
    public List<StuMessageResult> listStuMessage(StuMessageParam stuMessageParam) {
        QueryWrapper<StuMessage> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(stuMessageParam.getId())) {
            queryWrapper.lambda().eq(StuMessage::getId, stuMessageParam.getId());
        }
        if (Objects.nonNull(stuMessageParam.getCourseId())) {
            queryWrapper.lambda().eq(StuMessage::getCourseId, stuMessageParam.getCourseId());
        }
        if (Objects.nonNull(stuMessageParam.getStuId())) {
            queryWrapper.lambda().eq(StuMessage::getStuId, stuMessageParam.getStuId());
        }

        queryWrapper.lambda().eq(StuMessage::getHasDelete, HasDeleteEnum.UN_DELETE.getValue());

        List<StuMessage> stuMessages = stuMessageMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(stuMessages)) {
            return new ArrayList<>();
        }

        List<Integer> courseIds = stuMessages.stream().map(StuMessage::getCourseId).collect(Collectors.toList());
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.lambda().in(Course::getId, courseIds);
        List<Course> courses = courseMapper.selectList(courseQueryWrapper);
        Map<Integer, String> courseNameMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(courses)) {
            courseNameMap = courses.stream().collect(Collectors.toMap(Course::getId, Course::getName));
        }

        List<Integer> stuIds = stuMessages.stream().map(StuMessage::getStuId).collect(Collectors.toList());
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.lambda().in(Student::getId, stuIds);
        List<Student> students = studentMapper.selectList(studentQueryWrapper);
        Map<Integer, String> stuNameMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(students)) {
            stuNameMap = students.stream().collect(Collectors.toMap(Student::getId, Student::getName));
        }

        List<StuMessageResult> resultList = new ArrayList<>();
        for (StuMessage stuMessage : stuMessages) {
            StuMessageResult stuMessageResult = new StuMessageResult();
            BeanUtils.copyProperties(stuMessage, stuMessageResult);

            stuMessageResult.setCourseName(courseNameMap.get(stuMessage.getCourseId()));
            stuMessageResult.setStuName(stuNameMap.get(stuMessage.getStuId()));
            resultList.add(stuMessageResult);
        }

        return resultList;
    }
}
