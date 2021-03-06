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
        if (param != null) {
            int solutionId = Integer.parseInt(param);

            Solution solution = SolutionDao.read(solutionId);

            response.getWriter()
                    .append(solution.getDescription());
        }else {
            response.getWriter()
                    .append("Nie odnaleziono rozwiazania!");
        }
    }
}
