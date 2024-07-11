package View;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private JButton btnCarList;
    private JButton btnBrandList;
    private JButton btnModelList;
    private JButton btnReservation;
    private JButton btnLogout;

    public MainMenuFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Main Menu");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnCarList = new JButton("Car List");
        btnBrandList = new JButton("Brand List");
        btnModelList = new JButton("Model List");
        btnReservation = new JButton("Reservation Management");
        btnLogout = new JButton("Logout");

        setLayout(new GridLayout(5, 1));
        add(btnCarList);
        add(btnBrandList);
        add(btnModelList);
        add(btnReservation);
        add(btnLogout);

        btnCarList.addActionListener(e -> new CarListFrame().setVisible(true));
        btnBrandList.addActionListener(e -> new BrandFrame().setVisible(true));
        btnModelList.addActionListener(e -> new ModelFrame().setVisible(true));
        btnReservation.addActionListener(e -> new ReservationFrame().setVisible(true));
        btnLogout.addActionListener(e -> System.exit(0));

        this.setVisible(true);
    }
}

