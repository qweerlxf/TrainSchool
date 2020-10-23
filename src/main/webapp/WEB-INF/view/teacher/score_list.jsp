<%@ page import="java.util.List" %>
<%@ page import="com.tust.school.res.domain.dto.course.ScoreResult" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="top_teacher.jsp"/>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 学员成绩列表 </h2>
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
                            <th class="text-center hidden-xs">学生Id</th>
                            <th class="hidden-xs">学生姓名</th>
                            <th class="hidden-xs">课程Id</th>
                            <th class="hidden-xs">课程名称</th>
                            <th class="hidden-xs">成绩</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<ScoreResult> scoreResults = (List<ScoreResult>) request.getAttribute("scoreResults");
                            for(ScoreResult scoreResult:scoreResults)
                            {%>
                        <tr>
                            <td><%=scoreResult.getStudentId()%></td>
                            <td><%=scoreResult.getStudentName() %></td>
                            <td><%=scoreResult.getCourseId() %></td>
                            <td><%=scoreResult.getCourseName() %></td>
                            <td><input type="text" value="<%=scoreResult.getScore() %>" id="score"></td>
                            <td><button onclick="inputScore(this)" >修改成绩</button></td>
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