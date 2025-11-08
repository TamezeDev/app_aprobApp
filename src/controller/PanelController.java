package controller;

import view.StartPanel;

import javax.swing.*;

public class PanelController {


    public void startUI(){
        StartPanel startPanel = new StartPanel();
        JFrame frame = new JFrame("Principal");
        frame.setContentPane(startPanel.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
}
}
