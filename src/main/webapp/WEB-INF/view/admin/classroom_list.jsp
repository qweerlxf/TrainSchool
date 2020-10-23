<%@ page import="java.util.List" %>
<%@ page import="com.tust.school.res.domain.dto.classroom.ClassroomResult" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../top.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 教室列表 </h2>
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
                                    <i class="fa fa-plus" onclick="addClassroom(0)"></i>
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
                            <th class="text-center hidden-xs">教室Id</th>
                            <th class="hidden-xs">教室编号</th>
                            <th class="hidden-xs">教室名称</th>
                            <th class="hidden-xs">最大学生数</th>
                            <th class="hidden-xs">是否多媒体</th>
                            <th class="hidden-xs">地址</th>
                            <th class="hidden-xs">状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<ClassroomResult> classroomResults = (List<ClassroomResult>) request.getAttribute("classroomResults");
                            for(ClassroomResult classroomResult:classroomResults)
                            {%>
                        <tr>
                            <td><%=classroomResult.getId()%></td>
                            <td><%=classroomResult.getNo() %></td>
                            <td><%=classroomResult.getName() %></td>
                            <td><%=classroomResult.getMaxStuNum() %></td>
                            <td><%=classroomResult.getHasVideoStr() %></td>
                            <td><%=classroomResult.getAddress() %></td>
                            <td><%=classroomResult.getStatusStr() %></td>
                            <td>
                                <a href="/api/auth/admin/classroom/detail/<%=classroomResult.getId()%>">编辑</a>
                                <a href="/api/auth/admin/classroom/remove/<%=classroomResult.getId()%>">删除</a>
                                <button onclick="updateClassroomStatus(<%=classroomResult.getId()%>, <%=classroomResult.getStatus()%>)" >修改状态</button>
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