<%@ page import="java.util.ArrayList" %>
<%@ page import="user.NewsCategories" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="css_and_js.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<%
    Users users = (Users) session.getAttribute("currentUser");
    String notNull = request.getParameter("notNull");
    String success = request.getParameter("success");
    if (notNull != null) {
%>
<div class="container alert alert-danger d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        You can not send an empty text
    </div>
</div>
<%
    }
    if (success!=null){
%>
<div class="container alert alert-success d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        your blog has been added successfully
    </div>
</div>
<%
    }
%>
<div class="container" style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-8 mx-auto">
            <form action="/add-blog" method="post">
                <input type="hidden" name="userId" value="<%=users.getId()%>">
                <div class="row">
                    <div class="col-12">
                        <label>TITLE </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="title" placeholder="Title">
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <label>Categories </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <select class="form-select" name="newsCategory">
                            <%
                                ArrayList<NewsCategories> newsCategories = (ArrayList<NewsCategories>) request.getAttribute("newsCategoriess");
                                if (newsCategories != null) {
                                    for (NewsCategories newsCategory : newsCategories) {
                            %>
                            <option value="<%=newsCategory.getId()%>"><%=newsCategory.getName()%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>CONTENT </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <textarea class="form-control" name="content" rows="5" placeholder="Content"></textarea>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <button class="btn btn-success">ADD BLOG</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>