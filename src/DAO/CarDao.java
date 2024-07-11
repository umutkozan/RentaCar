package DAO;

import Entity.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private final Connection con;

    public CarDao() {
        this.con = ConnectionDB.getInstance();
    }

    public List<Car> findAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT c.*, m.model_name, b.brand_name FROM car c " +
                "JOIN model m ON c.car_model_id = m.model_id " +
                "JOIN brand b ON m.model_brand_id = b.brand_id " +
                "WHERE c.is_available = TRUE";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("car_id"),
                        rs.getInt("car_model_id"),
                        rs.getString("car_plate"),
                        rs.getString("model_name"),
                        rs.getString("brand_name"),
                        rs.getString("car_color"),
                        rs.getInt("car_km"),
                        rs.getBoolean("is_available")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public boolean save(Car car) {
        String query = "INSERT INTO car (car_model_id, car_plate, car_color, car_km, is_available) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, car.getModelId());
            pr.setString(2, car.getPlate());
            pr.setString(3, car.getColor());
            pr.setInt(4, car.getKilometers());
            pr.setBoolean(5, car.isAvailable());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Car car) {
        String query = "UPDATE car SET car_model_id = ?, car_plate = ?, car_color = ?, car_km = ?, is_available = ? WHERE car_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, car.getModelId());
            pr.setString(2, car.getPlate());
            pr.setString(3, car.getColor());
            pr.setInt(4, car.getKilometers());
            pr.setBoolean(5, car.isAvailable());
            pr.setInt(6, car.getId());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM car WHERE car_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCarWithReservations(int id) {
        // First, delete all reservations related to the car
        String deleteReservationsQuery = "DELETE FROM reservation WHERE car_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(deleteReservationsQuery);
            pr.setInt(1, id);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        // Then, delete the car itself
        return delete(id);
    }

    public Car findById(int id) {
        String query = "SELECT c.*, m.model_name, b.brand_name FROM car c " +
                "JOIN model m ON c.car_model_id = m.model_id " +
                "JOIN brand b ON m.model_brand_id = b.brand_id " +
                "WHERE c.car_id = ?";
        Car car = null;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                car = new Car(
                        rs.getInt("car_id"),
                        rs.getInt("car_model_id"),
                        rs.getString("car_plate"),
                        rs.getString("model_name"),
                        rs.getString("brand_name"),
                        rs.getString("car_color"),
                        rs.getInt("car_km"),
                        rs.getBoolean("is_available")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    public List<Car> findAllCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT c.*, m.model_name, b.brand_name FROM car c " +
                "JOIN model m ON c.car_model_id = m.model_id " +
                "JOIN brand b ON m.model_brand_id = b.brand_id";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("car_id"),
                        rs.getInt("car_model_id"),
                        rs.getString("car_plate"),
                        rs.getString("model_name"),
                        rs.getString("brand_name"),
                        rs.getString("car_color"),
                        rs.getInt("car_km"),
                        rs.getBoolean("is_available")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public List<Car> findCarsByCriteria(String model, String brand, String startDate, String endDate) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT c.*, m.model_name, b.brand_name FROM car c " +
                "JOIN model m ON c.car_model_id = m.model_id " +
                "JOIN brand b ON m.model_brand_id = b.brand_id " +
                "WHERE m.model_name = ? AND b.brand_name = ? AND c.is_available = TRUE";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, model);
            pr.setString(2, brand);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("car_id"),
                        rs.getInt("car_model_id"),
                        rs.getString("car_plate"),
                        rs.getString("model_name"),
                        rs.getString("brand_name"),
                        rs.getString("car_color"),
                        rs.getInt("car_km"),
                        rs.getBoolean("is_available")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }
}
