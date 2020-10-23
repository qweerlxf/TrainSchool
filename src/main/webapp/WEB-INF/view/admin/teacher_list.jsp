<%@ page import="java.util.List" %>
<%@ page import="com.tust.school.res.domain.dto.teacher.TeacherResult" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../top.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 教师列表 </h2>
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
                                    <i class="fa fa-plus" onclick="addTeacherIndex(0)"></i>
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
                            <th class="text-center hidden-xs">教师ID</th>
                            <th class="hidden-xs">教师编号</th>
                            <th class="hidden-xs">教师姓名</th>
                            <th class="hidden-xs">手机号</th>
                            <th class="hidden-xs">专业</th>
                            <th class="hidden-xs">状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<TeacherResult> teacherResults = (List<TeacherResult>) request.getAttribute("teacherResults");
                            for(TeacherResult teacherResult:teacherResults)
                            {%>
                        <tr>
                            <td><%=teacherResult.getId()%></td>
                            <td><%=teacherResult.getNo() %></td>
                            <td><%=teacherResult.getName() %></td>
                            <td><%=teacherResult.getPhone() %></td>
                            <td><%=teacherResult.getSpecialty() %></td>
                            <td><%=teacherResult.getStatusStr() %></td>
                            <td>
                                <a href="/api/auth/admin/teacher/detail/<%=teacherResult.getId()%>">编辑</a>
                                <a href="/api/auth/admin/teacher/remove/<%=teacherResult.getId()%>">删除</a>
                                <button onclick="updateTeacherStatus(<%=teacherResult.getId()%>, <%=teacherResult.getStatus()%>)" >修改状态</button>
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