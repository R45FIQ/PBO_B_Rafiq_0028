package com.praktikum.users;

import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;  // jangan lupa import ini
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mahasiswa extends User {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String nama, String nim) {
        return nama.equals(getNama()) && nim.equals(getNim());
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil!");
        super.displayInfo();
    }

    // Method reportItem yang sudah membuat Item baru dan menambahkan ke list global LoginSystem.itemList
    public void reportItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan Nama Barang: ");
        String namaBarang = scanner.nextLine();

        System.out.print("Masukkan Deskripsi Barang: ");
        String deskripsiBarang = scanner.nextLine();

        System.out.print("Masukkan Lokasi Terakhir/Ditemukan: ");
        String lokasiTerakhir = scanner.nextLine();

        Item newItem = new Item(namaBarang, deskripsiBarang, lokasiTerakhir, "Reported");
        LoginSystem.itemList.add(newItem);  // Tambah ke list global

        System.out.println("\nTerima kasih, barang telah dilaporkan dengan detail sebagai berikut:");
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Deskripsi Barang: " + deskripsiBarang);
        System.out.println("Lokasi Terakhir/Ditemukan: " + lokasiTerakhir);
        System.out.println("Status: Reported");
    }

    // Method menampilkan laporan barang yang berstatus Reported dari list global LoginSystem.itemList
    public void viewreportItem() {
        boolean adaLaporan = false;

        System.out.println("\nDaftar Barang yang Dilaporkan:");
        for (Item item : LoginSystem.itemList) {
            if ("Reported".equalsIgnoreCase(item.getStatus())) {
                System.out.println("------------------------------");
                System.out.println("Nama Barang     : " + item.getItemName());
                System.out.println("Deskripsi Barang: " + item.getDescription());
                System.out.println("Lokasi Terakhir : " + item.getLocation());
                System.out.println("Status          : " + item.getStatus());
                adaLaporan = true;
            }
        }
        if (!adaLaporan) {
            System.out.println("Belum ada laporan barang.");
        }
        System.out.println("------------------------------");
    }

    @Override
    public void aksesMenu() {
        displayAppMenu();
    }

    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        do {
            System.out.println("\n=== Menu Mahasiswa ===");
            System.out.println("1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Masukkan pilihan: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // buang newline
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine(); // bersihkan input buffer
                continue; // ulangi menu
            }

            switch (pilihan) {
                case 1:
                    reportItem();
                    break;
                case 2:
                    viewreportItem();
                    break;
                case 0:
                    System.out.println("Logout berhasil.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }
}
