package Entity;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int carId;
    private String customerName;
    private String customerIdNo;
    private String customerPhone;
    private String customerEmail;
    private LocalDate startDate;
    private LocalDate endDate;
    private int days; // Kiralama süresi (gün)
    private double dailyRate; // Günlük ücret
    private double price;
    private String note;
    private String status;

    // Boş Constructor
    public Reservation(int id, int carId, String customerName, String customerIdNo, String customerPhone, String customerEmail, String startDate, String endDate, int days, double dailyRate, double price, String note, String status) {}

    // Tüm Alanları İçeren Constructor
    public Reservation(int id, int carId, String customerName, String customerIdNo, String customerPhone, String customerEmail, LocalDate startDate, LocalDate endDate, int days, double dailyRate, double price, String note, String status) {
        this.id = id;
        this.carId = carId;
        this.customerName = customerName;
        this.customerIdNo = customerIdNo;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.dailyRate = dailyRate;
        this.price = price;
        this.note = note;
        this.status = status;
    }

    // Getter ve Setter Metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
