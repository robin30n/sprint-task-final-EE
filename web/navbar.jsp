<%@ page import="user.Users" %><%
  Users currentUser = (Users) session.getAttribute("useer");
%>

<nav class="bg-info rounded-5 px-5">
  <div class="d-flex align-items-center">
    <h4 class="m-1"><a class="navbar-brand" href="/home.html">BITLAB SHOP</a></h4>
    <%
      if (currentUser==null){
    %>
    <h6 class="m-3"><a class="navbar-brand" href="/login">Log in</a></h6>
    <h6 class="m-3"><a class="navbar-brand" href="/sign-in">Sign in</a></h6>
    <%
      }else {
    %>
    <h6 class="m-3"><a class="navbar-brand" href="/log-out">Log out</a></h6>
    <%
      if (currentUser.getRole_id().equals("2")){
    %>
    <h6 class="m-3"><a class="navbar-brand" href="/add-blog">add blog</a></h6>
    <%
      }
    %>
    <h6 class="m-3"><a class="navbar-brand" href="/edit">edit user</a></h6>
    <%
      }
    %>
  </div>
</nav>