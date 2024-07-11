package DAO;

import Entity.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDao {
    private final Connection con;

    public BrandDao() {
        this.con = ConnectionDB.getInstance();
    }

    // Tüm Brand'leri getir
    public List<Brand> findAllBrands() {
        List<Brand> brands = new ArrayList<>();
        String query = "SELECT * FROM public.brand";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand(
                        rs.getInt("brand_id"),
                        rs.getString("brand_name")
                );
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brands;
    }

    // Yeni Brand ekle
    public boolean save(Brand brand) {
        String query = "INSERT INTO public.brand (brand_name) VALUES (?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, brand.getName());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Brand güncelle
    public boolean update(Brand brand) {
        String query = "UPDATE public.brand SET brand_name = ? WHERE brand_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, brand.getName());
            pr.setInt(2, brand.getId());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Brand sil
    public boolean delete(int id) {
        String query = "DELETE FROM public.brand WHERE brand_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Brand ID ile getir
    public Brand findById(int id) {
        String query = "SELECT * FROM public.brand WHERE brand_id = ?";
        Brand brand = null;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                brand = new Brand(
                        rs.getInt("brand_id"),
                        rs.getString("brand_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brand;
    }
}
