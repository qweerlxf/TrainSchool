package com.tust.school.res.service;

import com.tust.school.res.domain.dto.classroom.ClassroomResult;
import com.tust.school.res.domain.dto.classroom.ClassroomSaveParam;
import com.tust.school.res.domain.dto.classroom.ClassroomStatusParam;

import java.util.List;

public interface ClassroomService {

    /**
     * 教室列表
     *
     * @return
     */
    List<ClassroomResult> listAllClassroom();

    /**
     * 教室详情
     *
     * @param classroomId
     * @return
     */
    ClassroomResult getClassroomById(Integer classroomId);

    /**
     * 新增或者更新
     *
     * @param classroomSaveParam
     */
    void save(ClassroomSaveParam classroomSaveParam);

    /**
     * 更新状态
     *
     * @param classroomStatusParam
     */
    void updateStatus(ClassroomStatusParam classroomStatusParam);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Integer id);
}
