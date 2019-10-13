package pl.coderslab.controller;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/usersOfGroup")
public class UsersOfGroup extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        int groupId = Integer.parseInt(param);

        UserDao userDao = new UserDao();
        GroupDao groupDao = new GroupDao();
        String groupName = groupDao.read(groupId).getName();

        List<User> usersOfGroup = userDao.findAllByGroupId(groupId);

        request.setAttribute("groupName", groupName);
        request.setAttribute("users", usersOfGroup);

        getServletContext().getRequestDispatcher("/usersOfGroup.jsp")
                .forward(request, response);
    }
}
