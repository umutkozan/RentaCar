package Business;

import DAO.CarDao;
import Entity.Car;

import java.util.List;

public class CarManager {
    private final CarDao carDao;

    public CarManager() {
        this.carDao = new CarDao();
    }

    public List<Car> findAvailableCars() {
        return carDao.findAvailableCars();
    }

    public boolean saveCar(Car car) {
        return carDao.save(car);
    }

    public boolean updateCar(Car car) {
        return carDao.update(car);
    }

    public boolean deleteCar(int id) {
        return carDao.delete(id);
    }

    public boolean deleteCarWithReservations(int id) {
        return carDao.deleteCarWithReservations(id);
    }

    public Car findCarById(int id) {
        return carDao.findById(id);
    }

    public List<Car> findAllCars() {
        return carDao.findAllCars();
    }

    public List<Car> findCarsByCriteria(String model, String brand, String startDate, String endDate) {
        return carDao.findCarsByCriteria(model, brand, startDate, endDate);
    }
}
