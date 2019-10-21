package pl.coderslab.controller.Admin.ManageExercises;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addExercise")
public class AddExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Exercise exercise = new Exercise(name, description);
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.create(exercise);

        response.sendRedirect("/displayExercises");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Admin/addExercise.jsp")
                .forward(request, response);
    }
}
