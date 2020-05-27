<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="<%=request.getContextPath()%>/student/showCourse">所有课程</a></li>
        <li><a href="<%=request.getContextPath()%>/student/selectedCourse">报名课程</a></li>
        <li><a href="<%=request.getContextPath()%>/student/overCourse">课程成绩</a></li>
        <li><a href="<%=request.getContextPath()%>/student/passwordRest">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="<%=request.getContextPath()%>/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
    </ul>
</div>