package pl.coderslab.controller.admin.manageExercises;

import org.apache.commons.lang3.StringUtils;
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
        String id = request.getParameter("id");

        if (StringUtils.isNumeric(id)) {
            Exercise exercise = new Exercise(newTitle, newDescription);
            exercise.setId(Integer.parseInt(id));
            ExerciseDao.update(exercise);

            response.sendRedirect("/displayExercises");
        }else{
            response.sendRedirect("/displayExercises?Edycja+zakonczona+niepowodzeniem!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            int idToInt = Integer.parseInt(id);

            request.setAttribute("exercise", ExerciseDao.read(idToInt));

            getServletContext().getRequestDispatcher("/Admin/editExercise.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/displayExercises?error=Nie+odnaleziono+zadania!");
        }
    }
}
