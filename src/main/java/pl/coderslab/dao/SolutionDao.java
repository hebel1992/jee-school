package pl.coderslab.dao;

import pl.coderslab.Utils.DBUtil;
import pl.coderslab.models.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    private static final String CREATE_QUERY = "INSERT INTO solution (description, exercise_id, user_id) VALUES (?, ?, ?)";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM solution WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE solution SET updated = CURRENT_TIMESTAMP, description = ?, exercise_id = ?, user_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM solution";
    private static final String FIND_ALL_BY_USER_ID_QUERY = "SELECT * FROM solution WHERE user_id = ?";
    private static final String FIND_ALL_BY_EXERCISE_ID_QUERY = "SELECT * FROM solution WHERE exercise_id=? ORDER BY created";
    private static final String FIND_RECENT_QUERY = "SELECT * FROM solution ORDER BY solution.created DESC LIMIT ?";

    public Solution create(Solution solution) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, solution.getDescription());
            preStmt.setInt(2, solution.getExercise_id());
            preStmt.setInt(3, solution.getUser_id());
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                solution.setId(rs.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna stworzyc wpisu o danych:" + solution.getDescription() + ", " + solution.getExercise_id() + ", " + solution.getUser_id());
            return null;
        }
    }

    public Solution read(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(READ_BY_ID_QUERY);
            preStmt.setInt(1, id);
            boolean exist = false;
            for (int i = 0; i < findAll().size(); i++) {
                if (findAll().get(i).getId() == id) {
                    exist = true;
                }
            }
            if (exist == false) {
                return null;
            }
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUser_id(rs.getInt("user_id"));
                solution.setCreated(rs.getTimestamp("created"));
                solution.setUpdated(rs.getTimestamp("updated"));
                solution.setDescription(rs.getString("description"));
                return solution;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wiersza o id: " + id);
            return null;
        }
    }

    public void update(Solution solution) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(UPDATE_QUERY);
            preStmt.setString(1, solution.getDescription());
            preStmt.setInt(2, solution.getExercise_id());
            preStmt.setInt(3, solution.getUser_id());
            preStmt.setInt(4, solution.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie mozna zaktualzowac wiersza o id: " + solution.getId());
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(DELETE_QUERY);
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie mozna usunac wiersza z tablicy o id: " + id);
            e.printStackTrace();
        }

    }

    public List<Solution> findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUser_id(rs.getInt("user_id"));
                solution.setCreated(rs.getTimestamp("created"));
                solution.setUpdated(rs.getTimestamp("updated"));
                solution.setDescription(rs.getString("description"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wszystkich wierszy z tabeli solution");
            return null;
        }
    }

    public List<Solution> findAllByUserId(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_BY_USER_ID_QUERY);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUser_id(rs.getInt("user_id"));
                solution.setCreated(rs.getTimestamp("created"));
                solution.setUpdated(rs.getTimestamp("updated"));
                solution.setDescription(rs.getString("description"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wierszy z tabeli solution dla uzytkownika o id: " + id);
            return null;
        }
    }

    public List<Solution> findAllByExerciseId(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_BY_EXERCISE_ID_QUERY);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUser_id(rs.getInt("user_id"));
                solution.setCreated(rs.getTimestamp("created"));
                solution.setUpdated(rs.getTimestamp("updated"));
                solution.setDescription(rs.getString("description"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wierszy z tabeli solution dla zadania o id: " + id);
            return null;
        }
    }

    public List<Solution> findRecent(int limit) {
        try (Connection conn = DBUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement preStmt = conn.prepareStatement(FIND_RECENT_QUERY);
            preStmt.setInt(1, limit);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUser_id(rs.getInt("user_id"));
                solution.setCreated(rs.getTimestamp("created"));
                solution.setUpdated(rs.getTimestamp("updated"));
                solution.setDescription(rs.getString("description"));

                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
