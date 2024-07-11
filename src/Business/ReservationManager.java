package Business;

import DAO.ReservationDao;
import Entity.Reservation;

import java.util.List;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public List<Reservation> findAllReservations() {
        return reservationDao.findAllReservations();
    }

    public boolean saveReservation(Reservation reservation) {
        return reservationDao.save(reservation);
    }

    public boolean updateReservation(Reservation reservation) {
        return reservationDao.update(reservation);
    }

    public boolean deleteReservation(int id) {
        return reservationDao.delete(id);
    }

    public List<Reservation> findReservationsByCarId(int carId) {
        return reservationDao.findReservationsByCarId(carId);
    }

    public boolean deleteReservationsByCarId(int carId) {
        return reservationDao.deleteReservationsByCarId(carId);
    }

    public List<Reservation> findReservationsByDateRange(String startDate, String endDate) {
        return reservationDao.findReservationsByDateRange(startDate, endDate);
    }

    public Reservation findReservationById(int id) {
        return this.reservationDao.findById(id);
    }
}
