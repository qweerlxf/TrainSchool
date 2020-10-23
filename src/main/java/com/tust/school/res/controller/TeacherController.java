package com.tust.school.res.controller;

import com.tust.school.res.domain.dto.course.CourseResult;
import com.tust.school.res.domain.dto.course.InputScoreParam;
import com.tust.school.res.domain.dto.course.ScoreResult;
import com.tust.school.res.domain.result.ResultWrap;
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

@Controller
@RequestMapping("/api/auth")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;

    @Resource
    private StudentService studentService;

    @RequestMapping("/teacher/course/list")
    public ModelAndView courseSelected() {
        ModelAndView modelAndView = new ModelAndView("teacher/course_list");

        List<CourseResult> courseResults = courseService.listTeacherCourse();
        modelAndView.addObject("courseList", courseResults);

        return modelAndView;
    }

    /**
     * 老师任教课程学生选课列表
     */
    @RequestMapping("/teacher/course/score/{courseId}")
    public ModelAndView courseScore(@PathVariable("courseId") Integer courseId) {
        ModelAndView modelAndView = new ModelAndView("teacher/score_list");

        List<ScoreResult> scoreResults = studentService.listStudentScore(courseId);
        modelAndView.addObject("scoreResults", scoreResults);

        return modelAndView;
    }

    @RequestMapping("/teacher/input/score")
    @ResponseBody
    public ResultWrap<String> inputStudentScore(@Validated @RequestBody InputScoreParam inputScoreParam) {
        ResultWrap<String> resultWrap = new ResultWrap<>();

        teacherService.inputStudentScore(inputScoreParam);

        resultWrap.setResult("/api/auth/teacher/course/score/" + inputScoreParam.getCourseId());
        resultWrap.setSuccess(true);
        return resultWrap;
    }

}
