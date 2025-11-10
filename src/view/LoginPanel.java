package view;

import controller.PanelController;
import model.UserLogin;
import model.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class LoginPanel {
    private String emailUser;
    private JPanel loginPanel;
    private JButton btnBack;
    private JTextField txtEmail;
    private JPasswordField pass;
    private JButton btnBorrar;
    private JButton btnAcceder;
    private JPanel mainBar;
    private JButton btnExit;
    private JButton btnMin;

    //Main function
    public LoginPanel(PanelController controller) {
        WindowUtils.attachExitButton(btnExit);
        WindowUtils.attachMinimizeButton(btnMin, this.getPanel());
        WindowUtils.enableWindowDrag(mainBar);
        initListeners(controller);
        setBordersTxt();
    }

    public JPanel getPanel() {
        return loginPanel;
    }

    // design textFields borders
    private void setBordersTxt() {
        txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        pass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
    }

    //clear values
    private void resetValues() {
        txtEmail.setText("");
        pass.setText("");
    }

    //init buttons
    private void initListeners(PanelController controller) {
        btnBack.addActionListener(e -> {
            resetValues();
            setBordersTxt();
            controller.showPanel("start");
        });
        btnBorrar.addActionListener(e -> {
            resetValues();
            setBordersTxt();
        });
        btnAcceder.addActionListener(e -> getLoginValues(controller));
    }

    //Check password
    private boolean validPassword(String password) {
        // Al menos 8 caracteres, una mayúscula, una minúscula, un número y un símbolo
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._#()\\-])[A-Za-z\\d@$!%*?&._#()\\-]{8,}$";
        return Pattern.matches(regex, password);
    }

    //Login
    private void getLoginValues(PanelController controller) {
        UserLogin userLogin = new UserLogin();
        String email, password;
        email = txtEmail.getText();
        password = pass.getText();

        if (!email.contains("@")) {
            showError("El correo introducido no es válido");
            setBordersTxt();
            txtEmail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            return;
        } else if (!validPassword(password)) {
            setBordersTxt();
            pass.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            showError("La contraseña introducida no es válida");
        } else if (userLogin.accessUser(email, password) != 200) {
            setBordersTxt();
            showError("Credenciales incorrectas");
        } else {
            setBordersTxt();
            resetValues();
            emailUser = email;
            UserPanel userPanel = new UserPanel(controller, emailUser);
            controller.addUserPanel(userPanel);
            controller.showPanel("userMenu");
        }
    }

    //Show error values
    private void showError(String error) {
        JOptionPane.showMessageDialog(
                loginPanel,
                error,
                "Error", JOptionPane.ERROR_MESSAGE);

    }
    //Get email User
    public String getEmailUser(){
        return emailUser;
    }

}
