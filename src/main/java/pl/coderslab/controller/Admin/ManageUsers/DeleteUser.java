package pl.coderslab.controller.Admin.ManageUsers;

import pl.coderslab.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("confirm");
        int id = Integer.parseInt(request.getParameter("id"));
        if (param != null) {
            UserDao userDao = new UserDao();
            userDao.delete(id);
            response.sendRedirect("displayUsers");
        } else {
            response.sendRedirect("/displayUsers");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDao();

        request.setAttribute("user", userDao.read(id));

        getServletContext().getRequestDispatcher("/Admin/deleteUser.jsp")
                .forward(request, response);
    }
}
