package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.config.handler.ServiceException;
import com.tust.school.res.config.handler.ServiceExceptionEnum;
import com.tust.school.res.domain.dto.course.ScoreResult;
import com.tust.school.res.domain.dto.student.StudentRegParam;
import com.tust.school.res.domain.dto.student.StudentResult;
import com.tust.school.res.domain.entity.Course;
import com.tust.school.res.domain.entity.RelStuCourse;
import com.tust.school.res.domain.entity.Student;
import com.tust.school.res.domain.enums.BaseStatusEnum;
import com.tust.school.res.domain.enums.HasDeleteEnum;
import com.tust.school.res.mapper.CourseMapper;
import com.tust.school.res.mapper.RelStuCourseMapper;
import com.tust.school.res.mapper.StudentMapper;
import com.tust.school.res.service.StudentService;
import com.tust.school.res.utils.Md5Utils;
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
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private RelStuCourseMapper relStuCourseMapper;

    @Override
    public String regStudent(StudentRegParam studentRegParam) throws Exception {
        Student student = new Student();

        BeanUtils.copyProperties(studentRegParam, student);

        student.setCreateDate(new Date());
        student.setNo(RandomUtils.getRandomReqNo());
        student.setHasDelete(HasDeleteEnum.UN_DELETE.getValue());
        student.setPassword(Md5Utils.md5Sign(studentRegParam.getPassword()));
        student.setStatus(BaseStatusEnum.ONLINE.getStatus());
        student.setUpdateDate(new Date());

        studentMapper.insert(student);

        return student.getNo();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void selectCourse(Integer courseId) {
        Integer userId = UserContextHelper.getUserId();
        Course course = courseMapper.selectById(courseId);
        checkSelectCourse(userId, course);

        course.setCurrentStuNum(course.getCurrentStuNum() + 1);
        courseMapper.updateById(course);

        RelStuCourse relStuCourse = new RelStuCourse();
        relStuCourse.setStuId(userId);
        relStuCourse.setCourseId(courseId);
        relStuCourse.setHasDelete(HasDeleteEnum.UN_DELETE.getValue());
        relStuCourse.setCreateDate(new Date());

        relStuCourseMapper.insert(relStuCourse);
    }

    @Override
    public void unSelect(Integer courseId) {
        Integer userId = UserContextHelper.getUserId();
        QueryWrapper<RelStuCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", userId).eq("course_id", courseId);

        RelStuCourse relStuCourse = new RelStuCourse();
        relStuCourse.setHasDelete(HasDeleteEnum.DELETED.getValue());
        relStuCourseMapper.update(relStuCourse, queryWrapper);
    }

    @Override
    public List<ScoreResult> listStudentScore(Integer courseId) {
        Course course = courseMapper.selectById(courseId);
        if (Objects.isNull(course)) {
            return new ArrayList<>();
        }

        QueryWrapper<RelStuCourse> relStuCourseQueryWrapper = new QueryWrapper<>();
        relStuCourseQueryWrapper.eq("course_id", course.getId()).eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<RelStuCourse> relStuCourses = relStuCourseMapper.selectList(relStuCourseQueryWrapper);
        if (CollectionUtils.isEmpty(relStuCourses)) {
            return new ArrayList<>();
        }

        List<Integer> stuIdList = relStuCourses.stream().map(RelStuCourse::getStuId).collect(Collectors.toList());
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.in("id", stuIdList);
        List<Student> studentList = studentMapper.selectList(studentQueryWrapper);

        Map<Integer, String> studentNameMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(studentList)) {
            studentNameMap = studentList.stream().collect(Collectors.toMap(Student::getId, Student::getName));
        }

        List<ScoreResult> resultList = new ArrayList<>();
        for (RelStuCourse relStuCourse : relStuCourses) {
            ScoreResult scoreResult = new ScoreResult();
            scoreResult.setCourseId(courseId);
            scoreResult.setCourseName(course.getName());
            scoreResult.setStudentId(relStuCourse.getStuId());
            scoreResult.setStudentName(studentNameMap.get(relStuCourse.getStuId()));
            scoreResult.setScore(relStuCourse.getScore());

            resultList.add(scoreResult);
        }

        return resultList;
    }

    @Override
    public List<StudentResult> listAllStudent() {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<Student> students = studentMapper.selectList(studentQueryWrapper);
        if (CollectionUtils.isEmpty(students)) {
            return new ArrayList<>();
        }

        List<StudentResult> studentResults = new ArrayList<>();
        for (Student student : students) {
            StudentResult studentResult = new StudentResult();
            BeanUtils.copyProperties(student, studentResult);

            studentResult.setStatusStr(BaseStatusEnum.getDescByStatus(student.getStatus()));
            studentResults.add(studentResult);
        }

        return studentResults;
    }

    public StudentResult getStudentResultById(Integer studentId) {
        Student student = studentMapper.selectById(studentId);
        if (Objects.isNull(student)) {
            return new StudentResult();
        }

        StudentResult studentResult = new StudentResult();
        BeanUtils.copyProperties(student, studentResult);

        return studentResult;
    }

    private void checkSelectCourse(Integer userId, Course course) {
        if (Objects.isNull(course)) {
            throw new ServiceException(ServiceExceptionEnum.COURSE_NOT_EXISTS);
        }

        if (course.getCurrentStuNum() + 1 > course.getMaxStuNum()) {
            throw new ServiceException(ServiceExceptionEnum.COURSE_MAX_STUDENT);
        }

        QueryWrapper<RelStuCourse> relStuCourseQueryWrapper = new QueryWrapper<>();
        relStuCourseQueryWrapper.eq("stu_id", userId).eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<RelStuCourse> relStuCourses = relStuCourseMapper.selectList(relStuCourseQueryWrapper);
        if (CollectionUtils.isEmpty(relStuCourses)) {
            return;
        }

        Map<Integer, RelStuCourse> relStuCourseMap = relStuCourses.stream().collect(Collectors.toMap(RelStuCourse::getCourseId, r -> r));
        if (Objects.nonNull(relStuCourseMap.get(course.getId()))) {
            throw new ServiceException(ServiceExceptionEnum.ALREADY_SELECTED_COURSE);
        }

        List<Integer> courseIds = relStuCourses.stream().map(RelStuCourse::getCourseId).collect(Collectors.toList());
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.in("id", courseIds).eq("has_delete", HasDeleteEnum.UN_DELETE.getValue());
        List<Course> courses = courseMapper.selectList(courseQueryWrapper);
        if (CollectionUtils.isEmpty(courses)) {
            return;
        }

        for (Course tempCourse : courses) {
            if (!Objects.equals(course.getCourseDay(), tempCourse.getCourseDay())) {
                continue;
            }

            boolean hasConflict = false;
            if (tempCourse.getCourseStart().compareTo(course.getCourseStart()) >= 0
                    && tempCourse.getCourseStart().compareTo(course.getCourseEnd()) <= 0) {
                hasConflict = true;
            } else if (tempCourse.getCourseEnd().compareTo(course.getCourseStart()) >= 0
                    && tempCourse.getCourseEnd().compareTo(course.getCourseEnd()) <= 0) {
                hasConflict = true;
            } else if (course.getCourseStart().compareTo(tempCourse.getCourseStart()) >= 0
                    && course.getCourseStart().compareTo(tempCourse.getCourseEnd()) <= 0) {
                hasConflict = true;
            } else if (course.getCourseEnd().compareTo(tempCourse.getCourseStart()) >= 0
                    && course.getCourseEnd().compareTo(tempCourse.getCourseEnd()) <= 0) {
                hasConflict = true;
            }

            if (hasConflict) {
                throw new ServiceException(ServiceExceptionEnum.COURSE_CONFLICT);
            }
        }
    }

}
