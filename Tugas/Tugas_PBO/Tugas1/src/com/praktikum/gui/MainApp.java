package com.praktikum.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Membuat instance LoginPanel
        LoginPanel loginPanel = new LoginPanel();

        // Membuat scene dengan ukuran 400x300 (bisa disesuaikan)
        Scene scene = new Scene(loginPanel, 400, 300);

        // Set judul window
        primaryStage.setTitle("Aplikasi Pelaporan Barang");

        // Set scene ke stage
        primaryStage.setScene(scene);

        // Menampilkan stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
