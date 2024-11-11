package org.example.otp2laksyviikko3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label languageLabel;

    @FXML
    Button saveButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    private String fName;
    private String lName;
    private String email;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employeeotp2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "seki281001";

    private static String TABLE;

    ResourceBundle bundle;

    @FXML
    private void handleLanguageChange() {
        String selectedLanguage = languageComboBox.getValue();

        if (selectedLanguage.equals("English")) {
            switchToEnglish();
            TABLE = "emplooyee_fa";
           fName = firstNameField.getText();
           lName = lastNameField.getText();
           email = emailField.getText();
        } else if (selectedLanguage.equals("Persian")) {
            switchToPersian();
            TABLE = "emplooyee_fa";
            fName = firstNameField.getText();
            lName = lastNameField.getText();
            email = emailField.getText();
        } else if (selectedLanguage.equals("Japanese")){
            switchToJapanese();
            TABLE = "emplooyee_jp";
            fName = firstNameField.getText();
            lName = lastNameField.getText();
            email = emailField.getText();

        }

    }
    private void updateUIText() {
        languageLabel.setText(bundle.getString("language.text"));
        firstNameLabel.setText(bundle.getString("fname.text"));
        lastNameLabel.setText(bundle.getString("lname.text"));
        emailLabel.setText(bundle.getString("email.text"));
        saveButton.setText(bundle.getString("save.text"));
    }

    @FXML
    private void switchToEnglish() {
        setLanguage(Locale.ENGLISH);
    }

    @FXML
    private void switchToFrench() {
        setLanguage(Locale.FRENCH);
    }

    @FXML
    private void switchToJapanese() {
        setLanguage(new Locale("jp"));
    }

    private void setLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle("bundle", locale);
        updateUIText();
    }

    @FXML
    private void switchToPersian() {
        setLanguage(new Locale("fa"));
    }

    @FXML
    private void sendSQL(){
        try {
            // Load and register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                // SQL query to insert records into the employees table
                String sql = "INSERT INTO " + TABLE + " (emp_id, firstname, lastname, email) VALUES (?, ?, ?, ?)";

                // Prepare the SQL statement
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Insert employee records
                    statement.setInt(1, 2);  // employee ID
                    statement.setString(2, fName);  // English name
                    statement.setString(3, lName);
                    statement.setString(3, email);
                    statement.executeUpdate();


                    System.out.println("Records inserted successfully.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}