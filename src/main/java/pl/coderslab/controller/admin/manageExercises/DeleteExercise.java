package pl.coderslab.controller.admin.manageExercises;

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
            ExerciseDao.delete(id);
            response.sendRedirect("/displayExercises");
        } else {
            response.sendRedirect("/displayExercises");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id!=null){
            int idToInt = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("exercise", ExerciseDao.read(idToInt));
            getServletContext().getRequestDispatcher("/Admin/deleteExercise.jsp")
                    .forward(request, response);
        }else {
            response.sendRedirect("/displayExercises?error=Nie+odnaleziono+zadania!");
        }

    }
}
