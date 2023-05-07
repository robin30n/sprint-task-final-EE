<%@ page import="java.util.ArrayList" %>
<%@ page import="user.News" %>
<%@ page import="user.Comments" %>
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

<div class="container">
    <div class="text-center mt-5">
        <h1>Welcome To BITLAB BLOG</h1>
    </div>
</div>
<div class="container" style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-12">
            <%
                Users user = (Users) request.getAttribute("userrr"); //to get user that added this news
                ArrayList<News> news = (ArrayList<News>) request.getAttribute("newss");
                if (news != null) {
                    for (News n : news) {
            %>


            <div class="row mt-3">
                <div class="col-11" style="background-color: lightgrey;">
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

                    <%--error for user who tried to send an empty comment--%>
                    <%
                        String error = request.getParameter("nullComment");
                        if (currentUser != null) {
                            if (error != null) {
                    %>
                    <div class="container alert alert-danger d-flex align-items-center justify-content-center my-3"
                         role="alert">
                        <div>
                            You are trying to send an empty comment
                        </div>
                    </div>
                    <%
                        }
                    %>

                    <div class="row mt-2">
                        <div class="col-9">
                            <form action="/add-comment" method="post">
                                <input type="hidden" name="news_id" value="<%=n.getId()%>">
                                <textarea class="form-control" name="comment" placeholder="Write a comment"></textarea>
                                <div class="d-flex justify-content-evenly mt-3">
                                    <button class="btn btn-success">ADD COMMENT</button>
                                    <a href="/add-comment?nnews_id=<%=n.getId()%>" class="btn btn-info">To see
                                        comments</a>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>

