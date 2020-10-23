<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>index</title>
</head>
<style type="text/css">
    #btn {
        font-size: 14px;
        font-family: 宋体;

        width: 100px;
        height: 28px;
        line-height: 28px;
        text-align: center;

        color: white;
        background-color: #3BD9FF;
        border-radius: 6px;
        border: 0;
    }
</style>
<body>
注册成功！你的账号是：<%=request.getAttribute("no")%>
<a href="/index" id="btn">返回登录</a>
</body>
</html>