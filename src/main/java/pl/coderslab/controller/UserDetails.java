package pl.coderslab.controller;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.models.Exercise;
import pl.coderslab.models.Solution;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userDetails")
public class UserDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");

        if (param != null) {
            int userId = Integer.parseInt(param);
            User user = UserDao.read(userId);
            List<Solution> solutions = getUserSolutions(userId);
            request.setAttribute("user", user);
            request.setAttribute("userSolutions", solutions);
            request.setAttribute("size", solutions.size());

            getServletContext().getRequestDispatcher("/userDetails.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/usersOfGroup?error=Nie+odnaleziono+uzytkownika!");
        }
    }

    private List<Solution> getUserSolutions(int userId) {
        List<Solution> solutions = SolutionDao.findAllByUserId(userId);

        for (Solution s : solutions) {
            int exerciseId = s.getExercise_id();
            Exercise exercise = ExerciseDao.read(exerciseId);
            s.setExerciseTitle(exercise.getTitle());
        }
        return solutions;
    }

}
