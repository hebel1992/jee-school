package pl.coderslab.dao;

import pl.coderslab.utils.DBUtil;
import pl.coderslab.models.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {
    private static final String CREATE_QUERY = "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM user_group WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE user_group SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM user_group";

    public static Group create(Group group) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, group.getName());
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                group.setId(rs.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna stworzyc wpisu o nazwie:" + group.getName());
            return null;
        }
    }

    public static Group read(int id) {
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
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                return group;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wiersza o id: " + id);
            return null;
        }
    }

    public static void update(Group group) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(UPDATE_QUERY);
            preStmt.setString(1, group.getName());
            preStmt.setInt(2, group.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna zaktualzowac wiersza o id: " + group.getId());
        }
    }

    public static void delete(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(DELETE_QUERY);
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna usunac wiersza z tablicy o id: " + id);
        }

    }

    public static List<Group> findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            List<Group> groups = new ArrayList<>();
            PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                groups.add(group);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie mozna wczytac wszystkich wierszy z tabeli user_group");
            return null;
        }
    }
}

