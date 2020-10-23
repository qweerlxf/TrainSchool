package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.domain.dto.course.CourseResult;
import com.tust.school.res.domain.dto.course.CourseSaveParam;
import com.tust.school.res.domain.dto.course.CourseStatusParam;
import com.tust.school.res.domain.entity.*;
import com.tust.school.res.domain.enums.BaseStatusEnum;
import com.tust.school.res.domain.enums.HasDeleteEnum;
import com.tust.school.res.mapper.*;
import com.tust.school.res.service.CourseService;
import com.tust.school.res.utils.RandomUtils;
import com.tust.school.res.utils.UserContextHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private RelStuCourseMapper relStuCourseMapper;

    @Resource
    private RelTeacherCourseMapper relTeacherCourseMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private RelCourseClassroomMapper relCourseClassroomMapper;

    @Resource
    private ClassroomMapper classroomMapper;

    @Override
    public List<CourseResult> listStudentUnselectedCourse() {
        Integer stuId = UserContextHelper.getUserId();

        List<Integer> selectedCourseIds = stuSelectedCourseIds(stuId);

        List<CourseResult> courseResults = new ArrayList<>();

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", BaseStatusEnum.ONLINE.getStatus());
        queryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        if (!CollectionUtils.isEmpty(selectedCourseIds)) {
            queryWrapper.notIn("id", selectedCourseIds);
        }

        List<Course> courses = courseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(courses)) {
            return courseResults;
        }

        for (Course course : courses) {
            CourseResult courseResult = new CourseResult();
            BeanUtils.copyProperties(course, courseResult);

            courseResults.add(courseResult);
        }

        return courseResults;
    }

    @Override
    public List<CourseResult> listStudentSelectedCourse() {
        Integer stuId = UserContextHelper.getUserId();
        List<Integer> selectedCourseIds = stuSelectedCourseIds(stuId);
        if (CollectionUtils.isEmpty(selectedCourseIds)) {
            return new ArrayList<>();
        }

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", selectedCourseIds);
        List<Course> courses = courseMapper.selectList(queryWrapper);
        List<CourseResult> resultList = new ArrayList<>();
        for (Course course : courses) {
            CourseResult courseResult = new CourseResult();
            BeanUtils.copyProperties(course, courseResult);

            resultList.add(courseResult);
        }

        return resultList;
    }

    @Override
    public List<CourseResult> listTeacherCourse() {
        Integer userId = UserContextHelper.getUserId();

        QueryWrapper<RelTeacherCourse> relTeacherCourseQueryWrapper = new QueryWrapper<>();
        relTeacherCourseQueryWrapper.eq("teacher_id", userId)
                .eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<RelTeacherCourse> relTeacherCourses = relTeacherCourseMapper.selectList(relTeacherCourseQueryWrapper);
        if (CollectionUtils.isEmpty(relTeacherCourses)) {
            return new ArrayList<>();
        }

        List<Integer> courseIds = relTeacherCourses.stream().map(RelTeacherCourse::getCourseId).collect(Collectors.toList());
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.in("id", courseIds).eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<Course> courses = courseMapper.selectList(courseQueryWrapper);
        if (CollectionUtils.isEmpty(courses)) {
            return new ArrayList<>();
        }

        List<CourseResult> resultList = new ArrayList<>();
        for (Course course : courses) {
            CourseResult courseResult = new CourseResult();
            BeanUtils.copyProperties(course, courseResult);

            resultList.add(courseResult);
        }

        return resultList;
    }

    @Override
    public List<CourseResult> listAllCourse() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<Course> courses = courseMapper.selectList(courseQueryWrapper);
        if (CollectionUtils.isEmpty(courses)) {
            return new ArrayList<>();
        }

        List<CourseResult> resultList = new ArrayList<>();
        for (Course course : courses) {
            CourseResult courseResult = new CourseResult();
            BeanUtils.copyProperties(course, courseResult);

            courseResult.setStatusStr(BaseStatusEnum.getDescByStatus(course.getStatus()));

            QueryWrapper<RelTeacherCourse> relTeacherCourseQueryWrapper = new QueryWrapper<>();
            relTeacherCourseQueryWrapper.eq("course_id", course.getId())
                    .eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
            RelTeacherCourse relTeacherCourse = relTeacherCourseMapper.selectOne(relTeacherCourseQueryWrapper);
            if (Objects.nonNull(relTeacherCourse)) {
                Teacher teacher = teacherMapper.selectById(relTeacherCourse.getTeacherId());
                if (Objects.nonNull(teacher)) {
                    courseResult.setTeacherId(teacher.getId());
                    courseResult.setTeacherName(teacher.getName());
                }
            }

            QueryWrapper<RelCourseClassroom> relCourseClassroomQueryWrapper = new QueryWrapper<>();
            relCourseClassroomQueryWrapper.eq("course_id", course.getId())
                    .eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
            RelCourseClassroom relCourseClassroom = relCourseClassroomMapper.selectOne(relCourseClassroomQueryWrapper);
            if (Objects.nonNull(relCourseClassroom)) {
                Classroom classroom = classroomMapper.selectById(relCourseClassroom.getClassroomId());
                if (Objects.nonNull(classroom)) {
                    courseResult.setClassroomId(classroom.getId());
                    courseResult.setClassroomName(classroom.getName());
                }
            }

            resultList.add(courseResult);
        }

        return resultList;
    }

    @Override
    public CourseResult getCourseResultById(Integer courseId) {
        Course course = courseMapper.selectById(courseId);
        if (Objects.isNull(course)) {
            return new CourseResult();
        }

        CourseResult courseResult = new CourseResult();
        BeanUtils.copyProperties(course, courseResult);

        courseResult.setStatusStr(BaseStatusEnum.getDescByStatus(course.getStatus()));

        QueryWrapper<RelTeacherCourse> relTeacherCourseQueryWrapper = new QueryWrapper<>();
        relTeacherCourseQueryWrapper.eq("course_id", course.getId())
                .eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        RelTeacherCourse relTeacherCourse = relTeacherCourseMapper.selectOne(relTeacherCourseQueryWrapper);

        if (Objects.isNull(relTeacherCourse)) {
            return courseResult;
        }

        Teacher teacher = teacherMapper.selectById(relTeacherCourse.getTeacherId());
        if (Objects.isNull(teacher)) {
            return courseResult;
        }

        courseResult.setTeacherName(teacher.getName());
        courseResult.setTeacherId(teacher.getId());

        return courseResult;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(CourseSaveParam courseSaveParam) {
        Course course = new Course();
        BeanUtils.copyProperties(courseSaveParam, course);

        Integer userId = UserContextHelper.getUserId();
        if (Objects.isNull(course.getId())) {
            course.setCreateDate(new Date());
            course.setCreatorId(userId);
            course.setNo(RandomUtils.getRandomReqNo());
            course.setUpdateId(userId);

            courseMapper.insert(course);
            courseSaveParam.setId(course.getId());
        } else {
            course.setUpdateId(userId);
            courseMapper.updateById(course);
        }

        insertRelTeacherCourse(courseSaveParam, userId);
        insertRelCourseClassRoom(courseSaveParam, userId);
    }

    private void insertRelCourseClassRoom(CourseSaveParam courseSaveParam, Integer userId) {
        RelCourseClassroom relCourseClassroom = new RelCourseClassroom();
        relCourseClassroom.setClassroomId(courseSaveParam.getClassroomId());
        relCourseClassroom.setCourseId(courseSaveParam.getId());
        relCourseClassroom.setCreateDate(new Date());
        relCourseClassroom.setCreatorId(userId);
        relCourseClassroom.setUpdateId(userId);

        RelCourseClassroom updateRecord = new RelCourseClassroom();
        updateRecord.setId(courseSaveParam.getId());
        updateRecord.setHasDelete(HasDeleteEnum.DELETED.getValue());
        QueryWrapper<RelCourseClassroom> relCourseClassroomQueryWrapper = new QueryWrapper<>();
        relCourseClassroomQueryWrapper.eq("course_id", courseSaveParam.getId())
                .eq("classroom_id", courseSaveParam.getClassroomId())
                .eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        relCourseClassroomMapper.update(updateRecord, relCourseClassroomQueryWrapper);

        relCourseClassroomMapper.insert(relCourseClassroom);
    }

    @Override
    public void updateStatus(CourseStatusParam courseStatusParam) {
        Course course = new Course();
        course.setId(courseStatusParam.getId());
        course.setStatus(courseStatusParam.getStatus());

        courseMapper.updateById(course);
    }

    @Override
    public void delete(Integer id) {
        Course course = new Course();
        course.setHasDelete(HasDeleteEnum.DELETED.getValue());
        course.setId(id);

        courseMapper.updateById(course);
    }

    private void insertRelTeacherCourse(CourseSaveParam courseSaveParam, Integer userId) {
        QueryWrapper<RelTeacherCourse> relTeacherCourseQueryWrapper = new QueryWrapper<>();
        relTeacherCourseQueryWrapper.eq("course_id", courseSaveParam.getId())
                .eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        RelTeacherCourse relTeacherCourse = new RelTeacherCourse();
        relTeacherCourse.setHasDelete(HasDeleteEnum.DELETED.getValue());
        relTeacherCourseMapper.update(relTeacherCourse, relTeacherCourseQueryWrapper);

        RelTeacherCourse insertRecord = new RelTeacherCourse();
        insertRecord.setCourseId(courseSaveParam.getId());
        insertRecord.setTeacherId(courseSaveParam.getTeacherId());
        insertRecord.setCreatorId(userId);
        insertRecord.setUpdateId(userId);
        insertRecord.setCreateDate(new Date());
        relTeacherCourseMapper.insert(insertRecord);
    }

    private List<Integer> stuSelectedCourseIds(Integer stuId) {
        QueryWrapper<RelStuCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", stuId);
        queryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());

        List<RelStuCourse> relStuCourses = relStuCourseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(relStuCourses)) {
            return new ArrayList<>();
        }

        return relStuCourses.stream().map(RelStuCourse::getCourseId).collect(Collectors.toList());
    }
}
