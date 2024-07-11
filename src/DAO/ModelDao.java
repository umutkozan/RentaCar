package DAO;

import Entity.Model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelDao {
    private final Connection con;

    public ModelDao() {
        this.con = ConnectionDB.getInstance();
    }

    public List<Model> findAllModels() {
        List<Model> models = new ArrayList<>();
        String query = "SELECT m.*, b.brand_name as brand_name FROM public.model m JOIN public.brand b ON m.model_brand_id = b.brand_id";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Model model = new Model(
                        rs.getInt("model_id"),
                        rs.getInt("model_brand_id"),
                        rs.getString("model_name"),
                        rs.getString("model_type"),
                        rs.getString("model_year"),
                        rs.getString("model_fuel"),
                        rs.getString("model_gear"),
                        rs.getString("brand_name")
                );
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }

        return models;
    }

    public boolean save(Model model) {
        String query = "INSERT INTO public.model (model_brand_id, model_name, model_type, model_year, model_fuel, model_gear) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, model.getBrandId());
            pr.setString(2, model.getName());
            pr.setString(3, model.getType());
            pr.setString(4, model.getYear());
            pr.setString(5, model.getFuel());
            pr.setString(6, model.getGear());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }

        return false;
    }

    public boolean update(Model model) {
        String query = "UPDATE public.model SET model_brand_id = ?, model_name = ?, model_type = ?, model_year = ?, model_fuel = ?, model_gear = ? WHERE model_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, model.getBrandId());
            pr.setString(2, model.getName());
            pr.setString(3, model.getType());
            pr.setString(4, model.getYear());
            pr.setString(5, model.getFuel());
            pr.setString(6, model.getGear());
            pr.setInt(7, model.getId());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }

        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.model WHERE model_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }

        return false;
    }

    public Model findById(int id) {
        String query = "SELECT m.*, b.brand_name as brand_name FROM public.model m JOIN public.brand b ON m.model_brand_id = b.brand_id WHERE model_id = ?";
        Model model = null;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                model = new Model(
                        rs.getInt("model_id"),
                        rs.getInt("model_brand_id"),
                        rs.getString("model_name"),
                        rs.getString("model_type"),
                        rs.getString("model_year"),
                        rs.getString("model_fuel"),
                        rs.getString("model_gear"),
                        rs.getString("brand_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
}
