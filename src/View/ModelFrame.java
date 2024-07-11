package View;

import Business.ModelManager;
import Business.BrandManager;
import Entity.Model;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModelFrame extends JFrame {
    private JTable modelTable;
    private ModelManager modelManager;
    private BrandManager brandManager;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;

    public ModelFrame() {
        modelManager = new ModelManager();
        brandManager = new BrandManager();
        initUI();
    }

    private void initUI() {
        setTitle("Model List");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelTable = new JTable();
        refreshModelTable();
        add(new JScrollPane(modelTable), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        add(panel, BorderLayout.SOUTH);

        // Button actions
        btnAdd.addActionListener(e -> addModel());
        btnUpdate.addActionListener(e -> updateModel());
        btnDelete.addActionListener(e -> deleteModel());

        this.setVisible(true);
    }

    private void addModel() {
        ModelForm form = new ModelForm(this, "New Model", true, modelManager, null, brandManager);
        form.setVisible(true);
        refreshModelTable(); // Model ekleme işleminden sonra tabloyu yenilemek için bu satırı ekleyin.
    }

    private void updateModel() {
        int selectedRow = modelTable.getSelectedRow();
        if (selectedRow >= 0) {
            int modelId = (int) modelTable.getValueAt(selectedRow, 0);
            Model model = modelManager.findModelById(modelId);
            ModelForm form = new ModelForm(this, "Update Model", true, modelManager, model, brandManager);
            form.setVisible(true);
            refreshModelTable(); // Model güncelleme işleminden sonra tabloyu yenilemek için bu satırı ekleyin.
        } else {
            JOptionPane.showMessageDialog(this, "Please select a model to update.");
        }
    }

    private void deleteModel() {
        int selectedRow = modelTable.getSelectedRow();
        if (selectedRow >= 0) {
            int modelId = (int) modelTable.getValueAt(selectedRow, 0);
            if (modelManager.deleteModel(modelId)) {
                JOptionPane.showMessageDialog(this, "Model deleted successfully!");
                refreshModelTable(); // Model silme işleminden sonra tabloyu yenilemek için bu satırı ekleyin.
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete model.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a model to delete.");
        }
    }

    private void refreshModelTable() {
        List<Model> models = modelManager.findAllModels();
        String[] columnNames = {"ID", "Brand ID", "Brand Name", "Name", "Type", "Year", "Fuel", "Gear"};
        Object[][] data = new Object[models.size()][8];

        for (int i = 0; i < models.size(); i++) {
            Model model = models.get(i);
            data[i][0] = model.getId();
            data[i][1] = model.getBrandId();
            data[i][2] = model.getBrandName();
            data[i][3] = model.getName();
            data[i][4] = model.getType();
            data[i][5] = model.getYear();
            data[i][6] = model.getFuel();
            data[i][7] = model.getGear();
        }

        modelTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
