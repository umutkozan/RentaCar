package View;

import Business.BrandManager;
import Business.ModelManager;
import Business.CarManager;
import Business.ReservationManager;
import Entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private final User user;
    private final BrandManager brandManager;
    private final ModelManager modelManager;
    private final CarManager carManager;
    private final ReservationManager reservationManager;
    private JLabel lblWelcome;
    private JTable tblBrands, tblModels, tblCars, tblReservations;
    private DefaultTableModel brandTableModel, modelTableModel, carTableModel, reservationTableModel;

    public AdminView(User user) {
        this.user = user;
        this.brandManager = new BrandManager();
        this.modelManager = new ModelManager();
        this.carManager = new CarManager();
        this.reservationManager = new ReservationManager();
        initializeGUI();
    }

    private void initializeGUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Admin View");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        lblWelcome = new JLabel("Hoşgeldiniz : " + this.user.getUsername());
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Marka Yönetimi Paneli
        JPanel brandPanel = new JPanel(new BorderLayout());
        brandTableModel = new DefaultTableModel(new Object[]{"ID", "Marka Adı"}, 0);
        tblBrands = new JTable(brandTableModel);
        loadBrandTable();
        brandPanel.add(new JScrollPane(tblBrands), BorderLayout.CENTER);

        JPanel brandControlPanel = new JPanel();
        JButton btnAddBrand = new JButton("Yeni Marka");
        JButton btnEditBrand = new JButton("Marka Düzenle");
        JButton btnDeleteBrand = new JButton("Marka Sil");

        btnAddBrand.addActionListener(e -> openBrandForm(null));
        btnEditBrand.addActionListener(e -> {
            int selectedRow = tblBrands.getSelectedRow();
            if (selectedRow >= 0) {
                int brandId = (int) tblBrands.getValueAt(selectedRow, 0);
                openBrandForm(brandManager.findBrandById(brandId));
            } else {
                JOptionPane.showMessageDialog(AdminView.this, "Lütfen düzenlemek için bir marka seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnDeleteBrand.addActionListener(e -> {
            int selectedRow = tblBrands.getSelectedRow();
            if (selectedRow >= 0) {
                int brandId = (int) tblBrands.getValueAt(selectedRow, 0);
                if (JOptionPane.showConfirmDialog(AdminView.this, "Bu markayı silmek istediğinizden emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (brandManager.deleteBrand(brandId)) {
                        JOptionPane.showMessageDialog(AdminView.this, "Marka başarıyla silindi.");
                        loadBrandTable();
                    } else {
                        JOptionPane.showMessageDialog(AdminView.this, "Marka silinirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(AdminView.this, "Lütfen silmek için bir marka seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        brandControlPanel.add(btnAddBrand);
        brandControlPanel.add(btnEditBrand);
        brandControlPanel.add(btnDeleteBrand);
        brandPanel.add(brandControlPanel, BorderLayout.SOUTH);

        // Model Yönetimi Paneli
        JPanel modelPanel = new JPanel(new BorderLayout());
        modelTableModel = new DefaultTableModel(new Object[]{"ID", "Marka ID", "Model Adı", "Tip", "Yıl", "Yakıt", "Vites"}, 0);
        tblModels = new JTable(modelTableModel);
        loadModelTable();
        modelPanel.add(new JScrollPane(tblModels), BorderLayout.CENTER);

        JPanel modelControlPanel = new JPanel();
        JButton btnAddModel = new JButton("Yeni Model");
        JButton btnEditModel = new JButton("Model Düzenle");
        JButton btnDeleteModel = new JButton("Model Sil");

        btnAddModel.addActionListener(e -> openModelForm(null));
        btnEditModel.addActionListener(e -> {
            int selectedRow = tblModels.getSelectedRow();
            if (selectedRow >= 0) {
                int modelId = (int) tblModels.getValueAt(selectedRow, 0);
                openModelForm(modelManager.findModelById(modelId));
            } else {
                JOptionPane.showMessageDialog(AdminView.this, "Lütfen düzenlemek için bir model seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnDeleteModel.addActionListener(e -> {
            int selectedRow = tblModels.getSelectedRow();
            if (selectedRow >= 0) {
                int modelId = (int) tblModels.getValueAt(selectedRow, 0);
                if (JOptionPane.showConfirmDialog(AdminView.this, "Bu modeli silmek istediğinizden emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (modelManager.deleteModel(modelId)) {
                        JOptionPane.showMessageDialog(AdminView.this, "Model başarıyla silindi.");
                        loadModelTable();
                    } else {
                        JOptionPane.showMessageDialog(AdminView.this, "Model silinirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(AdminView.this, "Lütfen silmek için bir model seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        modelControlPanel.add(btnAddModel);
        modelControlPanel.add(btnEditModel);
        modelControlPanel.add(btnDeleteModel);
        modelPanel.add(modelControlPanel, BorderLayout.SOUTH);

        // Araç Yönetimi Paneli
        JPanel carPanel = new JPanel(new BorderLayout());
        carTableModel = new DefaultTableModel(new Object[]{"ID", "Model ID", "Plaka", "Renk", "KM", "Durum"}, 0);
        tblCars = new JTable(carTableModel);
        loadCarTable();
        carPanel.add(new JScrollPane(tblCars), BorderLayout.CENTER);

        JPanel carControlPanel = new JPanel();
        JButton btnAddCar = new JButton("Yeni Araç");
        JButton btnEditCar = new JButton("Araç Düzenle");
        JButton btnDeleteCar = new JButton("Araç Sil");

        btnAddCar.addActionListener(e -> openCarForm(null));
        btnEditCar.addActionListener(e -> {
            int selectedRow = tblCars.getSelectedRow();
            if (selectedRow >= 0) {
                int carId = (int) tblCars.getValueAt(selectedRow, 0);
                openCarForm(carManager.findCarById(carId));
            } else {
                JOptionPane.showMessageDialog(AdminView.this, "Lütfen düzenlemek için bir araç seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnDeleteCar.addActionListener(e -> {
            int selectedRow = tblCars.getSelectedRow();
            if (selectedRow >= 0) {
                int carId = (int) tblCars.getValueAt(selectedRow, 0);
                if (JOptionPane.showConfirmDialog(AdminView.this, "Bu aracı silmek istediğinizden emin misiniz? Bu işlem tüm ilgili rezervasyonları da silecek.", "Onay", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (carManager.deleteCarWithReservations(carId)) {
                        JOptionPane.showMessageDialog(AdminView.this, "Araç ve ilgili rezervasyonlar başarıyla silindi.");
                        loadCarTable();
                    } else {
                        JOptionPane.showMessageDialog(AdminView.this, "Araç silinirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(AdminView.this, "Lütfen silmek için bir araç seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        carControlPanel.add(btnAddCar);
        carControlPanel.add(btnEditCar);
        carControlPanel.add(btnDeleteCar);
        carPanel.add(carControlPanel, BorderLayout.SOUTH);

        // Rezervasyon Yönetimi Paneli
        JPanel reservationPanel = new JPanel(new BorderLayout());
        reservationTableModel = new DefaultTableModel(new Object[]{"ID", "Araba ID", "Müşteri Adı", "Kimlik No", "Telefon", "E-posta", "Başlangıç Tarihi", "Bitiş Tarihi", "Gün Sayısı", "Günlük Ücret", "Fiyat", "Not", "Durum"}, 0);
        tblReservations = new JTable(reservationTableModel);
        loadReservationTable();
        reservationPanel.add(new JScrollPane(tblReservations), BorderLayout.CENTER);

        JPanel reservationControlPanel = new JPanel();
        JButton btnAddReservation = new JButton("Yeni Rezervasyon");
        JButton btnEditReservation = new JButton("Rezervasyon Düzenle");
        JButton btnDeleteReservation = new JButton("Rezervasyon Sil");

        btnAddReservation.addActionListener(e -> openReservationForm(null));
        btnEditReservation.addActionListener(e -> {
            int selectedRow = tblReservations.getSelectedRow();
            if (selectedRow >= 0) {
                int reservationId = (int) tblReservations.getValueAt(selectedRow, 0);
                openReservationForm(reservationManager.findReservationById(reservationId));
            } else {
                JOptionPane.showMessageDialog(AdminView.this, "Lütfen düzenlemek için bir rezervasyon seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnDeleteReservation.addActionListener(e -> {
            int selectedRow = tblReservations.getSelectedRow();
            if (selectedRow >= 0) {
                int reservationId = (int) tblReservations.getValueAt(selectedRow, 0);
                if (JOptionPane.showConfirmDialog(AdminView.this, "Bu rezervasyonu silmek istediğinizden emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (reservationManager.deleteReservation(reservationId)) {
                        JOptionPane.showMessageDialog(AdminView.this, "Rezervasyon başarıyla silindi.");
                        loadReservationTable();
                    } else {
                        JOptionPane.showMessageDialog(AdminView.this, "Rezervasyon silinirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(AdminView.this, "Lütfen silmek için bir rezervasyon seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        reservationControlPanel.add(btnAddReservation);
        reservationControlPanel.add(btnEditReservation);
        reservationControlPanel.add(btnDeleteReservation);
        reservationPanel.add(reservationControlPanel, BorderLayout.SOUTH);

        tabbedPane.add("Markalar", brandPanel);
        tabbedPane.add("Modeller", modelPanel);
        tabbedPane.add("Araçlar", carPanel);
        tabbedPane.add("Rezervasyonlar", reservationPanel);

        this.setLayout(new BorderLayout());
        this.add(lblWelcome, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void loadBrandTable() {
        brandTableModel.setRowCount(0);
        for (Brand brand : brandManager.findAllBrands()) {
            brandTableModel.addRow(new Object[]{brand.getId(), brand.getName()});
        }
    }

    private void loadModelTable() {
        modelTableModel.setRowCount(0);
        for (Model model : modelManager.findAllModels()) {
            modelTableModel.addRow(new Object[]{model.getId(), model.getBrandId(), model.getName(), model.getType(), model.getYear(), model.getFuel(), model.getGear()});
        }
    }

    private void loadCarTable() {
        carTableModel.setRowCount(0);
        for (Car car : carManager.findAllCars()) {
            carTableModel.addRow(new Object[]{car.getId(), car.getModelId(), car.getPlate(), car.getColor(), car.getKilometers(), car.isAvailable()});
        }
    }

    private void loadReservationTable() {
        reservationTableModel.setRowCount(0);
        for (Reservation reservation : reservationManager.findAllReservations()) {
            reservationTableModel.addRow(new Object[]{reservation.getId(), reservation.getCarId(), reservation.getCustomerName(), reservation.getCustomerIdNo(), reservation.getCustomerPhone(), reservation.getCustomerEmail(), reservation.getStartDate(), reservation.getEndDate(), reservation.getDays(), reservation.getDailyRate(), reservation.getPrice(), reservation.getNote(), reservation.getStatus()});
        }
    }

    private void openBrandForm(Brand brand) {
        BrandForm form = new BrandForm(this, "Marka Yönetimi", true, brandManager, brand);
        form.setVisible(true);
        loadBrandTable();
    }

    private void openModelForm(Model model) {
        ModelForm form = new ModelForm(this, "Model Yönetimi", true, modelManager, model, brandManager);
        form.setVisible(true);
        loadModelTable();
    }

    private void openCarForm(Car car) {
        CarForm form = new CarForm(this, "Araç Yönetimi", true, carManager, car);
        form.setVisible(true);
        loadCarTable();
    }

    private void openReservationForm(Reservation reservation) {
        ReservationForm form = new ReservationForm(this, "Rezervasyon Yönetimi", true, reservationManager, reservation, carManager);
        form.setVisible(true);
        loadReservationTable();
    }
}
