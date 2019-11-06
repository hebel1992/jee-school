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

        if (param != null) {
            int groupId = Integer.parseInt(param);
            String groupName = GroupDao.read(groupId).getName();

            List<User> usersOfGroup = UserDao.findAllByGroupId(groupId);

            request.setAttribute("groupName", groupName);
            request.setAttribute("users", usersOfGroup);
            request.setAttribute("size", usersOfGroup.size());
        }

        getServletContext().getRequestDispatcher("/usersOfGroup.jsp")
                .forward(request, response);
    }
}
