<%@ page import="java.util.Objects" %>
<%@ page import="com.tust.school.res.domain.dto.course.CourseResult" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tust.school.res.domain.dto.teacher.TeacherResult" %>
<%@ page import="com.tust.school.res.domain.dto.classroom.ClassroomResult" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../top.jsp"/>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 课程详情 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <div class="panel-body bg-light">
                    <%
                        CourseResult courseResult = (CourseResult) request.getAttribute("courseResult");
                        if (Objects.isNull(courseResult.getId())) {
                            courseResult.setName("");
                            courseResult.setCourseDay(0);
                            courseResult.setMaxStuNum(0);
                            courseResult.setTeacherName("");
                            courseResult.setCourseStart(new Time(0L));
                            courseResult.setCourseEnd(new Time(0L));
                        }
                    %>
                    <div class="section-divider mt20 mb40">
                        <span> 基本信息 </span>
                    </div>
                    <div class="section row">
                        &nbsp&nbsp&nbsp课程名&nbsp<input type="text" id="name" name="name" value="<%=courseResult.getName() %>"><br>
                        &nbsp&nbsp&nbsp星期:<input type="text" id="courseDay" name="courseDay" value="<%=courseResult.getCourseDay() %>"><br>
                        课程开始时间:<input type="text" id="courseStart" name="courseStart" value="<%=courseResult.getCourseStart() %>" class="Wdate" onClick="WdatePicker({dateFmt:'HH:mm:ss'})"  /><br>
                        课程结束时间:<input type="text" id="courseEnd" name="courseEnd" value="<%=courseResult.getCourseEnd() %>"  class="Wdate" onClick="WdatePicker({dateFmt:'HH:mm:ss'})"  /><br>
                        限选人数:<input type="text" id="maxStuNum" name="maxStuNum" value="<%=courseResult.getMaxStuNum() %>"><br>
                        任教教师:<select id="teacherSelect">
                        <option value="0" selected>请选择：</option>
                        <%
                            List<TeacherResult> list = (List) request.getAttribute("teacherResults");
                            for (TeacherResult teacherResult: list) {
                        %>
                        <option value="<%=teacherResult.getId()%>"  ><%=teacherResult.getName()%></option>
                        <%} %>
                    </select>
                        <br>
                        上课教室:<select id="classroomSelect">
                        <option value="0" selected>请选择：</option>
                        <%
                            List<ClassroomResult> classroomResults =  (List) request.getAttribute("classroomResults");
                            for (ClassroomResult classroomResult: classroomResults) {
                        %>
                        <option value="<%=classroomResult.getId()%>"  ><%=classroomResult.getName()%></option>
                        <%} %>
                    </select>
                        <div class="panel-footer text-right">
                            <button type="submit" class="button" onclick="saveCourse(<%=courseResult.getId()%>)" > 保存 </button>
                            <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>