<%@ page import="java.util.List" %>
<%@ page import="com.tust.school.res.domain.dto.course.CourseResult" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="top_student.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 我的课程列表 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel  heading-border">
                <div class="panel-menu">
                    <div class="row">
                        <div class="hidden-xs hidden-sm col-md-3">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-refresh"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-trash"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-plus" onclick="javascript:window.location.href='';"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-9 text-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-chevron-left"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-chevron-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body pn">
                    <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                        <thead>
                        <tr class="">
                            <th class="text-center hidden-xs">课程号</th>
                            <th class="hidden-xs">课程名</th>
                            <th class="hidden-xs">星期</th>
                            <th class="hidden-xs">课程开始时间</th>
                            <th class="hidden-xs">课程结束时间</th>
                            <th class="hidden-xs">限选人数</th>
                            <th class="hidden-xs">当前选课人数</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<CourseResult> courseResults = (List<CourseResult>) request.getAttribute("courseList");
                            for(CourseResult courseResult:courseResults)
                            {%>
                        <tr>
                            <td><%=courseResult.getNo()%></td>
                            <td><%=courseResult.getName() %></td>
                            <td><%=courseResult.getCourseDay() %></td>
                            <td><%=courseResult.getCourseStart() %></td>
                            <td><%=courseResult.getCourseEnd() %></td>
                            <td><%=courseResult.getMaxStuNum() %></td>
                            <td><%=courseResult.getCurrentStuNum() %></td>
                            <td><a href="/api/auth/student/course/info/<%=courseResult.getId()%>">详情</a></td>
                            <td><button onclick="unSelectCourse(<%=courseResult.getId() %>)" >退选</button></td>
                        </tr>
                        <%}
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>