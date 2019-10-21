package pl.coderslab.controller.Admin.ManageUsers;

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

@WebServlet("/addUser")
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        User user = new User(name, email, password, groupId);
        UserDao userDao = new UserDao();

        if(groupId!=0){
            userDao.create(user);
        }else{
            userDao.createWithoutGroup(user);
        }

        response.sendRedirect("/displayUsers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDao groupDao = new GroupDao();
        request.setAttribute("groups", groupDao.findAll());

        getServletContext().getRequestDispatcher("/Admin/addUser.jsp")
                .forward(request, response);
    }
}
