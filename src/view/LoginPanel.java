package view;

import controller.PanelController;
import javax.swing.*;

public class LoginPanel {
    private JPanel loginPanel;
    private JButton btnBack;

    public LoginPanel(PanelController controller) {
        btnBack.addActionListener(e -> controller.showPanel("start"));
    }

    public JPanel getPanel() {
        return loginPanel;
    }
}
