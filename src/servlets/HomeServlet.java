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
import user.Users;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/home.html")
public class HomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Users currentUser = (Users) session.getAttribute("useer");

        ArrayList<News> news = DBConnector.getNews();
        req.setAttribute("newss",news);

        if (currentUser != null) {
            req.getRequestDispatcher("/home_page.jsp").forward(req, resp);
        } else resp.sendRedirect("/login");

    }
}