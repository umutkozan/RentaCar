package View;

import Business.UserManager;
import Entity.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField fld_username;
    private JPasswordField fld_pass;
    private JButton btn_login;

    public LoginFrame() {
        // Pencere başlığı, boyutu ve ortalanması
        this.setTitle("Rent a Car");
        this.setSize(400, 400);
        this.setLocation(
                (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2
        );

        // Nimbus temasını uygulamak
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kullanıcı arayüzü bileşenleri
        fld_username = new JTextField();
        fld_pass = new JPasswordField();
        btn_login = new JButton("Giriş");

        // Layout ve bileşenleri ekleme
        this.setLayout(new GridLayout(4, 1));
        this.add(new JLabel("Kullanıcı Adı:"));
        this.add(fld_username);
        this.add(new JLabel("Şifre:"));
        this.add(fld_pass);
        this.add(btn_login);

        // Buton tıklama olayını ekleme
        btn_login.addActionListener(e -> handleLogin());

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Giriş işlemini yönetme metodu
    private void handleLogin() {
        String username = fld_username.getText();
        String password = new String(fld_pass.getPassword());

        if (isFieldEmpty(fld_username) || isFieldEmpty(fld_pass)) {
            showMsg("fill");
        } else {
            UserManager userManager = new UserManager();
            User user = userManager.findByLogin(username, password);

            if (user != null) {
                showMsg("done");
                // Başarılı giriş işlemleri
                this.dispose(); // LoginFrame'i kapat
                new MainMenuFrame().setVisible(true); // Yeni MainMenuFrame'i aç
            } else {
                showMsg("notFound");
            }
        }
    }

    // Alanların boş olup olmadığını kontrol eden metot
    private boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    // Hata ve bilgi mesajlarını gösteren yardımcı metot
    public static void showMsg(String str) {
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz !";
                title = "Hata!";
            }
            case "done" -> {
                msg = "İşlem Başarılı !";
                title = "Sonuç";
            }
            case "notFound" -> {
                msg = "Kayıt bulunamadı !";
                title = "Bulunamadı";
            }
            case "error" -> {
                msg = "Hatalı işlem yaptınız !";
                title = "Hata!";
            }
            default -> {
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
