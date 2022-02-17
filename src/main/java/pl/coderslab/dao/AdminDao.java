package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO " +
            "admins(first_name, last_name, email, password, superadmin, enable) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins where id = ?";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET" +
            " first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? where id = ?";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins";
    private static final String FIND_BY_EMAIL = "SELECT * FROM admins WHERE email = ? ;";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Admin create(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_ADMIN_QUERY,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashPassword(admin.getPassword()));
            statement.setInt(5, admin.getSuperadmin());
            statement.setInt(6, admin.getEnable());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int adminId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_ADMIN_QUERY);
            statement.setInt(1, adminId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_ADMIN_QUERY);
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, this.hashPassword(admin.getPassword()));
            statement.setInt(5, admin.getSuperadmin());
            statement.setInt(6, admin.getEnable());
            statement.setInt(7, admin.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin findByEmail(String email){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin read(int adminId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_ADMIN_QUERY);
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Admin> findAll() {
        List<Admin> adminsList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminsList.add(adminToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;
    }
}
