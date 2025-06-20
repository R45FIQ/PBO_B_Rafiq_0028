package com.praktikum.gui;

import com.praktikum.data.Item;
import com.praktikum.users.Mahasiswa;
import com.praktikum.main.LoginSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MahasiswaDashboard extends BorderPane {
    private TableView<Item> table;
    private ObservableList<Item> laporanData;
    private Mahasiswa loggedInMahasiswa;

    // Konstruktor menerima Mahasiswa yang login
    public MahasiswaDashboard(Mahasiswa mahasiswa) {
        this.loggedInMahasiswa = mahasiswa;

        VBox topSection = new VBox(10);
        topSection.setPadding(new Insets(10));

        // Gunakan nama mahasiswa yang login untuk greeting
        Label greetingLabel = new Label("Selamat datang, " + loggedInMahasiswa.getNama());
        Label instructionLabel = new Label("Laporkan Barang Hilang/Temuan");

        HBox inputBox = new HBox(5);

        TextField namaField = new TextField();
        namaField.setPromptText("Nama Barang");

        TextField deskripsiField = new TextField();
        deskripsiField.setPromptText("Deskripsi");

        TextField lokasiField = new TextField();
        lokasiField.setPromptText("Lokasi");

        // Hilangkan input statusField
        // TextField statusField = new TextField();
        // statusField.setPromptText("Status");

        Button laporButton = new Button("Laporkan");

        // Hanya tambah 3 field input, tanpa statusField
        inputBox.getChildren().addAll(namaField, deskripsiField, lokasiField, laporButton);

        topSection.getChildren().addAll(greetingLabel, instructionLabel, inputBox);

        Label daftarLabel = new Label("Daftar Laporan Anda");
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Item, String> deskripsiCol = new TableColumn<>("Deskripsi");
        deskripsiCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(namaCol, lokasiCol, deskripsiCol, statusCol);

        laporanData = FXCollections.observableArrayList();
        table.setItems(laporanData);

        VBox centerSection = new VBox(5, daftarLabel, table);
        centerSection.setPadding(new Insets(10));

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            Stage stage = (Stage) getScene().getWindow();
            stage.setScene(new Scene(new LoginPanel(), 400, 300));
        });

        HBox bottomSection = new HBox(logoutButton);
        bottomSection.setPadding(new Insets(10));
        bottomSection.setAlignment(Pos.BOTTOM_LEFT);

        laporButton.setOnAction(e -> {
            String nama = namaField.getText().trim();
            String deskripsi = deskripsiField.getText().trim();
            String lokasi = lokasiField.getText().trim();

            if (!nama.isEmpty() && !lokasi.isEmpty()) {
                String status = "Reported"; // status otomatis

                Item newItem = new Item(nama, deskripsi, lokasi, status);
                laporanData.add(newItem);
                LoginSystem.itemList.add(newItem);

                namaField.clear();
                deskripsiField.clear();
                lokasiField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setHeaderText(null);
                alert.setContentText("Nama dan Lokasi harus diisi!");
                alert.showAndWait();
            }
        });

        setTop(topSection);
        setCenter(centerSection);
        setBottom(bottomSection);
    }
}
