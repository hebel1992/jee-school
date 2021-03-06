package pl.coderslab.controller.admin.manageExercises;

import pl.coderslab.dao.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/displayExercises")
public class DisplayExercises extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("exercises", ExerciseDao.findAll());

        getServletContext().getRequestDispatcher("/Admin/displayExercises.jsp")
                .forward(request, response);
    }
}
