package servlets;

import db.DBConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.Users;

import java.io.IOException;

@WebServlet(value = "/sign-in")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/sign_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String secPassword = req.getParameter("secPassword");
        Users user = DBConnector.getUser(email);

        if(!password.equals(secPassword)){
            resp.sendRedirect("sign-in?notSame");
        }
        else if(user!=null){
            resp.sendRedirect("sign-in?alreadyRegistered");
        }
        else {
            user = new Users(email,password,fullName);
            DBConnector.addUser(user);
            resp.sendRedirect("login");
        }
    }
}
