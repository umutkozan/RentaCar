package View;

import Business.CarManager;
import Business.ModelManager;
import Entity.Car;
import Entity.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarForm extends JDialog {
    private JTextField txtPlate, txtColor, txtKilometers;
    private JComboBox<Model> cmbModel;
    private JButton btnSave, btnCancel;
    private CarManager carManager;
    private Car car;

    public CarForm(Frame owner, String title, boolean modal, CarManager carManager, Car car) {
        super(owner, title, modal);
        this.carManager = carManager;
        this.car = car;

        initUI();
        if (car != null) {
            populateFields();
        }
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Model:"));
        cmbModel = new JComboBox<>();
        ModelManager modelManager = new ModelManager();
        List<Model> models = modelManager.findAllModels();
        for (Model model : models) {
            cmbModel.addItem(model);
        }
        panel.add(cmbModel);

        panel.add(new JLabel("Plate:"));
        txtPlate = new JTextField();
        panel.add(txtPlate);

        panel.add(new JLabel("Color:"));
        txtColor = new JTextField();
        panel.add(txtColor);

        panel.add(new JLabel("Kilometers:"));
        txtKilometers = new JTextField();
        panel.add(txtKilometers);

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        panel.add(btnSave);
        panel.add(btnCancel);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCar();
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
        for (int i = 0; i < cmbModel.getItemCount(); i++) {
            Model model = cmbModel.getItemAt(i);
            if (model.getId() == car.getModelId()) {
                cmbModel.setSelectedItem(model);
                break;
            }
        }
        txtPlate.setText(car.getPlate());
        txtColor.setText(car.getColor());
        txtKilometers.setText(String.valueOf(car.getKilometers()));
    }

    private void saveCar() {
        try {
            Model selectedModel = (Model) cmbModel.getSelectedItem();
            String plate = txtPlate.getText();
            String color = txtColor.getText();
            int kilometers = Integer.parseInt(txtKilometers.getText());

            if (car == null) {
                car = new Car(0, selectedModel.getId(), plate, selectedModel.getName(), selectedModel.getBrandName(), color, kilometers, true);
                carManager.saveCar(car);
            } else {
                car.setModelId(selectedModel.getId());
                car.setPlate(plate);
                car.setColor(color);
                car.setKilometers(kilometers);
                carManager.updateCar(car);
            }

            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
