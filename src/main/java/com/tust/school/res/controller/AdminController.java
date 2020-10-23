package com.tust.school.res.controller;

import com.tust.school.res.domain.dto.classroom.ClassroomResult;
import com.tust.school.res.domain.dto.classroom.ClassroomSaveParam;
import com.tust.school.res.domain.dto.classroom.ClassroomStatusParam;
import com.tust.school.res.domain.dto.course.CourseResult;
import com.tust.school.res.domain.dto.course.CourseSaveParam;
import com.tust.school.res.domain.dto.course.CourseStatusParam;
import com.tust.school.res.domain.dto.student.StudentResult;
import com.tust.school.res.domain.dto.teacher.TeacherResult;
import com.tust.school.res.domain.dto.teacher.TeacherSaveParam;
import com.tust.school.res.domain.dto.teacher.TeacherStatusParam;
import com.tust.school.res.domain.result.ResultWrap;
import com.tust.school.res.service.ClassroomService;
import com.tust.school.res.service.CourseService;
import com.tust.school.res.service.StudentService;
import com.tust.school.res.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RequestMapping("/api/auth")
@Controller
public class AdminController {

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;

    @Resource
    private ClassroomService classroomService;

    @RequestMapping("/admin/student/list")
    public ModelAndView studentList() {
        ModelAndView modelAndView = new ModelAndView("admin/student_list");

        List<StudentResult> studentResults = studentService.listAllStudent();
        modelAndView.addObject("studentResults", studentResults);

        return modelAndView;
    }

    @RequestMapping("/admin/teacher/list")
    public ModelAndView teacherList() {
        ModelAndView modelAndView = new ModelAndView("admin/teacher_list");

        List<TeacherResult> teacherResults = teacherService.listAllTeacher();
        modelAndView.addObject("teacherResults", teacherResults);

        return modelAndView;
    }

    @RequestMapping("/admin/teacher/detail/{teacherId}")
    public ModelAndView teacherDetail(@PathVariable("teacherId") Integer teacherId) {
        ModelAndView modelAndView = new ModelAndView("admin/teacher_add");
        if (Objects.equals(teacherId, 0)) {
            modelAndView.addObject("teacherResult", new TeacherResult());
            return modelAndView;
        }

        TeacherResult teacherResult = teacherService.getTeacherResultById(teacherId);
        modelAndView.addObject("teacherResult", teacherResult);

        return modelAndView;
    }

    @RequestMapping("/admin/teacher/add")
    public ModelAndView teacherAdd(TeacherSaveParam teacherSaveParam){
        teacherService.save(teacherSaveParam);

        return teacherList();
    }

    @RequestMapping("/admin/teacher/save")
    @ResponseBody
    public ResultWrap<String> teacherSave(@Validated @RequestBody TeacherSaveParam teacherSaveParam) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        teacherService.save(teacherSaveParam);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/teacher/list");
        return resultWrap;
    }

    @RequestMapping("/admin/teacher/update/status")
    @ResponseBody
    public ResultWrap<String> updateTeacherStatus(@Validated @RequestBody TeacherStatusParam teacherSaveParam) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        teacherService.updateStatus(teacherSaveParam);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/teacher/list");
        return resultWrap;
    }

    @RequestMapping("/admin/teacher/remove/{id}")
    public ModelAndView teacherDelete(@PathVariable("id") Integer id){
        teacherService.delete(id);
        return teacherList();
    }

    @RequestMapping("/admin/teacher/delete/{id}")
    @ResponseBody
    public ResultWrap<String> deleteTeacher(@PathVariable("id") Integer id) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        teacherService.delete(id);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/teacher/list");
        return resultWrap;
    }

    @RequestMapping("/admin/course/list")
    public ModelAndView courseList() {
        ModelAndView modelAndView = new ModelAndView("admin/course_list");

        List<CourseResult> courseResults = courseService.listAllCourse();
        modelAndView.addObject("courseResults", courseResults);

        return modelAndView;
    }

    @RequestMapping("/admin/course/detail/{courseId}")
    public ModelAndView courseDetail(@PathVariable("courseId") Integer courseId) {
        ModelAndView modelAndView = new ModelAndView("admin/course_add");
        List<TeacherResult> teacherResults = teacherService.listAllTeacher();
        modelAndView.addObject("teacherResults", teacherResults);

        List<ClassroomResult> classroomResults = classroomService.listAllClassroom();
        modelAndView.addObject("classroomResults", classroomResults);

        if (Objects.equals(courseId, 0)) {
            modelAndView.addObject("courseResult", new CourseResult());
            return modelAndView;
        }

        CourseResult courseResult = courseService.getCourseResultById(courseId);
        modelAndView.addObject("courseResult", courseResult);

        return modelAndView;
    }

    @RequestMapping("/admin/course/save")
    @ResponseBody
    public ResultWrap<String> courseSave(@Validated @RequestBody CourseSaveParam courseSaveParam) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        courseService.save(courseSaveParam);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/course/list");
        return resultWrap;
    }

    @RequestMapping("/admin/course/update/status")
    @ResponseBody
    public ResultWrap<String> updateCourseStatus(@Validated @RequestBody CourseStatusParam courseStatusParam) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        courseService.updateStatus(courseStatusParam);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/course/list");
        return resultWrap;
    }

    @RequestMapping("/admin/course/delete/{id}")
    @ResponseBody
    public ResultWrap<String> deleteCourse(@PathVariable("id") Integer id) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        courseService.delete(id);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/course/list");
        return resultWrap;
    }

    @RequestMapping("/admin/course/remove/{id}")
    public ModelAndView courseDelete(@PathVariable("id") Integer id){
        courseService.delete(id);
        return courseList();
    }

    @RequestMapping("/admin/classroom/list")
    public ModelAndView classroomList() {
        ModelAndView modelAndView = new ModelAndView("admin/classroom_list");

        List<ClassroomResult> classroomResults = classroomService.listAllClassroom();
        modelAndView.addObject("classroomResults", classroomResults);

        return modelAndView;
    }

    @RequestMapping("/admin/classroom/detail/{classroomId}")
    public ModelAndView classroomDetail(@PathVariable("classroomId") Integer classroomId) {
        ModelAndView modelAndView = new ModelAndView("admin/classroom_add");
        if (Objects.equals(classroomId, 0)) {
            modelAndView.addObject("classroomResult", new ClassroomResult());
            return modelAndView;
        }

        ClassroomResult classroomResult = classroomService.getClassroomById(classroomId);
        modelAndView.addObject("classroomResult", classroomResult);

        return modelAndView;
    }

    @RequestMapping("/admin/classroom/save")
    @ResponseBody
    public ResultWrap<String> classroomSave(@Validated @RequestBody ClassroomSaveParam classroomSaveParam) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        classroomService.save(classroomSaveParam);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/classroom/list");
        return resultWrap;
    }

    @RequestMapping("/admin/classroom/update/status")
    @ResponseBody
    public ResultWrap<String> updateClassroomStatus(@Validated @RequestBody ClassroomStatusParam classroomStatusParam) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        classroomService.updateStatus(classroomStatusParam);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/classroom/list");
        return resultWrap;
    }

    @RequestMapping("/admin/classroom/delete/{id}")
    @ResponseBody
    public ResultWrap<String> deleteClassroom(@PathVariable("id") Integer id) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        classroomService.delete(id);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/admin/classroom/list");
        return resultWrap;
    }

    @RequestMapping("/admin/classroom/remove/{id}")
    public ModelAndView classroomDelete(@PathVariable("id") Integer id){
        classroomService.delete(id);
        return classroomList();
    }
}
