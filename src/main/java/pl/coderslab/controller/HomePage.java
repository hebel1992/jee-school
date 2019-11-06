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

@WebServlet("/")
public class HomePage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String initParam = getServletContext().getInitParameter("number-solutions");

        if (initParam != null) {
            int limit = Integer.parseInt(initParam);

            List<Solution> recentSolutions = getRecentSolutions(limit);

            request.setAttribute("recent", recentSolutions);
        }

        getServletContext().getRequestDispatcher("/index1.jsp")
                .forward(request, response);
    }

    private List<Solution> getRecentSolutions(int limit) {
        List<Solution> recentSolutions = SolutionDao.findRecent(limit);
        List<User> recentSolutionsUser = UserDao.findAll();
        List<Exercise> recentSolutionsExercise = ExerciseDao.findAll();
        for (Solution s : recentSolutions) {
            for (User u : recentSolutionsUser) {
                if (s.getUser_id() == u.getId()) {
                    s.setUsername(u.getUsername());
                }
            }
            for (Exercise e : recentSolutionsExercise) {
                if (s.getExercise_id() == e.getId()) {
                    s.setExerciseTitle(e.getTitle());
                }
            }
        }
        return recentSolutions;
    }
}
