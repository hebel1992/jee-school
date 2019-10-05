package pl.coderslab;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.models.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/testServlet")
public class testServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Group userGroup = new Group("group1");

        GroupDao groupDao = new GroupDao();
        groupDao.create(userGroup);

        List<Group> all = Arrays.asList(groupDao.findAll());
        response.getWriter().println(all);
    }
}
