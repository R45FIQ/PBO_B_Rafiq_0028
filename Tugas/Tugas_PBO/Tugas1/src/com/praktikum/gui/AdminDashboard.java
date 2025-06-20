package com.praktikum.gui;

import com.praktikum.main.LoginSystem;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import com.praktikum.data.Item;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminDashboard {

    private final Admin admin;

    private TableView<Item> itemTable;
    private TableView<Mahasiswa> mahasiswaTable;

    public AdminDashboard(Admin admin) {
        this.admin = admin;
    }

    public void show(Stage stage) {
        Label greeting = new Label("Halo, Administrator " + admin.getUsername());
        greeting.setPadding(new Insets(5, 0, 10, 0));

        // Table Barang
        itemTable = new TableView<>();
        itemTable.setItems(FXCollections.observableArrayList(LoginSystem.itemList));

        TableColumn<Item, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Item, String> colLokasi = new TableColumn<>("Lokasi");
        colLokasi.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Item, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        itemTable.getColumns().addAll(colNama, colLokasi, colStatus);
        itemTable.setPrefWidth(380);
        itemTable.setPrefHeight(250);

        Button btnClaimed = new Button("Tandai Claimed");
        btnClaimed.setOnAction(e -> tandaiClaimed());

        Button btnLogout = new Button("Logout");
        btnLogout.setOnAction(e -> {
            LoginPanel loginPanel = new LoginPanel();
            Scene loginScene = new Scene(loginPanel, 400, 300);
            stage.setScene(loginScene);
        });

        HBox actionButtons = new HBox(10, btnClaimed, btnLogout);

        VBox barangBox = new VBox(new Label("Laporan Barang"), itemTable, actionButtons);
        barangBox.setSpacing(8);

        // Table Mahasiswa
        mahasiswaTable = new TableView<>();
        mahasiswaTable.setItems(FXCollections.observableArrayList(
                LoginSystem.userList.stream()
                        .filter(u -> u instanceof Mahasiswa)
                        .map(u -> (Mahasiswa) u)
                        .toList()
        ));

        TableColumn<Mahasiswa, String> colNamaMhs = new TableColumn<>("Nama");
        colNamaMhs.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Mahasiswa, String> colNim = new TableColumn<>("NIM");
        colNim.setCellValueFactory(new PropertyValueFactory<>("nim"));

        mahasiswaTable.getColumns().addAll(colNamaMhs, colNim);
        mahasiswaTable.setPrefWidth(380);
        mahasiswaTable.setPrefHeight(250);

        TextField namaField = new TextField();
        namaField.setPromptText("Nama Mahasiswa");

        TextField nimField = new TextField();
        nimField.setPromptText("NIM");

        Button btnTambah = new Button("Tambah");
        btnTambah.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnTambah.setOnAction(e -> tambahMahasiswa(namaField, nimField));

        Button btnHapus = new Button("Hapus");
        btnHapus.setOnAction(e -> hapusMahasiswa());

        HBox inputMhsBox = new HBox(5, btnTambah, btnHapus, namaField, nimField);
        inputMhsBox.setAlignment(Pos.CENTER_LEFT);

        VBox mahasiswaBox = new VBox(new Label("Data Mahasiswa"), mahasiswaTable, inputMhsBox);
        mahasiswaBox.setSpacing(8);

        HBox mainContent = new HBox(15, barangBox, mahasiswaBox);
        mainContent.setPadding(new Insets(10));

        VBox root = new VBox(5, greeting, mainContent);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Lost & Found Kampus");
        stage.show();
    }

    private void tandaiClaimed() {
        Item selected = itemTable.getSelectionModel().getSelectedItem();
        if (selected != null && "Reported".equalsIgnoreCase(selected.getStatus())) {
            selected.setStatus("Claimed");
            itemTable.refresh();
            showAlert("Sukses", "Barang ditandai sebagai 'Claimed'.");
        } else {
            showAlert("Gagal", "Pilih barang dengan status 'Reported'.");
        }
    }

    private void tambahMahasiswa(TextField namaField, TextField nimField) {
        String nama = namaField.getText().trim();
        String nim = nimField.getText().trim();

        if (nama.isEmpty() || nim.isEmpty()) {
            showAlert("Gagal", "Nama dan NIM tidak boleh kosong.");
            return;
        }

        boolean exists = LoginSystem.userList.stream()
                .anyMatch(u -> u instanceof Mahasiswa && u.getNim().equals(nim));

        if (exists) {
            showAlert("Gagal", "Mahasiswa dengan NIM ini sudah ada.");
            return;
        }

        Mahasiswa mhs = new Mahasiswa(nama, nim);
        LoginSystem.userList.add(mhs);
        mahasiswaTable.getItems().add(mhs);
        namaField.clear();
        nimField.clear();
        showAlert("Sukses", "Mahasiswa ditambahkan.");
    }

    private void hapusMahasiswa() {
        Mahasiswa selected = mahasiswaTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            mahasiswaTable.getItems().remove(selected);
            LoginSystem.userList.removeIf(u -> u instanceof Mahasiswa && u.getNim().equals(selected.getNim()));
            showAlert("Sukses", "Mahasiswa dihapus.");
        } else {
            showAlert("Gagal", "Pilih mahasiswa yang akan dihapus.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
