package pl.coderslab.controller.Admin.ManageGroups;

import pl.coderslab.dao.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteGroup")
public class DeleteGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("confirm");
        int id = Integer.parseInt(request.getParameter("id"));
        if (param != null) {
            GroupDao groupDao = new GroupDao();
            groupDao.delete(id);
            response.sendRedirect("displayGroups");
        } else {
            response.sendRedirect("/displayGroups");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        GroupDao groupDao = new GroupDao();

        request.setAttribute("group", groupDao.read(id));

        getServletContext().getRequestDispatcher("/Admin/deleteGroup.jsp")
                .forward(request, response);
    }
}
