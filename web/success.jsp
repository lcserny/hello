<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 27.07.2015
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    Welcome ${requestScope["user"].getUsername()}.
</body>
</html>
