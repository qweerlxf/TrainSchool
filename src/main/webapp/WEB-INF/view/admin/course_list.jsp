<%@ page import="java.util.List" %>
<%@ page import="com.tust.school.res.domain.dto.course.CourseResult" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../top.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 课程列表 </h2>
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
                                    <i class="fa fa-plus" onclick="addCourseIndex(0)"></i>
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
                            <th class="text-center hidden-xs">Id</th>
                            <th class="hidden-xs">课程编号</th>
                            <th class="hidden-xs">开始时间</th>
                            <th class="hidden-xs">结束时间</th>
                            <th class="hidden-xs">星期</th>
                            <th class="hidden-xs">课程名称</th>
                            <th class="hidden-xs">限选人数</th>
                            <th class="hidden-xs">当前人数</th>
                            <th class="hidden-xs">任教教师</th>
                            <th class="hidden-xs">教室</th>
                            <th class="hidden-xs">状态</th>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<CourseResult> courseResults = (List<CourseResult>) request.getAttribute("courseResults");
                            for(CourseResult courseResult:courseResults)
                            {%>
                        <tr>
                            <td><%=courseResult.getId()%></td>
                            <td><%=courseResult.getNo() %></td>
                            <td><%=courseResult.getCourseStart() %></td>
                            <td><%=courseResult.getCourseEnd() %></td>
                            <td><%=courseResult.getCourseDay() %></td>
                            <td><%=courseResult.getName() %></td>
                            <td><%=courseResult.getMaxStuNum() %></td>
                            <td><%=courseResult.getCurrentStuNum() %></td>
                            <td><%=courseResult.getTeacherName() %></td>
                            <td><%=courseResult.getClassroomName() %></td>
                            <td><%=courseResult.getStatusStr() %></td>
                            <td>
                                <a href="/api/auth/admin/course/detail/<%=courseResult.getId()%>">编辑</a>
                                <a href="/api/auth/admin/course/remove/<%=courseResult.getId()%>">删除</a>
                                <button onclick="updateCourseStatus(<%=courseResult.getId()%>, <%=courseResult.getStatus()%>)" >修改状态</button>
                            </td>
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

<jsp:include page="../bottom.jsp"/>