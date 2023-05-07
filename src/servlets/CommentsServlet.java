package servlets;

import db.DBConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.Comments;
import user.News;
import user.NewsCategories;
import user.Users;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/add-comment")
public class CommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = -1;

        try{
            id = Integer.parseInt(req.getParameter("nnews_id"));
        }catch (Exception e) {
        }

        HttpSession session = req.getSession();
        Users currentUser = (Users) session.getAttribute("useer");
        req.getSession().setAttribute("currentUser",currentUser);

        News news = DBConnector.getNews(id);
        req.setAttribute("neews",news);

        Users users = DBConnector.getUser(news.getUsers().getId());
        req.setAttribute("userrr",users);

        ArrayList<NewsCategories> newsCategories = DBConnector.getNewsCategories();
        req.setAttribute("newsCategoriees", newsCategories);

        ArrayList<Comments> comments = DBConnector.getAllComments();
        req.setAttribute("commments", comments);

        if (currentUser != null) {
            req.getRequestDispatcher("/comments.jsp").forward(req, resp);
        } else resp.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");
        int newsId =Integer.parseInt(req.getParameter("news_id"));

        HttpSession session = req.getSession();
        Users currentUser = (Users) session.getAttribute("useer");

        Comments comments = new Comments();
        comments.setComment(comment);
        comments.setUsers(new Users(currentUser.getId()));
        comments.setNews_id(newsId);
        if (!comments.getComment().equals("")) {
            DBConnector.addComment(comments);
            resp.sendRedirect("/");
        }
        else resp.sendRedirect("/?nullComment");
    }
}
