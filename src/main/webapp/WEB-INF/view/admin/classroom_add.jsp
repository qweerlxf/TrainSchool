<%@ page import="com.tust.school.res.domain.dto.classroom.ClassroomResult" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../top.jsp"/>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 教室信息 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <div class="panel-body bg-light">
                    <%
                        ClassroomResult classroomResult = (ClassroomResult) request.getAttribute("classroomResult");
                        if(Objects.isNull(classroomResult.getId())){
                            classroomResult.setName("");
                            classroomResult.setMaxStuNum(0);
                            classroomResult.setAddress("");
                        }
                    %>
                    <div class="section-divider mt20 mb40">
                        <span> 基本信息 </span>
                    </div>
                    <div class="section row">
                        &nbsp&nbsp&nbsp教室名称&nbsp<input type="text" id="name" name="name" value="<%=classroomResult.getName() %>"><br><br><br>
                        &nbsp&nbsp&nbsp最大学生数&nbsp<input type="text" id="maxStuNum" name="maxStuNum" value="<%=classroomResult.getMaxStuNum() %>"><br><br><br>
                        &nbsp&nbsp&nbsp是否多媒体&nbsp<select id="videoSelect">
                        <option value="-1" selected>&nbsp请选择&nbsp</option>
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                        <br><br><br>
                        &nbsp&nbsp&nbsp地址&nbsp<input type="text" id="address" name="address" value="<%=classroomResult.getAddress() %>"><br><br>
                        <div class="panel-footer text-right">
                            <button type="submit" class="button" onclick="saveClassroom(<%=classroomResult.getId() %>)" > 保存 </button>
                            <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>