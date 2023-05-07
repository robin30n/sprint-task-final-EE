package servlets;

import db.DBConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.News;
import user.NewsCategories;
import user.Users;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/add-blog")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users currentUser = (Users) session.getAttribute("useer");
        req.getSession().setAttribute("currentUser",currentUser);

        ArrayList<NewsCategories> newsCategories = DBConnector.getNewsCategories();
        req.setAttribute("newsCategoriess", newsCategories);
        if (currentUser != null && currentUser.getRole_id().equals("2")) {
            req.getRequestDispatcher("/add_news.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long newsCategory = Long.parseLong(req.getParameter("newsCategory"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        News news = new News();
        news.setContent(content);
        news.setTitle(title);
        news.setUsers(new Users(Long.parseLong(req.getParameter("userId"))));
        news.setNewsCategories(new NewsCategories(newsCategory));

        if (news.getContent().equals("") || news.getTitle().equals("")) {
            resp.sendRedirect("/add-blog?notNull");
        } else {
            DBConnector.addNews(news);
            resp.sendRedirect("/add-blog?success");
        }
    }
}
