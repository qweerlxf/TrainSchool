package com.tust.school.res.service;

import com.tust.school.res.domain.dto.course.InputScoreParam;
import com.tust.school.res.domain.dto.teacher.TeacherResult;
import com.tust.school.res.domain.dto.teacher.TeacherSaveParam;
import com.tust.school.res.domain.dto.teacher.TeacherStatusParam;

import java.util.List;

public interface TeacherService {

    /**
     * 录入学生成绩
     *
     * @param inputScoreParam
     */
    void inputStudentScore(InputScoreParam inputScoreParam);

    /**
     * 教师列表
     *
     * @return
     */
    List<TeacherResult> listAllTeacher();

    /**
     * 教师详情
     *
     * @param teacherId
     * @return
     */
    TeacherResult getTeacherResultById(Integer teacherId);

    /**
     * 教师新增或修改
     *
     * @param teacherSaveParam
     */
    void save(TeacherSaveParam teacherSaveParam);

    /**
     * 教师上下架
     *
     * @param teacherSaveParam
     */
    void updateStatus(TeacherStatusParam teacherSaveParam);

    /**
     * 删除教师
     *
     * @param id
     */
    void delete(Integer id);
}
