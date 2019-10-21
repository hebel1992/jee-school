package pl.coderslab.controller.Admin.ManageUsers;

import pl.coderslab.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/displayUsers")
public class DisplayUsers extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        request.setAttribute("users", userDao.findAll());

        getServletContext().getRequestDispatcher("/Admin/displayUsers.jsp")
                .forward(request, response);
    }
}
