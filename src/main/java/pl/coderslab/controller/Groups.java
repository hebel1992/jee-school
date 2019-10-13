package pl.coderslab.controller;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.models.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/groups")
public class Groups extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDao groupDao = new GroupDao();
        List<Group> groups = groupDao.findAll();

        request.setAttribute("groups", groups);

        getServletContext().getRequestDispatcher("/groups.jsp")
                .forward(request, response);
    }
}
