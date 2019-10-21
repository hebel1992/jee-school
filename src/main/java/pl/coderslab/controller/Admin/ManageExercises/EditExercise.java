package pl.coderslab.controller.Admin.ManageExercises;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editExercise")
public class EditExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newTitle = request.getParameter("newTitle");
        String newDescription = request.getParameter("newDescription");

        int id = Integer.parseInt(request.getParameter("id"));

        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exercise = new Exercise(newTitle, newDescription);
        exercise.setId(id);
        exerciseDao.update(exercise);

        response.sendRedirect("/displayExercises");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ExerciseDao exerciseDao = new ExerciseDao();

        request.setAttribute("exercise", exerciseDao.read(id));

        getServletContext().getRequestDispatcher("/Admin/editExercise.jsp")
                .forward(request, response);
    }
}
