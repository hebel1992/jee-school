package pl.coderslab.controller.admin.manageGroups;

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

        Group group = new Group(newName);
        group.setId(id);
        GroupDao.update(group);

        response.sendRedirect("/displayGroups");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            int idToInt = Integer.parseInt(id);
            request.setAttribute("group", GroupDao.read(idToInt));

            getServletContext().getRequestDispatcher("/Admin/editGroup.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/displayGroups?error=Nie+odnaleziono+grupy!");
        }
    }
}
