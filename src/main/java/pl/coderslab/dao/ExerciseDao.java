package pl.coderslab.dao;

import pl.coderslab.Utils.DBUtil;
import pl.coderslab.models.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {
    private static final String CREATE_QUERY = "INSERT INTO exercise(title, description) VALUES (?,?)";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM exercise WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM exercise WHERE id = ?";
    private static final String FINAD_ALL_QUERRY = "SELECT * FROM exercise";

    public Exercise create(Exercise exercise) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, exercise.getTitle());
            preStmt.setString(2, exercise.getDescription());
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                exercise.setId(rs.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna stworzyc wpisu o danych:" + exercise.getTitle() + ", " + exercise.getDescription());
            return null;
        }
    }

    public Exercise read(int id) {
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
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                return exercise;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wiersza o id: " + id);
            return null;
        }
    }

    public void update(Exercise exercise) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(UPDATE_QUERY);
            preStmt.setString(1, exercise.getTitle());
            preStmt.setString(2, exercise.getDescription());
            preStmt.setInt(3, exercise.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna zaktualzowac wiersza o id: " + exercise.getId());
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

    public List<Exercise> findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            List<Exercise> exercises = new ArrayList<>();
            PreparedStatement preStmt = conn.prepareStatement(FINAD_ALL_QUERRY);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                exercises.add(exercise);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wszystkich wierszy z tabeli exercise");
            return null;
        }
    }
}
