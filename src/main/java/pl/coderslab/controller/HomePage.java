package pl.coderslab.controller;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomePage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        int limit = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        List<Solution> recentSolutions = solutionDao.findRecent(limit);

        request.setAttribute("recent", recentSolutions);

        getServletContext().getRequestDispatcher("/index1.jsp")
                .forward(request, response);
    }
}
