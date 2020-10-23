<%@ page import="java.util.List" %>
<%@ page import="com.tust.school.res.domain.dto.classes.ClassResult" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册页面</title>
    <link type="text/css" rel="stylesheet" href="../../../js/css/reg.css">
</head>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/student/student.js"></script>

<body>
<div class="header" style="padding: 0;">
    <h2 style="color: white; width: 400px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">学员注册</h2>
</div>
<div id="reg_frame">

    <form id="line_height">
        <br>姓名<input type="text" id="name" name="name"><br>

        班级<select id="classSelect" >
        <option value="0" selected>请选择：</option>
        <%
            List<ClassResult> list = (List) request.getAttribute("classList");
            for (int i = 0; i < list.size(); i++) {
        %>
        <option value="<%=list.get(i).getId()%>"><%=list.get(i).getName()%>
        </option>
        <%} %>
    </select>
        <br>
        年龄<input type="text" id="age" name="age"><br>
        密码<input type="password" id="password" name="password"><br>
        确认<input type="password" id="confirmPassword" name="confirmPassword" ><br>
        <input type="button" id="btn_reg" value="提交" onclick="regStudent()" class="text_field"/>
    </form>
</div>
</body>
</html>