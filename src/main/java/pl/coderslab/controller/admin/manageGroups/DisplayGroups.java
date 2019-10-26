package pl.coderslab.controller.admin.manageGroups;

import pl.coderslab.dao.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/displayGroups")
public class DisplayGroups extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("groups", GroupDao.findAll());

        getServletContext().getRequestDispatcher("/Admin/displayGroups.jsp")
                .forward(request, response);
    }
}
