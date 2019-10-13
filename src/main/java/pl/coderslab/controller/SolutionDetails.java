package pl.coderslab.controller;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solutionDetails")
public class SolutionDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("solutionId");
        int solutionId = Integer.parseInt(param);

        SolutionDao solutionDao = new SolutionDao();
        Solution solution = solutionDao.read(solutionId);

        response.getWriter()
                .append(solution.getDescription());
    }
}
