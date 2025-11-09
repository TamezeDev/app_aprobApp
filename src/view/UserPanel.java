package view;


import controller.PanelController;
import model.UserData;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;



public class UserPanel {
    private JPanel userPanel;
    private JButton btlExit;
    private JButton btnConfig;
    private JButton button1;
    private JLabel labelName;
    private JPopupMenu popupMenu;

    private String firstName, surname, email, study;

//Main function
    public UserPanel(PanelController controller, String email) {

        this.email = email;
        initUI();
    }


    public UserPanel() {
        initUI();
    }

    public JPanel getPanel() {
        return userPanel;
    }

    private void initUI() {
        if (email != null && !email.isEmpty()) {
            saveUserData(email);
            labelName.setText(firstName);
        }
    }

//Save userdata
    private void saveUserData(String email) {
        UserData userData = new UserData();
        String jsonResult = userData.getUserData(email);

        if (jsonResult == null || jsonResult.isEmpty()) {
            System.err.println("Respuesta vacía, no se pudo cargar el usuario");
            return;
        }

        JSONObject obj = new JSONObject(jsonResult);
        firstName = obj.getString("first_name"); // ← nombre correcto
        surname = obj.getString("surname");      // ← nombre correcto
        this.email = obj.getString("email");
        study = obj.getString("study");
    }

    //Getters & Setters
    public String getFirstName(){
        return firstName;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }
    public String getStudy(){
        return study;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setStudy(String study) {
        this.study = study;
    }

    // 🔹 Método main para pruebas
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel de Usuario - Pruebas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null); // centrar pantalla

            UserPanel userPanel = new UserPanel();
            frame.setContentPane(userPanel.getPanel());

            frame.setVisible(true);
        });
    }
}

