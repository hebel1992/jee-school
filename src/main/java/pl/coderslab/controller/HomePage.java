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
        int limit = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));

        List<Solution> recentSolutions = getRecentSolutions(limit);

        request.setAttribute("recent", recentSolutions);

        getServletContext().getRequestDispatcher("/index1.jsp")
                .forward(request, response);
    }

    private List<Solution> getRecentSolutions(int limit) {
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();

        List<Solution> recentSolutions = solutionDao.findRecent(limit);
        List<User> recentSolutionsUser = userDao.findAll();
        List<Exercise> recentSolutionsExercise = exerciseDao.findAll();
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
