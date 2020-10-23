<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../teacher/top_teacher.jsp"/>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 个人信息 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <div class="panel-body bg-light">
                    <div class="section-divider mt20 mb40">
                        <span> 基本信息 </span>
                    </div>
                    <div class="section row">
                        <a href="/api/auth/teacher/course/list" id="s_link">查看我的课程</a><br><br><br>
                        <a href="/api/auth/claim_voucher/deal" id="s_link">待处理报销单</a><br><br><br>
                        <a href="/api/auth/claim_voucher/self" id="s_link">个人报销单</a><br><br><br>
                        <a href="/api/auth/claim_voucher/to_add" id="s_link">填写报销单</a><br><br>
                    </div>
                    <div class="panel-footer text-right">
                        <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../bottom.jsp"/>