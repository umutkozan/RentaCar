package DAO;

import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final Connection con;

    public UserDao() {
        this.con = ConnectionDB.getInstance();
    }

    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_pass = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_pass"),
                        rs.getString("user_role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Diğer User DAO metotları
}
