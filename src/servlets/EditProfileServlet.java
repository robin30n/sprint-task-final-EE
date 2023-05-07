package servlets;

import com.mysql.cj.xdevapi.Session;
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

@WebServlet(value = "/edit")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/edit_profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users users = new Users();
        String password1 = req.getParameter("newPassword");
        String password2 = req.getParameter("secNewPassword");

        HttpSession session = req.getSession();
        Users currentUser = (Users) session.getAttribute("useer");

        if (password1.equals(password2)){
            users.setId(currentUser.getId());
            users.setFullName(req.getParameter("FullName"));
            users.setPassword(password1);
            users.setEmail(currentUser.getEmail());
            DBConnector.updateUser(users);
            resp.sendRedirect("/edit?done");
        }
        else resp.sendRedirect("/edit?notSame");
    }
}
