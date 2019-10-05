package pl.coderslab.dao;

import pl.coderslab.models.User;
import pl.coderslab.Utils.DBUtil;

import java.sql.*;
import java.util.Arrays;


public class UserDao {
    private static final String CREATE_QUERY = "INSERT INTO users (username, email, password, group_id) VALUES (?, ?, ? ,?)";
    private static final String CREATE_WITHOUT_GROUP_QUERY = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE users SET username=?, email=?, password=?, group_id=? WHERE id=? ";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users";
    private static final String FIND_ALL_BY_GROUP_ID_QUERY = "SELECT * FROM users WHERE group_id = ?";

    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, user.getUsername());
            preStmt.setString(2, user.getEmail());
            preStmt.setString(3, user.getPassword());
            preStmt.setInt(4, user.getGroup_id());
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna stworzyc wpisu o danych:" + user.getUsername() + ", " + user.getEmail() + ", " + user.getPassword() + ", " + user.getGroup_id());
            return null;
        }
    }

    public User createWithoutGroup(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(CREATE_WITHOUT_GROUP_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, user.getUsername());
            preStmt.setString(2, user.getEmail());
            preStmt.setString(3, user.getPassword());
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna stworzyc wpisu o danych:" + user.getUsername() + ", " + user.getEmail() + ", " + user.getPassword());
            return null;
        }
    }

    public User read(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            User user = new User();
            PreparedStatement preStmt = conn.prepareStatement(READ_BY_ID_QUERY);
            preStmt.setInt(1, id);
            boolean exist = false;
            for (int i = 0; i < findAll().length; i++) {
                if (findAll()[i].getId() == id) {
                    exist = true;
                }
            }
            if (exist == false) {
                return null;
            }
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGroup_id(rs.getInt("group_id"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wiersza o id: " + id);
            return null;
        }
    }

    public void update(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(UPDATE_QUERY);
            preStmt.setString(1, user.getUsername());
            preStmt.setString(2, user.getEmail());
            preStmt.setString(3, user.getPassword());
            if (user.getGroup_id() == 0) {
                preStmt.setNull(4, Types.INTEGER);
            } else {
                preStmt.setInt(4, user.getGroup_id());
            }
            preStmt.setInt(5, user.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna zaktualzowac wiersza o id: " + user.getId());
        }
    }

    public void delete(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(DELETE_QUERY);
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna usunac wiersza z tablicy o id: " + id);
        }
    }

    public User[] findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setGroup_id(rs.getInt("group_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wszystkich wierszy z tabeli users");
            return null;
        }
    }

    public User[] findAllByGroupId(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_BY_GROUP_ID_QUERY);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setGroup_id(rs.getInt("group_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wszystkich wierszy dla id grupy: " + id);
            return null;
        }
    }

    private User[] addToArray(User user, User[] users) {
        User[] tmp = Arrays.copyOf(users, users.length + 1);
        tmp[users.length] = user;
        return tmp;
    }
}
