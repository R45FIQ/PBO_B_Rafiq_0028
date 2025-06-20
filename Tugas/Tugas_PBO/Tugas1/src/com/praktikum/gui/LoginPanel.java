package com.praktikum.gui;

import com.praktikum.main.LoginSystem;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.Admin;
import com.praktikum.users.User;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class LoginPanel extends VBox {
    public ComboBox<String> roleComboBox;
    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;
    public Label errorLabel;

    public LoginPanel() {
        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label title = new Label("Login Sistem Lost & Found");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Mahasiswa", "Admin");
        roleComboBox.setValue("Mahasiswa");

        usernameField = new TextField();
        usernameField.setPromptText("Nama / Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("NIM / Password");

        loginButton = new Button("Login");

        errorLabel = new Label("Login gagal, periksa kredensial.");
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER);
        formBox.setMaxWidth(300);
        formBox.getChildren().addAll(
                roleComboBox,
                usernameField,
                passwordField,
                loginButton,
                errorLabel
        );

        getChildren().addAll(title, formBox);

        loginButton.setOnAction(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String role = roleComboBox.getValue();

        if (role.equals("Mahasiswa")) {
            Mahasiswa loggedInMhs = null;


            if (username.equals("Muhammad Sulaiman Rafiq") && password.equals("202410370110028")) {
                loggedInMhs = new Mahasiswa("Muhammad Sulaiman Rafiq", "202410370110028");
            } else {
                // Cari di daftar mahasiswa LoginSystem.userList
                for (User u : LoginSystem.userList) {
                    if (u instanceof Mahasiswa mhs) {
                        if (mhs.getNama().equals(username) && mhs.getNim().equals(password)) {
                            loggedInMhs = mhs;
                            break;
                        }
                    }
                }
            }

            if (loggedInMhs != null) {
                Stage stage = (Stage) getScene().getWindow();
                MahasiswaDashboard dashboard = new MahasiswaDashboard(loggedInMhs);
                Scene nextScene = new Scene(dashboard, 600, 400);
                stage.setScene(nextScene);
            } else {
                errorLabel.setVisible(true);
            }

        } else if (role.equals("Admin")) {
            if (username.equals("admin028") && password.equals("password028")) {
                Stage stage = (Stage) getScene().getWindow();
                Admin admin = new Admin("Admin Name", "001", username, password);
                AdminDashboard dashboard = new AdminDashboard(admin);
                dashboard.show(stage);
            } else {
                errorLabel.setVisible(true);
            }
        }
    }
}
