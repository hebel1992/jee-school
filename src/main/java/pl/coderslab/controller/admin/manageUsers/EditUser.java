package pl.coderslab.controller.admin.manageUsers;

import org.apache.commons.lang3.StringUtils;
import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editUser")
public class EditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("newName");
        String newEmail = request.getParameter("newEmail");
        String newPassword = request.getParameter("newPassword");
        String groupId = request.getParameter("newGroupId");

        String userId = request.getParameter("userId");

        if (StringUtils.isNumeric(groupId) && StringUtils.isNumeric(userId)) {
            if (Integer.parseInt(groupId) != 0) {
                User user = new User(newName, newEmail, newPassword, Integer.parseInt(groupId));
                user.setId(Integer.parseInt(userId));
                UserDao.update(user);
            } else {
                User user = new User(newName, newEmail, newPassword);
                user.setId(Integer.parseInt(userId));
                UserDao.update(user);
            }

            response.sendRedirect("/displayUsers");
        }else {
            response.sendRedirect("/displayUsers?error=Edycja+uzytkownika+zakonczona+niepowodzeniem");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            int idToInt = Integer.parseInt(id);

            request.setAttribute("user", UserDao.read(idToInt));

            request.setAttribute("groups", GroupDao.findAll());

            getServletContext().getRequestDispatcher("/Admin/editUser.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/displayUsers?error=Nie+odnaleziono+uzytkownika");
        }

    }
}
