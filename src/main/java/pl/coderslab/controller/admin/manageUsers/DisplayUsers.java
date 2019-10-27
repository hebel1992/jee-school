package pl.coderslab.controller.admin.manageUsers;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.models.Group;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/displayUsers")
public class DisplayUsers extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = UserDao.findAll();

        for (User u : users) {
            int groupId = u.getGroup_id();
            if (groupId != 0) {
                Group group = GroupDao.read(groupId);
                u.setGroup_name(group.getName());
            }
        }

        request.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/Admin/displayUsers.jsp")
                .forward(request, response);
    }
}
