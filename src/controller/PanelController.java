package controller;

import view.LoginPanel;
import view.RegisterPanel;
import view.StartPanel;

import javax.swing.*;
import java.awt.*;

public class PanelController {

    private JFrame frame;
    private JPanel cards;
    private CardLayout cardLayout;


    public void startUI(){

        //Ventana principal
        frame = new JFrame("main");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar CardLayout
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        //Crear los paneles
        StartPanel startPanel = new StartPanel(this);
        LoginPanel loginPanel =  new LoginPanel(this);
        RegisterPanel resgiterPanel = new RegisterPanel(this);

        // Asignar nombres únicos
        cards.add(startPanel.getPanel(), "start");
        cards.add(loginPanel.getPanel(), "login");
        cards.add(resgiterPanel.getPanel(), "register");

        //Iniciar por defecto el panel principal
        frame.setContentPane(cards);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);}

        //Cambio de panel
        public void showPanel(String namePanel){
            cardLayout.show(cards, namePanel);
        }
        //Recuperar la referencia del frame
        public  JFrame getFrame(){
        return frame;
        }
}

