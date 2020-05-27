<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="<%=request.getContextPath()%>/admin/showCourse">课程管理</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/showStudent">学员管理</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/showTeacher">讲师管理</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/userPasswordRest">账号密码重置<sapn class="glyphicon glyphicon-repeat pull-right" /></a></li>
        <li><a href="<%=request.getContextPath()%>/admin/passwordRest">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="<%=request.getContextPath()%>/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
    </ul>
</div>