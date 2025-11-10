package view;

import model.ReverseSpinnerListModel;
import model.UserRegister;
import controller.PanelController;
import model.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class RegisterPanel {
    private JPanel registerPanel;
    private JButton btnBack;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JPasswordField pass;
    private JSpinner spFormacion;
    private JButton btnLimpiar;
    private JButton btnEnviar;
    private JButton btnExit;
    private JPanel mainBar;
    private JButton btnMin;

    //main function
    public RegisterPanel(PanelController controller) {
        WindowUtils.attachExitButton(btnExit);
        WindowUtils.attachMinimizeButton(btnMin, this.getPanel());
        WindowUtils.enableWindowDrag(mainBar);
        initListeners(controller);
        setBordersTxt();
        loadStudiesSpinner();
    }

    public JPanel getPanel() {
        return registerPanel;
    }

    // load background spinner
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
            defEditor.getTextField().setFont(new Font("Segoe UI", Font.PLAIN, 16));
        }
    }

    //load spinner list
    private void loadStudiesSpinner() {
        String[] formations = {"DAM", "DAW", "ASIR"};
        ReverseSpinnerListModel model = new ReverseSpinnerListModel(formations);
        spFormacion.setModel(model);
        styleSpinner(spFormacion);
    }


    // design textFields borders
    private void setBordersTxt() {
        txtName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtSurname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        pass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
    }

    //clear values
    private void resetValues() {
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        pass.setText("");
        loadStudiesSpinner();
    }

    //init buttons
    private void initListeners(PanelController controller) {
        btnBack.addActionListener(e -> {
            resetValues();
            setBordersTxt();
            controller.showPanel("start");

        });
        btnLimpiar.addActionListener(e -> {
            resetValues();
            setBordersTxt();
        });
        btnEnviar.addActionListener(e -> {
            if (getRegisterValues()) {
                controller.showPanel("start");
            }
        });

    }

    //get user values and show result info
    private boolean getRegisterValues() {
        String name = txtName.getText().trim();
        String surname = txtSurname.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(pass.getPassword());
        String study = spFormacion.getValue().toString();

        if (name.isEmpty()) {
            setBordersTxt();
            setErrorBorder(txtName, true);
            showAlert("Debe introducir un nombre", "Error", "error");
            return false;
        }
        if (surname.isEmpty()) {
            setBordersTxt();
            setErrorBorder(txtSurname, true);
            showAlert("Debe introducir los apellidos", "Error", "error");
            return false;
        }
        if (!validEmail(email)) {
            setBordersTxt();
            setErrorBorder(txtEmail, true);
            showAlert("Dirección de correo no válida", "Error", "error");
            return false;
        }
        if (!validPassword(password)) {
            setBordersTxt();
            setErrorBorder(pass, true);
            showAlert("Contraseña no válida", "Error", "error");
            return false;
        }

        UserRegister userRegister = new UserRegister();
        int responseCode = userRegister.createUser(name, surname, email, password, study);

        if (responseCode == 200) {
            showAlert("Registro completado con éxito", "Información", "info");
            setBordersTxt();
            resetValues();
            return true;
        } else {
            showAlert("Error en el servidor. Intente de nuevo más tarde", "Advertencia", "warning");
            setBordersTxt();
            return true;
        }
    }
    //set border error
    private void setErrorBorder(JComponent comp, boolean error) {
        Color color = error ? Color.RED : Color.BLACK;
        comp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
    }
    //validate email
    private boolean validEmail(String email) {
        return Pattern.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$", email);
    }

    //validate password
    private boolean validPassword(String password) {
        // Al menos 8 caracteres, una mayúscula, una minúscula, un número y un símbolo
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._#()\\-])[A-Za-z\\d@$!%*?&._#()\\-]{8,}$";
        return Pattern.matches(regex, password);
    }

    // Show different alert messages
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

