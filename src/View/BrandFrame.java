package View;

import Business.BrandManager;
import Entity.Brand;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BrandFrame extends JFrame {
    private JTable brandTable;
    private BrandManager brandManager;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;

    public BrandFrame() {
        brandManager = new BrandManager();
        initUI();
    }

    private void initUI() {
        setTitle("Brand List");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        refreshTable();

        JPanel panel = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        add(panel, BorderLayout.SOUTH);

        // Button actions
        btnAdd.addActionListener(e -> openBrandForm(null));
        btnUpdate.addActionListener(e -> {
            int selectedRow = brandTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) brandTable.getValueAt(selectedRow, 0);
                Brand brand = brandManager.findBrandById(id);
                openBrandForm(brand);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a brand to update.");
            }
        });
        btnDelete.addActionListener(e -> deleteBrand());

        this.setVisible(true);
    }

    private void refreshTable() {
        List<Brand> brands = brandManager.findAllBrands();
        String[] columnNames = {"ID", "Name"};
        Object[][] data = new Object[brands.size()][2];

        for (int i = 0; i < brands.size(); i++) {
            Brand brand = brands.get(i);
            data[i][0] = brand.getId();
            data[i][1] = brand.getName();
        }

        brandTable = new JTable(data, columnNames);
        add(new JScrollPane(brandTable), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void openBrandForm(Brand brand) {
        BrandForm form = new BrandForm(this, "Brand Form", true, brandManager, brand);
        form.setVisible(true);
        refreshTable();
    }

    private void deleteBrand() {
        int selectedRow = brandTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) brandTable.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this brand? This action cannot be undone.", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (brandManager.deleteBrand(id)) {
                    JOptionPane.showMessageDialog(this, "Brand deleted successfully!");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete brand.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a brand to delete.");
        }
    }
}
