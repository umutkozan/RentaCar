package View;

import Business.ReservationManager;
import Business.CarManager;
import Entity.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReservationFrame extends JFrame {
    private JTable reservationTable;
    private ReservationManager reservationManager;
    private CarManager carManager;
    private JButton btnAdd, btnUpdate, btnDelete;

    public ReservationFrame() {
        reservationManager = new ReservationManager();
        carManager = new CarManager();
        initUI();
    }

    private void initUI() {
        setTitle("Reservation Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tabloyu burada oluşturun
        String[] columnNames = {"ID", "Araba ID", "Müşteri Adı", "Kimlik No", "Telefon", "E-posta", "Başlangıç Tarihi", "Bitiş Tarihi", "Gün Sayısı", "Günlük Ücret", "Fiyat", "Not", "Durum"};
        reservationTable = new JTable(new DefaultTableModel(columnNames, 0));
        add(new JScrollPane(reservationTable), BorderLayout.CENTER);

        // Tabloyu oluşturduktan sonra refreshTable metodunu çağırın
        refreshTable();

        JPanel panel = new JPanel();
        btnAdd = new JButton("Ekle");
        btnUpdate = new JButton("Güncelle");
        btnDelete = new JButton("Sil");

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        add(panel, BorderLayout.SOUTH);

        // Button actions
        btnAdd.addActionListener(e -> addReservation());
        btnUpdate.addActionListener(e -> updateReservation());
        btnDelete.addActionListener(e -> deleteReservation());

        this.setVisible(true);
    }

    private void addReservation() {
        ReservationForm form = new ReservationForm(this, "Yeni Rezervasyon Ekle", true, reservationManager, null, carManager);
        form.setVisible(true);
        refreshTable();
    }

    private void updateReservation() {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) reservationTable.getValueAt(selectedRow, 0);
            Reservation reservation = reservationManager.findAllReservations().stream().filter(r -> r.getId() == id).findFirst().orElse(null);
            if (reservation != null) {
                ReservationForm form = new ReservationForm(this, "Rezervasyonu Güncelle", true, reservationManager, reservation, carManager);
                form.setVisible(true);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen güncellenecek rezervasyonu seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteReservation() {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) reservationTable.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Bu rezervasyonu silmek istediğinize emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                reservationManager.deleteReservation(id);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen silinecek rezervasyonu seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refreshTable() {
        List<Reservation> reservations = reservationManager.findAllReservations();
        String[] columnNames = {"ID", "Araba ID", "Müşteri Adı", "Kimlik No", "Telefon", "E-posta", "Başlangıç Tarihi", "Bitiş Tarihi", "Gün Sayısı", "Günlük Ücret", "Fiyat", "Not", "Durum"};
        Object[][] data = new Object[reservations.size()][13];

        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            data[i][0] = reservation.getId();
            data[i][1] = reservation.getCarId();
            data[i][2] = reservation.getCustomerName();
            data[i][3] = reservation.getCustomerIdNo();
            data[i][4] = reservation.getCustomerPhone();
            data[i][5] = reservation.getCustomerEmail();
            data[i][6] = reservation.getStartDate();
            data[i][7] = reservation.getEndDate();
            data[i][8] = reservation.getDays();
            data[i][9] = reservation.getDailyRate();
            data[i][10] = reservation.getPrice();
            data[i][11] = reservation.getNote();
            data[i][12] = reservation.getStatus();
        }

        reservationTable.setModel(new DefaultTableModel(data, columnNames));
    }
}
