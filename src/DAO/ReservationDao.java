package DAO;

import Entity.Reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {
    private final Connection con;

    public ReservationDao() {
        this.con = ConnectionDB.getInstance();
    }

    public List<Reservation> findAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_id_no"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("days"),
                        rs.getDouble("daily_rate"),
                        rs.getDouble("price"),
                        rs.getString("note"),
                        rs.getString("status")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation (car_id, customer_name, customer_id_no, customer_phone, customer_email, start_date, end_date, days, daily_rate, price, note, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getCarId());
            pr.setString(2, reservation.getCustomerName());
            pr.setString(3, reservation.getCustomerIdNo());
            pr.setString(4, reservation.getCustomerPhone());
            pr.setString(5, reservation.getCustomerEmail());
            pr.setDate(6, Date.valueOf(reservation.getStartDate()));
            pr.setDate(7, Date.valueOf(reservation.getEndDate()));
            pr.setInt(8, reservation.getDays());
            pr.setDouble(9, reservation.getDailyRate());
            pr.setDouble(10, reservation.getPrice());
            pr.setString(11, reservation.getNote());
            pr.setString(12, reservation.getStatus());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET car_id = ?, customer_name = ?, customer_id_no = ?, customer_phone = ?, customer_email = ?, start_date = ?, end_date = ?, days = ?, daily_rate = ?, price = ?, note = ?, status = ? WHERE id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getCarId());
            pr.setString(2, reservation.getCustomerName());
            pr.setString(3, reservation.getCustomerIdNo());
            pr.setString(4, reservation.getCustomerPhone());
            pr.setString(5, reservation.getCustomerEmail());
            pr.setDate(6, Date.valueOf(reservation.getStartDate()));
            pr.setDate(7, Date.valueOf(reservation.getEndDate()));
            pr.setInt(8, reservation.getDays());
            pr.setDouble(9, reservation.getDailyRate());
            pr.setDouble(10, reservation.getPrice());
            pr.setString(11, reservation.getNote());
            pr.setString(12, reservation.getStatus());
            pr.setInt(13, reservation.getId());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.reservation WHERE id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Reservation> findReservationsByCarId(int carId) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation WHERE car_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, carId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_id_no"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("days"),
                        rs.getDouble("daily_rate"),
                        rs.getDouble("price"),
                        rs.getString("note"),
                        rs.getString("status")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public boolean deleteReservationsByCarId(int carId) {
        String query = "DELETE FROM public.reservation WHERE car_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, carId);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Reservation> findReservationsByDateRange(String startDate, String endDate) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation WHERE start_date BETWEEN ? AND ? OR end_date BETWEEN ? AND ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setDate(1, Date.valueOf(startDate));
            pr.setDate(2, Date.valueOf(endDate));
            pr.setDate(3, Date.valueOf(startDate));
            pr.setDate(4, Date.valueOf(endDate));
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_id_no"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("days"),
                        rs.getDouble("daily_rate"),
                        rs.getDouble("price"),
                        rs.getString("note"),
                        rs.getString("status")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public Reservation findById(int id) {
        String query = "SELECT * FROM public.reservation WHERE id = ?";
        Reservation reservation = null;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                reservation = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_id_no"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("days"),
                        rs.getDouble("daily_rate"),
                        rs.getDouble("price"),
                        rs.getString("note"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }
}
