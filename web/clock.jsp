<%@page contentType="text/html;" pageEncoding="utf-8"  %>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP Clock</title>
</head>
<body>
    <div align="center">
        <br/>
        Hello there!
        <br/><br/>

        It's been <%= System.currentTimeMillis() %> miliseconds since midnight, January 1st 1970.
        <br/><br/>

        In other words, it's
        <%
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEEEEE");
            String today = simpleDateFormat.format(now);
            out.println(today.trim());
        %>
    </div>
</body>
</html>
