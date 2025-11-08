package view;

import model.UserRegister;
import controller.PanelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class RegisterPanel {
    private JPanel registerPanel;
    private JButton btnBack;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JPasswordField pass;
    private JSpinner spFormacion;
    private JButton limpiarCamposButton;
    private JButton enviarDatosButton;

    public RegisterPanel(PanelController controller) {
        btnBack.addActionListener(e -> controller.showPanel("start"));
        txtName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtSurname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        pass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        loadStudiesSpinner();
        limpiarCamposButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetValues();
            }
        });
        enviarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (getRegisterValues()) {
                    controller.showPanel("start");
                }
            }
        });
    }

    public JPanel getPanel() {
        return registerPanel;
    }

    private void styleSpinner(JSpinner spinner) {
        Color bgColor = new Color(208, 215, 229);

        spinner.setBackground(bgColor);
        spinner.setBorder(BorderFactory.createEmptyBorder());

        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor defEditor = (JSpinner.DefaultEditor) editor;

            defEditor.getTextField().setBackground(bgColor);
            defEditor.getTextField().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            defEditor.getTextField().setForeground(Color.BLACK);
            defEditor.getTextField().setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }
    }

    private void loadStudiesSpinner() {

        String[] formations = {"DAM", "DAW", "ASIR"};
        SpinnerListModel model = new SpinnerListModel(formations);
        spFormacion.setModel(model);
        styleSpinner(spFormacion);
    }

    private void resetValues() {
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        pass.setText("");
        loadStudiesSpinner();
    }

    private boolean getRegisterValues() {
        UserRegister userRegister = new UserRegister();

        String name, surname, email, password, study;
        name = txtName.getText();
        surname = txtSurname.getText();
        email = txtEmail.getText();
        password = pass.getText();
        study = spFormacion.getValue().toString();
        if (!email.contains("@")) {
            txtEmail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            showAlert("Dirección de correo no válida", "Error", "error");
            return false;
        }

        if (!validPassword(password)) {
            pass.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            showAlert("La contraseña debe contener un mínimo de 8 caracteres incluyendo minúsculas, mayúsculas, números y algún carácter especial", "Error", "error");
            txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            return false;
        }
        if (userRegister.createUser(name, surname, email, password, study)) {
            showAlert("Registro completado con éxito, ya puede loguearse", "Información:", "info");
            return true;

        } else {
            showAlert("Ha ocurrido un error en el servidor, intenten de nuevo más tarde", "Información:", "warning");
            return true;
        }
    }

    private boolean validPassword(String password) {
        // Al menos 8 caracteres, una mayúscula, una minúscula, un número y un símbolo
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._#()\\-])[A-Za-z\\d@$!%*?&._#()\\-]{8,}$";
        return Pattern.matches(regex, password);
    }

    private void showAlert(String message, String title, String statusMessage) {
        int messageType;

        switch (statusMessage.toLowerCase()) {
            case "error":
                messageType = JOptionPane.ERROR_MESSAGE;
                break;
            case "warning":
                messageType = JOptionPane.WARNING_MESSAGE;
                break;
            case "info":
            case "information":
                messageType = JOptionPane.INFORMATION_MESSAGE;
                break;
            case "question":
                messageType = JOptionPane.QUESTION_MESSAGE;
                break;
            default:
                messageType = JOptionPane.PLAIN_MESSAGE;
        }

        JOptionPane.showMessageDialog(
                registerPanel,
                message,
                title,
                messageType
        );
    }

}

