package view;


import controller.PanelController;
import model.*;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;


public class UserPanel {
    private JPanel userPanel;
    private JButton btnLogout;
    private JButton btnConfig;
    private JButton btnDataBase;
    private JLabel labelName;
    private JButton nuevoTestButton;
    private JButton corregirFallosButton;
    private JSpinner spModules;
    private JLabel labelPorcentajeAprobados;
    private JLabel labelNumeroCorregir;
    private JLabel labelNotaMedia;
    private JButton btnExit;
    private JButton btnMin;
    private JPanel mainBar;
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
        WindowUtils.attachExitButton(btnExit);
        WindowUtils.attachMinimizeButton(btnMin, this.getPanel());
        WindowUtils.enableWindowDrag(mainBar);
        loadStudiesSpinner();
        //check email user?
        if (email != null && !email.isEmpty()) {
            saveUserData(email);
            labelName.setText(firstName);
            getAverageTest(email);
            getPassedTest(email);
            getNumberFailedQuestion(email);
        }
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
        String[] formations = {"Sistemas inf.", "Programación", "Entornos", "Base datos", "ITP", "Lenguajes"};
        ReverseSpinnerListModel model = new ReverseSpinnerListModel(formations);
        spModules.setModel(model);
        styleSpinner(spModules);
    }

    //Save userdata
    private void saveUserData(String email) {
        UserData userData = new UserData();
        String jsonResult = userData.getUserData(email);

        if (jsonResult == null || jsonResult.isEmpty()) {
            System.out.println("Respuesta vacía, no se pudo cargar el usuario");
            return;
        }

        JSONObject obj = new JSONObject(jsonResult);
        firstName = obj.getString("first_name");
        surname = obj.getString("surname");
        this.email = obj.getString("email");
        study = obj.getString("study");
    }

    //get/set average test
    private void getAverageTest(String email) {
        TestData testData = new TestData();
        Double averageTest = testData.getAverageTest(email);
        if (averageTest == null) {
            System.out.println("No se pudo cargar la media de los test");
            return;
        }
        labelNotaMedia.setText("Nota media global: " + String.format("%.1f", averageTest));
    }

    //get/set test passed
    private void getPassedTest(String email) {
        TestData testData = new TestData();
        Double passedTest = testData.getPassedTest(email);
        if (passedTest == null) {
            System.out.println("No se pudo cargar el número de test aprobados");
            return;
        }
        labelPorcentajeAprobados.setText("Porcentaje de test aprobados: " + String.format("%.1f%%", passedTest));
    }

    //get/set number failed questions
    private void getNumberFailedQuestion(String email) {
        QuestionData questionData = new QuestionData();
        Double numberErrors = questionData.getNumFailedQuestions(email);
        if (numberErrors == null) {
            System.out.println("No se pudo obtener número de preguntas erróneas");
            return;
        }
        labelNumeroCorregir.setText("Número de preguntas por corregir: " + String.format("%.0f", numberErrors));
    }

    //Getters & Setters
    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getStudy() {
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
            frame.setSize(1024, 800);
            frame.setLocationRelativeTo(null); // centrar pantalla

            UserPanel userPanel = new UserPanel();
            frame.setContentPane(userPanel.getPanel());

            frame.setVisible(true);
        });
    }
}

