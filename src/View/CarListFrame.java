package View;

import Business.CarManager;
import Entity.Car;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarListFrame extends JFrame {
    private JTable carTable;
    private CarManager carManager;
    private JButton btnAdd, btnUpdate, btnDelete, btnManageBrands;

    public CarListFrame() {
        carManager = new CarManager();
        initUI();
    }

    private void initUI() {
        setTitle("Car List");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Car> cars = carManager.findAvailableCars();
        String[] columnNames = {"ID", "Plate", "Model", "Brand", "Color", "KM"};
        Object[][] data = new Object[cars.size()][6];

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            data[i][0] = car.getId();
            data[i][1] = car.getPlate();
            data[i][2] = car.getModel();  // Assuming car has a getModel method
            data[i][3] = car.getBrand();  // Assuming car has a getBrand method
            data[i][4] = car.getColor();
            data[i][5] = car.getKilometers();
        }

        carTable = new JTable(data, columnNames);
        add(new JScrollPane(carTable), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnManageBrands = new JButton("Manage Brands");

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnManageBrands);
        add(panel, BorderLayout.SOUTH);

        // Button actions
        btnAdd.addActionListener(e -> addCar());
        btnUpdate.addActionListener(e -> updateCar());
        btnDelete.addActionListener(e -> deleteCar());
        btnManageBrands.addActionListener(e -> manageBrands());

        this.setVisible(true);
    }

    private void addCar() {
        CarForm form = new CarForm(this, "New Car", true, carManager, null);
        form.setVisible(true);
        refreshTable();
    }

    private void updateCar() {
        int selectedRow = carTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) carTable.getValueAt(selectedRow, 0);
            Car car = carManager.findAvailableCars().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
            if (car != null) {
                CarForm form = new CarForm(this, "Update Car", true, carManager, car);
                form.setVisible(true);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a car to update.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteCar() {
        int selectedRow = carTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) carTable.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Deleting this car will also delete all related reservations. Do you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                carManager.deleteCarWithReservations(id);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a car to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void manageBrands() {
        BrandFrame brandFrame = new BrandFrame();
        brandFrame.setVisible(true);
    }

    private void refreshTable() {
        List<Car> cars = carManager.findAvailableCars();
        String[] columnNames = {"ID", "Plate", "Model", "Brand", "Color", "KM"};
        Object[][] data = new Object[cars.size()][6];

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            data[i][0] = car.getId();
            data[i][1] = car.getPlate();
            data[i][2] = car.getModel();
            data[i][3] = car.getBrand();
            data[i][4] = car.getColor();
            data[i][5] = car.getKilometers();
        }

        carTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
