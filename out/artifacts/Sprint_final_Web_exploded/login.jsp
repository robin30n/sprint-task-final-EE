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
    String error = request.getParameter("error");
    if (error != null) {
%>
<div class="container alert alert-danger d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        Incorrect email or password
    </div>
</div>
<%
    }
%>

<div class="col-6 mx-auto">

    <form method="post" action="/login">
        <div class="container mt-5">
            <div class="form-floating">
                <input name="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Email address</label>
            </div>
            <div class="form-floating my-3">
                <input name="password" type="password" class="form-control" id="floatingPassword"
                       placeholder="Password">
                <label for="floatingPassword">Password</label>
            </div>
            <button class="w-100 btn btn-lg btn-success" type="submit">Log in</button>
        </div>
    </form>

</div>
</body>
</html>
