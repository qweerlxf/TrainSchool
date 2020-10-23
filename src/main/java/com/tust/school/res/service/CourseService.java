package com.tust.school.res.service;

import com.tust.school.res.domain.dto.course.CourseResult;
import com.tust.school.res.domain.dto.course.CourseSaveParam;
import com.tust.school.res.domain.dto.course.CourseStatusParam;

import java.util.List;

public interface CourseService {

    /**
     * 学生未选课程
     *
     * @return
     */
    List<CourseResult> listStudentUnselectedCourse();


    /**
     * 学生已选课程
     */
    List<CourseResult> listStudentSelectedCourse();

    /**
     * 老师任教课程列表
     *
     * @return
     */
    List<CourseResult> listTeacherCourse();


    /**
     * 获取课程列表
     *
     * @return
     */
    List<CourseResult> listAllCourse();

    /**
     * 课程详情
     *
     * @param courseId
     * @return
     */
    CourseResult getCourseResultById(Integer courseId);

    /**
     * 新增课程或修改课程
     *
     * @param courseSaveParam
     */
    void save(CourseSaveParam courseSaveParam);

    /**
     * 上下架课程
     *
     * @param courseStatusParam
     */
    void updateStatus(CourseStatusParam courseStatusParam);

    /**
     * 删除课程
     *
     * @param id
     */
    void delete(Integer id);
}
