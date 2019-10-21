package pl.coderslab.controller.Admin.ManageGroups;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.models.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editGroup")
public class EditGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("newName");
        int id = Integer.parseInt(request.getParameter("id"));

        GroupDao groupDao = new GroupDao();
        Group group = new Group(newName);
        group.setId(id);
        groupDao.update(group);

        response.sendRedirect("/displayGroups");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        GroupDao groupDao = new GroupDao();

        request.setAttribute("group", groupDao.read(id));

        getServletContext().getRequestDispatcher("/Admin/editGroup.jsp")
                .forward(request, response);
    }
}
