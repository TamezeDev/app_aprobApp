package view;

import controller.PanelController;
import model.WindowUtils;

import javax.swing.*;
import java.awt.event.*;

public class StartPanel {
    private JPanel startPanel;
    private JButton btnExit;
    private JButton btnMin;
    private JButton btnRegister;
    private JButton btnLogin;
    private JPanel mainBar;


    private int mouseX, mouseY;

    public StartPanel(PanelController controller) {

        //MainBar utils
        WindowUtils.attachExitButton(btnExit);
        WindowUtils.attachMinimizeButton(btnMin, this.getPanel());
        WindowUtils.enableWindowDrag(mainBar);
        //Navegate others panels
        btnLogin.addActionListener(e -> controller.showPanel("login"));
        btnRegister.addActionListener(e -> controller.showPanel("register"));
    }

    public JPanel getPanel() {
        return startPanel;
    }
}
