package com.praktikum.main;

import com.praktikum.data.Item;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginSystem {
    // Penyimpanan data global
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> itemList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tambah data default
        userList.add(new Admin("Admin Sistem", "000", "admin028", "password028"));
        userList.add(new Mahasiswa("Muhammad Sulaiman Rafiq", "202410370110028"));
        userList.add(new Mahasiswa("Budi Santoso", "202410370110029"));

        while (true) {
            int pilihan = -1;
            System.out.println("\n===== Sistem Pelaporan Barang Hilang/Temuan =====");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // buang newline

                if (pilihan == 1) {
                    System.out.print("Masukkan username: ");
                    String username = scanner.nextLine();
                    System.out.print("Masukkan password: ");
                    String password = scanner.nextLine();

                    User admin = findUser(1, username, password);
                    if (admin != null) {
                        admin.displayInfo();
                        admin.aksesMenu(); // gunakan aksesMenu agar konsisten
                    } else {
                        System.out.println("Login Admin gagal! Username atau password salah.");
                    }

                } else if (pilihan == 2) {
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();

                    User mahasiswa = findUser(2, nama, nim);
                    if (mahasiswa != null) {
                        mahasiswa.displayInfo();
                        mahasiswa.aksesMenu();
                    } else {
                        System.out.println("Login Mahasiswa gagal! Nama atau NIM tidak ditemukan.");
                    }

                } else if (pilihan == 3) {
                    System.out.println("Terima kasih telah menggunakan sistem. Keluar...");
                    break; // keluar dari while
                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih 1, 2, atau 3.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka! Silakan coba lagi.");
                scanner.nextLine(); // bersihkan buffer
            }
        }

        scanner.close();
    }

    // Method login terpusat
    public static User findUser(int tipeUser, String input1, String input2) {
        for (User u : userList) {
            if (tipeUser == 1 && u instanceof Admin) {
                if (u.login(input1, input2)) {
                    return u;
                }
            } else if (tipeUser == 2 && u instanceof Mahasiswa) {
                if (u.login(input1, input2)) {
                    return u;
                }
            }
        }
        return null;
    }
}
