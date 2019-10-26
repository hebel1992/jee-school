package pl.coderslab.controller.admin.manageExercises;

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

        Exercise exercise = new Exercise(newTitle, newDescription);
        exercise.setId(id);
        ExerciseDao.update(exercise);

        response.sendRedirect("/displayExercises");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("exercise", ExerciseDao.read(id));

        getServletContext().getRequestDispatcher("/Admin/editExercise.jsp")
                .forward(request, response);
    }
}
