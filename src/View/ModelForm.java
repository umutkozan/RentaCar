package View;

import Business.ModelManager;
import Business.BrandManager;
import Entity.Model;
import Entity.Brand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModelForm extends JDialog {
    private JTextField txtModelName, txtModelYear, txtModelType, txtFuelType, txtGearType;
    private JComboBox<Brand> cmbBrand;
    private JButton btnSave, btnCancel;
    private ModelManager modelManager;
    private Model model;
    private BrandManager brandManager;

    public ModelForm(Frame owner, String title, boolean modal, ModelManager modelManager, Model model, BrandManager brandManager) {
        super(owner, title, modal);
        this.modelManager = modelManager;
        this.model = model;
        this.brandManager = brandManager;
        initUI();
        if (model != null) {
            populateFields();
        }
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        panel.add(new JLabel("Brand:"));
        cmbBrand = new JComboBox<>();
        List<Brand> brands = brandManager.findAllBrands();
        for (Brand brand : brands) {
            cmbBrand.addItem(brand);
        }
        panel.add(cmbBrand);

        panel.add(new JLabel("Model Name:"));
        txtModelName = new JTextField();
        panel.add(txtModelName);

        panel.add(new JLabel("Model Year:"));
        txtModelYear = new JTextField();
        panel.add(txtModelYear);

        panel.add(new JLabel("Model Type:"));
        txtModelType = new JTextField();
        panel.add(txtModelType);

        panel.add(new JLabel("Fuel Type:"));
        txtFuelType = new JTextField();
        panel.add(txtFuelType);

        panel.add(new JLabel("Gear Type:"));
        txtGearType = new JTextField();
        panel.add(txtGearType);

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        panel.add(btnSave);
        panel.add(btnCancel);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveModel();
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
        for (int i = 0; i < cmbBrand.getItemCount(); i++) {
            if (cmbBrand.getItemAt(i).getId() == model.getBrandId()) {
                cmbBrand.setSelectedIndex(i);
                break;
            }
        }
        txtModelName.setText(model.getName());
        txtModelYear.setText(model.getYear());
        txtModelType.setText(model.getType());
        txtFuelType.setText(model.getFuel());
        txtGearType.setText(model.getGear());
    }

    private void saveModel() {
        try {
            Brand selectedBrand = (Brand) cmbBrand.getSelectedItem();
            int brandId = selectedBrand.getId();
            String modelName = txtModelName.getText();
            String modelYear = txtModelYear.getText();
            String modelType = txtModelType.getText();
            String fuelType = txtFuelType.getText();
            String gearType = txtGearType.getText();

            if (model == null) {
                model = new Model(0, brandId, modelName, modelType, modelYear, fuelType, gearType, selectedBrand.getName());
                modelManager.saveModel(model);
            } else {
                model.setBrandId(brandId);
                model.setName(modelName);
                model.setYear(modelYear);
                model.setType(modelType);
                model.setFuel(fuelType);
                model.setGear(gearType);
                modelManager.updateModel(model);
            }

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
