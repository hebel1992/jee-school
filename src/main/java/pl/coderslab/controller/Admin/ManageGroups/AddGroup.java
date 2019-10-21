package pl.coderslab.controller.Admin.ManageGroups;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.models.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addGroup")
public class AddGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Group group = new Group(request.getParameter("name"));
        GroupDao groupDao = new GroupDao();
        groupDao.create(group);

        response.sendRedirect("/displayGroups");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Admin/addGroup.jsp")
                .forward(request, response);
    }
}
