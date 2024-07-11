package View;

import Business.ReservationManager;
import Business.CarManager;
import Entity.Car;
import Entity.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ReservationForm extends JDialog {
    private JTextField txtCustomerName, txtCustomerIdNo, txtCustomerPhone, txtCustomerEmail, txtStartDate, txtEndDate, txtDailyRate, txtPrice, txtNote, txtStatus;
    private JTextField txtDays;
    private JComboBox<Car> cmbCar;
    private JButton btnSave, btnCancel, btnCalculate;
    private ReservationManager reservationManager;
    private Reservation reservation;
    private CarManager carManager;

    public ReservationForm(Frame owner, String title, boolean modal, ReservationManager reservationManager, Reservation reservation, CarManager carManager) {
        super(owner, title, modal);
        this.reservationManager = reservationManager;
        this.reservation = reservation;
        this.carManager = carManager;
        initUI();
        if (reservation != null) {
            populateFields();
        }
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Araba:"), gbc);
        cmbCar = new JComboBox<>();
        List<Car> cars = carManager.findAllCars();
        for (Car car : cars) {
            cmbCar.addItem(car);
        }
        gbc.gridx = 1;
        panel.add(cmbCar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Müşteri Adı:"), gbc);
        txtCustomerName = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtCustomerName, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Kimlik No:"), gbc);
        txtCustomerIdNo = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtCustomerIdNo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Telefon:"), gbc);
        txtCustomerPhone = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtCustomerPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("E-posta:"), gbc);
        txtCustomerEmail = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtCustomerEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Başlangıç Tarihi (dd/MM/yyyy):"), gbc);
        txtStartDate = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtStartDate, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Bitiş Tarihi (dd/MM/yyyy):"), gbc);
        txtEndDate = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtEndDate, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Gün Sayısı:"), gbc);
        txtDays = new JTextField(20);
        txtDays.setEditable(false);
        gbc.gridx = 1;
        panel.add(txtDays, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Günlük Ücret:"), gbc);
        txtDailyRate = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtDailyRate, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Fiyat:"), gbc);
        txtPrice = new JTextField(20);
        txtPrice.setEditable(false);
        gbc.gridx = 1;
        panel.add(txtPrice, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Not:"), gbc);
        txtNote = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtNote, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Durum:"), gbc);
        txtStatus = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtStatus, gbc);

        btnSave = new JButton("Kaydet");
        btnCancel = new JButton("İptal");
        btnCalculate = new JButton("Hesapla");

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(btnCalculate, gbc);
        gbc.gridx = 1;
        panel.add(btnSave, gbc);
        gbc.gridx = 2;
        panel.add(btnCancel, gbc);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReservation();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePrice();
            }
        });

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void populateFields() {
        for (int i = 0; i < cmbCar.getItemCount(); i++) {
            if (cmbCar.getItemAt(i).getId() == reservation.getCarId()) {
                cmbCar.setSelectedIndex(i);
                break;
            }
        }
        txtCustomerName.setText(reservation.getCustomerName());
        txtCustomerIdNo.setText(reservation.getCustomerIdNo());
        txtCustomerPhone.setText(reservation.getCustomerPhone());
        txtCustomerEmail.setText(reservation.getCustomerEmail());
        txtStartDate.setText(reservation.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtEndDate.setText(reservation.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtDays.setText(String.valueOf(reservation.getDays()));
        txtDailyRate.setText(String.valueOf(reservation.getDailyRate()));
        txtPrice.setText(String.valueOf(reservation.getPrice()));
        txtNote.setText(reservation.getNote());
        txtStatus.setText(reservation.getStatus());
    }

    private void saveReservation() {
        try {
            Car selectedCar = (Car) cmbCar.getSelectedItem();
            int carId = selectedCar.getId();
            String customerName = txtCustomerName.getText();
            String customerIdNo = txtCustomerIdNo.getText();
            String customerPhone = txtCustomerPhone.getText();
            String customerEmail = txtCustomerEmail.getText();
            LocalDate startDate = LocalDate.parse(txtStartDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate endDate = LocalDate.parse(txtEndDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int days = Integer.parseInt(txtDays.getText());
            double dailyRate = Double.parseDouble(txtDailyRate.getText());
            double price = Double.parseDouble(txtPrice.getText());
            String note = txtNote.getText();
            String status = txtStatus.getText();

            if (reservation == null) {
                reservation = new Reservation(0, carId, customerName, customerIdNo, customerPhone, customerEmail, startDate, endDate, days, dailyRate, price, note, status);
                reservationManager.saveReservation(reservation);
            } else {
                reservation.setCarId(carId);
                reservation.setCustomerName(customerName);
                reservation.setCustomerIdNo(customerIdNo);
                reservation.setCustomerPhone(customerPhone);
                reservation.setCustomerEmail(customerEmail);
                reservation.setStartDate(startDate);
                reservation.setEndDate(endDate);
                reservation.setDays(days);
                reservation.setDailyRate(dailyRate);
                reservation.setPrice(price);
                reservation.setNote(note);
                reservation.setStatus(status);
                reservationManager.updateReservation(reservation);
            }

            dispose();
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli tarih formatında veriler girin (dd/MM/yyyy).", "Hata", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli sayısal değerler girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doğru doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculatePrice() {
        try {
            LocalDate startDate = LocalDate.parse(txtStartDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate endDate = LocalDate.parse(txtEndDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int days = (int) (endDate.toEpochDay() - startDate.toEpochDay());
            txtDays.setText(String.valueOf(days));

            double dailyRate = Double.parseDouble(txtDailyRate.getText());
            double price = days * dailyRate;
            txtPrice.setText(String.valueOf(price));

            filterAvailableCars(startDate, endDate);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Lütfen tarihleri doğru formatta girin (dd/MM/yyyy).", "Hata", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli bir günlük ücret girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<Car> allCars = carManager.findAllCars();
        List<Reservation> reservations = reservationManager.findReservationsByDateRange(startDate.toString(), endDate.toString());

        for (Reservation reservation : reservations) {
            allCars.removeIf(car -> car.getId() == reservation.getCarId());
        }

        cmbCar.removeAllItems();
        for (Car car : allCars) {
            cmbCar.addItem(car);
        }
    }
}
