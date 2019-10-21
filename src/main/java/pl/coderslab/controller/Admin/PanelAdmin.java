package pl.coderslab.controller.Admin;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/panelAdmin")
public class PanelAdmin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/Admin/panelAdmin.jsp")
                .forward(request, response);
    }
}
