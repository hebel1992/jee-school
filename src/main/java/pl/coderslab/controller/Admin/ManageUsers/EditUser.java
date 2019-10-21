package pl.coderslab.controller.Admin.ManageUsers;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editUser")
public class EditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("newName");
        String newEmail = request.getParameter("newEmail");
        String newPassword = request.getParameter("newPassword");
        int groupId = Integer.parseInt(request.getParameter("newGroupId"));

        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDao userDao = new UserDao();

        if (groupId != 0) {
            User user = new User(newName, newEmail, newPassword, groupId);
            user.setId(userId);
            userDao.update(user);
        } else {
            User user = new User(newName, newEmail, newPassword);
            user.setId(userId);
            userDao.update(user);
        }

        response.sendRedirect("/displayUsers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));

        UserDao userDao = new UserDao();
        request.setAttribute("user", userDao.read(userId));

        GroupDao groupDao = new GroupDao();
        request.setAttribute("groups", groupDao.findAll());

        getServletContext().getRequestDispatcher("/Admin/editUser.jsp")
                .forward(request, response);
    }
}