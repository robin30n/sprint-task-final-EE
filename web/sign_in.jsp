<%@ page import="java.util.ArrayList" %>
<%--
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
<%
    String notSame = request.getParameter("notSame");
    String registered = request.getParameter("alreadyRegistered");
    if (notSame != null) {
%>
<div class="container alert alert-danger d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        Passwords are not the same!!!
    </div>
</div>
<%
    }
    if (registered != null) {
%>
<div class="container alert alert-danger d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        this email is already registered!!!
    </div>
</div>
<%
    }
%>

<div class="col-6 mx-auto">

    <form method="post" action="/sign-in">
        <div class="container mt-5">
            <div class="form-floating my-3">
                <input name="fullName" type="text" class="form-control" placeholder="ФИО">
                <label>Full name</label>
            </div>
            <div class="form-floating my-3">
                <input name="email" type="email" class="form-control" placeholder="name@example.com">
                <label>Email address</label>
            </div>
            <div class="form-floating my-3">
                <input name="password" type="password" class="form-control"
                       placeholder="Password">
                <label>Password</label>
            </div>
            <div class="form-floating my-3">
                <input name="secPassword" type="password" class="form-control"
                       placeholder="Rewrite the Password">
                <label>Rewrite the Password</label>
            </div>
            <button class="w-100 btn btn-lg btn-success" type="submit">Sign in</button>
        </div>
    </form>

</div>
</body>
</html>
