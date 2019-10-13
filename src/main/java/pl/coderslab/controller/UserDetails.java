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
        int userId = Integer.parseInt(param);

        UserDao userDao = new UserDao();
        User user = userDao.read(userId);

        List<Solution> solutions = getUserSolutions(userId);

        request.setAttribute("user", user);
        request.setAttribute("userSolutions", solutions);

        getServletContext().getRequestDispatcher("/userDetails.jsp")
                .forward(request, response);
    }

    private List<Solution> getUserSolutions(int userId) {
        SolutionDao solutionDao = new SolutionDao();
        List<Solution> solutions = solutionDao.findAllByUserId(userId);

        ExerciseDao exerciseDao = new ExerciseDao();
        List<Exercise> exercises = exerciseDao.findAll();

        for (Solution s : solutions) {
            for (Exercise e : exercises) {
                if (e.getId() == s.getExercise_id()) {
                    s.setExerciseTitle(e.getTitle());
                }
            }
        }
        return solutions;
    }

}
