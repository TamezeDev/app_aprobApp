package view;

import controller.PanelController;
import javax.swing.*;
import java.awt.*;

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

}

