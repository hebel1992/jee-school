package pl.coderslab.controller.Admin.ManageExercises;

import pl.coderslab.dao.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteExercise")
public class DeleteExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("confirm");
        int id = Integer.parseInt(request.getParameter("id"));

        if (param != null) {
            ExerciseDao exerciseDao = new ExerciseDao();
            exerciseDao.delete(id);
            response.sendRedirect("/displayExercises");
        } else {
            response.sendRedirect("/displayExercises");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ExerciseDao exerciseDao = new ExerciseDao();

        request.setAttribute("exercise", exerciseDao.read(id));

        getServletContext().getRequestDispatcher("/Admin/deleteExercise.jsp")
                .forward(request, response);
    }
}
