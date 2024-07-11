package View;

import Business.BrandManager;
import Entity.Brand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrandForm extends JDialog {
    private JTextField txtBrandName;
    private JButton btnSave, btnCancel;
    private BrandManager brandManager;
    private Brand brand;

    public BrandForm(Frame owner, String title, boolean modal, BrandManager brandManager, Brand brand) {
        super(owner, title, modal);
        this.brandManager = brandManager;
        this.brand = brand;
        initUI();
        if (brand != null) {
            populateFields();
        }
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Brand Name:"));
        txtBrandName = new JTextField();
        panel.add(txtBrandName);

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        panel.add(btnSave);
        panel.add(btnCancel);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBrand();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void populateFields() {
        txtBrandName.setText(brand.getName());
    }

    private void saveBrand() {
        String brandName = txtBrandName.getText();
        if (brandName == null || brandName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Brand name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (brand == null) {
            brand = new Brand();
            brand.setName(brandName);
            if (brandManager.saveBrand(brand)) {
                JOptionPane.showMessageDialog(this, "Brand added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add brand.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            brand.setName(brandName);
            if (brandManager.updateBrand(brand)) {
                JOptionPane.showMessageDialog(this, "Brand updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update brand.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
