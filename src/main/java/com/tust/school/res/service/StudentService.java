package com.tust.school.res.service;

import com.tust.school.res.domain.dto.course.ScoreResult;
import com.tust.school.res.domain.dto.student.StudentRegParam;
import com.tust.school.res.domain.dto.student.StudentResult;

import java.util.List;

public interface StudentService {

    /**
     * 学生注册
     *
     * @param studentRegParam
     */
    String regStudent(StudentRegParam studentRegParam) throws Exception;

    /**
     * 学生选课
     *
     * @param courseId
     */
    void selectCourse(Integer courseId);

    /**
     * 退选课程
     *
     * @param courseId
     */
    void unSelect(Integer courseId);

    /**
     * 根据课程id 来获取学生成绩列表
     *
     * @param courseId
     * @return
     */
    List<ScoreResult> listStudentScore(Integer courseId);

    List<StudentResult> listAllStudent();

    StudentResult getStudentResultById(Integer studentId);

}
