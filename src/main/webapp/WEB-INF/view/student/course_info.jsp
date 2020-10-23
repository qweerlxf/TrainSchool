<%@ page import="java.util.List" %>
<%@ page import="com.tust.school.res.domain.dto.course.CourseResult" %>
<%@ page import="com.tust.school.res.domain.dto.message.StuMessageResult" %>
<%@ page import="org.springframework.util.CollectionUtils" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="top_student.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 课程详情 </h2>
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
                        <tbody>
                        <%
                            CourseResult courseResult = (CourseResult) request.getAttribute("courseResult");
                            {%>
                        <br><br>
                        &nbsp&nbsp&nbsp&nbsp课程号:<%=courseResult.getNo()%><br><br>
                        &nbsp&nbsp&nbsp&nbsp课程名:<%=courseResult.getName() %><br><br>
                        &nbsp&nbsp&nbsp&nbsp星期:<%=courseResult.getCourseDay() %><br><br>
                        &nbsp&nbsp&nbsp&nbsp课程开始时间:<%=courseResult.getCourseStart() %><br><br>
                        &nbsp&nbsp&nbsp&nbsp课程结束时间:<%=courseResult.getCourseEnd() %><br><br>
                        &nbsp&nbsp&nbsp&nbsp限选人数:<%=courseResult.getMaxStuNum() %><br><br>
                        &nbsp&nbsp&nbsp&nbsp当前选课人数:<%=courseResult.getCurrentStuNum() %><br><br><br>
                        <%}
                        %>
                        <h3>&nbsp&nbsp&nbsp&nbsp课程留言</h3><br><br>
                        <%
                            List<StuMessageResult> stuMessageList = (List<StuMessageResult>) request.getAttribute("stuMessageList");
                            if(!CollectionUtils.isEmpty(stuMessageList)){
                                for(StuMessageResult stuMessageResult:stuMessageList)
                                {%>
                        <%=stuMessageResult.getStuName() %> : <%=stuMessageResult.getContent()%><br>
                        <%}
                        }
                        %>
                        <br><br>&nbsp&nbsp&nbsp&nbsp
                        <form id="messageForm" action="/api/auth/student/message/add">
                            <input type="text" name="content">
                            <input type="text" hidden="true" value="<%=courseResult.getId() %>" name="courseId">
                            <input type="submit" value="提交留言">
                        </form>
                        <br><br>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>