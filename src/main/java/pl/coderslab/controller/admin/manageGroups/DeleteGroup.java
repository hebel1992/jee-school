package pl.coderslab.controller.admin.manageGroups;

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
            GroupDao.delete(id);
            response.sendRedirect("displayGroups");
        } else {
            response.sendRedirect("/displayGroups");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            int idToInt = Integer.parseInt(id);

            request.setAttribute("group", GroupDao.read(idToInt));

            getServletContext().getRequestDispatcher("/Admin/deleteGroup.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/displayGroups?error=Nie+odnaleziono+grupy!");
        }
    }
}
