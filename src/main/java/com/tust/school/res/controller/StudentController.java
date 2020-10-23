package com.tust.school.res.controller;

import com.tust.school.res.config.handler.ServiceException;
import com.tust.school.res.config.handler.ServiceExceptionEnum;
import com.tust.school.res.domain.dto.classes.ClassResult;
import com.tust.school.res.domain.dto.course.CourseResult;
import com.tust.school.res.domain.dto.message.StuMessageAddParam;
import com.tust.school.res.domain.dto.message.StuMessageParam;
import com.tust.school.res.domain.dto.message.StuMessageResult;
import com.tust.school.res.domain.dto.student.StudentRegParam;
import com.tust.school.res.domain.enums.BaseStatusEnum;
import com.tust.school.res.domain.result.ResultWrap;
import com.tust.school.res.service.ClassService;
import com.tust.school.res.service.CourseService;
import com.tust.school.res.service.StuMessageService;
import com.tust.school.res.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private ClassService classService;

    @Resource
    private CourseService courseService;

    @Resource
    private StuMessageService stuMessageService;

    @RequestMapping(value = "/student/reg", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrap<String> studentReg(@Validated @RequestBody StudentRegParam studentRegParam) throws Exception {
        if (!Objects.equals(studentRegParam.getPassword(), studentRegParam.getConfirmPassword())) {
            throw new ServiceException(ServiceExceptionEnum.TWICE_PASSWORD_NOT_EQUALS);
        }
        ResultWrap<String> resultWrap = new ResultWrap<>();

        String no = studentService.regStudent(studentRegParam);

        resultWrap.setResult("/api/student/success/" + no);
        resultWrap.setSuccess(true);
        return resultWrap;
    }

    @RequestMapping("/student/reg/index")
    public ModelAndView regStudentIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/reg");

        List<ClassResult> classResults = classService.listAllClass();
        modelAndView.addObject("classList", classResults);

        return modelAndView;
    }

    @RequestMapping("/student/success/{no}")
    public ModelAndView regSuccess(@PathVariable("no") String no) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/success");

        modelAndView.addObject("no", no);

        return modelAndView;
    }

    @RequestMapping("/auth/student/success/course/list/unselected")
    public ModelAndView courseListUnSelect() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/un_select_course");

        List<CourseResult> courseResults = courseService.listStudentUnselectedCourse();
        modelAndView.addObject("courseList", courseResults);

        return modelAndView;
    }

    @RequestMapping("/auth/student/success/course/list/selected")
    public ModelAndView courseListSelected() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/selected_course");

        List<CourseResult> courseResults = courseService.listStudentSelectedCourse();
        modelAndView.addObject("courseList", courseResults);

        return modelAndView;
    }

    @RequestMapping("/auth/student/select/course/{courseId}")
    @ResponseBody
    public ResultWrap<String> selectCourse(@PathVariable("courseId") Integer courseId) {
        ResultWrap<String> resultWrap = new ResultWrap<>();
        studentService.selectCourse(courseId);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/student/success/course/list/selected");
        return resultWrap;
    }

    @RequestMapping("/auth/student/un/select/course/{courseId}")
    @ResponseBody
    public ResultWrap<String> unSelectCourse(@PathVariable("courseId") Integer courseId) {
        ResultWrap<String> resultWrap = new ResultWrap<>();
        studentService.unSelect(courseId);

        resultWrap.setSuccess(true);
        resultWrap.setResult("/api/auth/student/success/course/list/selected");
        return resultWrap;
    }

    @RequestMapping("/auth/student/course/info/{courseId}")
    public ModelAndView courseInfo(@PathVariable("courseId") Integer courseId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/course_info");

        CourseResult courseResult = courseService.getCourseResultById(courseId);
        modelAndView.addObject("courseResult", courseResult);

        StuMessageParam stuMessageParam = new StuMessageParam();
        stuMessageParam.setCourseId(courseId);
        stuMessageParam.setStatus(BaseStatusEnum.ONLINE.getStatus());
        List<StuMessageResult> results = stuMessageService.listStuMessage(stuMessageParam);
        modelAndView.addObject("stuMessageList", results);

        return modelAndView;
    }

    @RequestMapping("/auth/student/message/add")
    public ModelAndView studentMessageAdd(StuMessageAddParam stuMessageAddParam){
        stuMessageService.addStuMessage(stuMessageAddParam);

        ModelAndView modelAndView = new ModelAndView("student/message_success");
        modelAndView.addObject("courseId", stuMessageAddParam.getCourseId());

        return modelAndView;
    }
}
