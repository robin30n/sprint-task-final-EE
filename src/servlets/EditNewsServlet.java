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

@WebServlet(value = "/edit-news")
public class EditNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        News news = new News();
        news.setId(Long.parseLong(req.getParameter("edit_news_id")));
        news.setNewsCategories(new NewsCategories(Long.parseLong(req.getParameter("editCategory"))));
        news.setTitle(req.getParameter("editTitle"));
        news.setContent(req.getParameter("editContent"));

        if (news.getContent().equals("") || news.getTitle().equals("")) {
            resp.sendRedirect("/add-blog?notNull");
        } else {
            DBConnector.updateNews(news);
            resp.sendRedirect("/add-blog?success");
        }
    }
}
