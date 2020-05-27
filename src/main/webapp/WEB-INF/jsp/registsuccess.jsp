<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <%-- <base href="<%=basepath%>" /> --%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <title>注册成功跳转界面</title>
    <style type="text/css">
        #d1 {
             height: 40px;
             weight: 800px;
             magin: auto;
             top: 300px;
             background-color: green;
        }
        #s2 {
             color: red;
             font-size: 30px;
        }
        a {
            magin: auto;
        }
    </style>
    <script type="text/javascript">
        var i = 9;
        var Interval = setInterval(function () {
            document.getElementById("s2").innerHTML = i + "";
            i--;
            if (i == 0) {
                clearInterval(Interval);
                window.location="/login"; 
            }
        }, 1000);

    </script>
</head>
<body>
<h1>注册成功！！</h1>
<div id="d1" class="center-block">
    <a href="<%=request.getContextPath()%>/login.jsp"><h3>立即跳转</h3></a><br/> 
    <span id="s1">${tip}</span>
    <h2><span id="s2">10</span>秒后自动跳转到登录页面</h2>
</div>

</body>
</html>

