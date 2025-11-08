package view;

import controller.PanelController;
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

        // Botón de salida
        btnExit.addActionListener(e -> System.exit(0));

        //Minimiza ventana
        btnMin.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(startPanel);
            frame.setState(JFrame.ICONIFIED);
        });

        // Movimiento de ventana desde la barra
        mainBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        mainBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainBar);
                frame.setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
            }
        });

        //Navegar entre paneles
        btnLogin.addActionListener(e -> controller.showPanel("login"));
        btnRegister.addActionListener(e -> controller.showPanel("register"));
    }

    public JPanel getPanel() {
        return startPanel;
    }
}
