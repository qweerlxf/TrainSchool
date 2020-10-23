<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
    <link type="text/css" rel="stylesheet" href="../../../js/css/login.css">

</head>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/login/login.js"></script>

<body>
<div class="header" style="padding: 0;">
    <h2 style="color: white; width: 400px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">欢迎使用本培训系统</h2>
</div>
<div id="login_frame">

    <p id="image_logo"><img src="../../../js/img/user.png"></p>

    <form method="post" action="login.js">

        <p><label class="label_input">用户名</label><input type="text" name="account" id="account" class="text_field"/></p>
        <p><label class="label_input">密码</label><input type="password" name="password" id="password" class="text_field"/></p>

        <div id="login_control">
            <input type="button" id="btn_login" value="登录" onclick="login() " class="text_field"/>
            <input type="button" id="btn_login2" value="学员注册" onclick="studentReg()" class="text_field"/>
        </div>


    </form>

</div>
</body>
</html>