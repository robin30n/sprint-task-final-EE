<%@ page import="user.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="user.Comments" %>
<%@ page import="user.NewsCategories" %><%--
  Created by IntelliJ IDEA.
  User: akimzhan
  Date: 07.05.2023
  Time: 04:04
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
    ArrayList<NewsCategories> newsCategories = (ArrayList<NewsCategories>) request.getAttribute("newsCategoriees"); //all categories
    News n = (News) request.getAttribute("neews"); //for current news
    ArrayList<Comments> comments = (ArrayList<Comments>) request.getAttribute("commments"); //for all comments
    Users current_user = (Users) session.getAttribute("currentUser");

    String notNull = request.getParameter("notNull"); //errors
    String success = request.getParameter("success"); //errors
    if (notNull != null) {
%>
<div class="container alert alert-danger d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        You can not update to an empty text
    </div>
</div>
<%
    }
    if (success!=null){
%>
<div class="container alert alert-success d-flex align-items-center justify-content-center my-3" role="alert">
    <div>
        your blog has been updated successfully
    </div>
</div>
<%
    }
%>
<div class="row my-3">
    <div class="col-11 mx-auto" style="background-color: lightgrey;">
        <h2><%=n.getTitle()%>
        </h2>
        <p class="mt-2"><%=n.getContent()%>
        </p>
        <p class="mt-2">
            Posted by <strong><%=n.getUsers().getFull_name()%>
        </strong>
            at <strong><%=n.getTimestamp()%>
        </strong>
            in category <strong><%=n.getNewsCategories().getName()%>
        </strong>
        </p>
        <%
            if (current_user.getRole_id().equals("2")) {
        %>
        <button type="button" class="btn btn-primary btn-sm mb-3" data-bs-toggle="modal"
                data-bs-target="#editNews">
            + Edit News
        </button>
        <button type="button" class="btn btn-danger btn-sm mb-3" data-bs-toggle="modal"
                data-bs-target="#deleteNews">
            - Delete News
        </button>
        <%
            }
        %>
    </div>
</div>

<%
    for (Comments comment : comments) {
        if (comment.getNews_id() == n.getId()) {
%>
<div class="container row mt-2 mx-auto bg-warning rounded-3">

    <div class="col-12">

        <h5><%=comment.getUsers().getFull_name()%>
        </h5>

        <p><%=comment.getComment()%>
        </p>

        <p>At <strong><%=comment.getPost_date()%>
        </strong></p>

    </div>

</div>
<%
        }
    }
%>
</body>
</html>

<form action="/delete" method="post">
    <div class="modal fade" id="deleteNews" data-bs-backdrop="static"
         data-bs-keyboard="false"
         tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <input type="hidden" name="delete_news_id" value="<%=n.getId()%>">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal
                        title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h5>Are you sure?</h5>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-bs-dismiss="modal">NO
                    </button>
                    <button type="submit" class="btn btn-danger">YES</button>
                </div>
            </div>
        </div>
    </div>
</form>


<form action="/edit-news" method="post">
    <div class="modal fade" id="editNews" data-bs-backdrop="static" data-bs-keyboard="false"
         tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">

                <div class="modal-body">
                    <input type="hidden" name="edit_news_id" value="<%=n.getId()%>">

                    <div class="row mt-3">
                        <div class="col-12">
                            <label>Title: </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <input type="text" class="form-control" name="editTitle"
                                   value="<%=n.getTitle()%>">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>Content: </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <textarea class="form-control" name="editContent" rows="5" placeholder="Content"><%=n.getContent()%></textarea>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>Category: </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <select class="form-select" name="editCategory">
                                <%
                                    if (newsCategories != null) {
                                        for (NewsCategories newsCategory : newsCategories) {
                                %>
                                <option <%=(newsCategory.getId()==n.getNewsCategories().getId()?"selected":"")%> value="<%=newsCategory.getId()%>"><%=newsCategory.getName()%>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-6">
                            <button class="btn btn-success">UPDATE NEWS</button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>