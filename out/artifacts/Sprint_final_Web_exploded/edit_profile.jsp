
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
<%
    String done = request.getParameter("done");
    String notSame = request.getParameter("notSame");
    if (notSame != null) {
%>
<div class="container alert alert-danger d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        Passwords are not the same!!!
    </div>
</div>
<%
    }
    if (done!=null){
%>
<div class="container alert alert-success d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        Well done everything is great
    </div>
</div>
<%
    }
%>
<%@include file="navbar.jsp" %>
<div class="text-center mt-5">
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editUser">
        edit user
    </button>
</div>

<form action="/edit" method="post">
    <div class="modal fade" id="editUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>Full name: </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <input type="text" class="form-control" name="FullName">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>New password: </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <input type="password" class="form-control" name="newPassword">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>Rewrite new password: </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <input type="password" class="form-control" name="secNewPassword">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
