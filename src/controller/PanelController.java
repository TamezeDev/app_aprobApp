package controller;

import view.LoginPanel;
import view.RegisterPanel;
import view.StartPanel;
import view.UserPanel;

import javax.swing.*;
import java.awt.*;

public class PanelController {

    private JFrame frame;
    private JPanel cards;
    private CardLayout cardLayout;


    public void startUI() {

        //main Window
        frame = new JFrame("main");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Config CardLayout
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        //Create panels
        StartPanel startPanel = new StartPanel(this);
        LoginPanel loginPanel = new LoginPanel(this);
        RegisterPanel resgiterPanel = new RegisterPanel(this);
        UserPanel userPanel = new UserPanel(this, loginPanel.getEmailUser());

        // Nombrate panels
        cards.add(startPanel.getPanel(), "start");
        cards.add(loginPanel.getPanel(), "login");
        cards.add(resgiterPanel.getPanel(), "register");
        cards.add(userPanel.getPanel(), "userMenu");

        //Init default panel
        frame.setContentPane(cards);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Change panel
    public void showPanel(String namePanel) {
        cardLayout.show(cards, namePanel);
    }

    //Recover reference panel
    public JFrame getFrame() {
        return frame;
    }
    //Add new panel
    public void addUserPanel(UserPanel userPanel) {
        cards.add(userPanel.getPanel(), "userMenu");
    }

}

