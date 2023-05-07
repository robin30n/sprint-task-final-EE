

<%@ page import="user.Users" %><%--
  Created by IntelliJ IDEA.
  User: akimzhan
  Date: 14.04.2023
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="css_and_js.jsp" %>
</head>
<body class="p-3">
<%@include file="navbar.jsp" %>
<div class="container text-center mt-5">
    <%
        Users users = (Users) request.getAttribute("user");
        if (users != null){
    %>
    <h1>Welcome <%=users.getFull_name()%>
    </h1>
    <h5>This is your profile page</h5>
    <%
        }else response.sendRedirect("/login");
    %>
</div>
</body>
</html>
