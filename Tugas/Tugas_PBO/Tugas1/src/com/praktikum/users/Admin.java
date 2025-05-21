package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void aksesMenu() {
        displayAppMenu();
    }

    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        do {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
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
                    manageItems();
                    break;
                case 2:
                    manageUsers();
                    break;
                case 0:
                    System.out.println("Logout berhasil.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    @Override
    public void manageItems() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        do {
            System.out.println("\n--- Kelola Laporan Barang ---");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil (Claimed)");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1:
                    if (LoginSystem.itemList.isEmpty()) {
                        System.out.println("Belum ada laporan barang.");
                    } else {
                        System.out.println("Daftar Laporan Barang:");
                        int idx = 0;
                        for (Item item : LoginSystem.itemList) {
                            System.out.println(idx + ". Nama: " + item.getItemName() +
                                    ", Deskripsi: " + item.getDescription() +
                                    ", Lokasi: " + item.getLocation() +
                                    ", Status: " + item.getStatus());
                            idx++;
                        }
                    }
                    break;

                case 2:
                    if (LoginSystem.itemList.isEmpty()) {
                        System.out.println("Belum ada laporan barang.");
                        break;
                    }

                    System.out.println("Daftar Barang Berstatus 'Reported':");
                    int countReported = 0;
                    for (int i = 0; i < LoginSystem.itemList.size(); i++) {
                        Item item = LoginSystem.itemList.get(i);
                        if ("Reported".equalsIgnoreCase(item.getStatus())) {
                            System.out.println(i + ". " + item.getItemName() + " - " + item.getDescription() + " - " + item.getLocation());
                            countReported++;
                        }
                    }
                    if (countReported == 0) {
                        System.out.println("Tidak ada barang dengan status 'Reported'.");
                        break;
                    }

                    try {
                        System.out.print("Masukkan nomor indeks barang yang ingin ditandai: ");
                        int inputIdx = scanner.nextInt();
                        scanner.nextLine();

                        Item selectedItem = LoginSystem.itemList.get(inputIdx);

                        if (!"Reported".equalsIgnoreCase(selectedItem.getStatus())) {
                            System.out.println("Barang pada indeks tersebut tidak berstatus 'Reported'.");
                        } else {
                            selectedItem.setStatus("Claimed");
                            System.out.println("Status barang berhasil diubah menjadi 'Claimed'.");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Input harus berupa angka!");
                        scanner.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Indeks yang dimasukkan tidak valid.");
                    }
                    break;

                case 0:
                    System.out.println("Kembali ke menu utama.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    @Override
    public void manageUsers() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        do {
            System.out.println("\n--- Kelola Data Mahasiswa ---");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nama Mahasiswa: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan NIM Mahasiswa: ");
                    String nim = scanner.nextLine();

                    boolean exists = false;
                    for (User u : LoginSystem.userList) {
                        if (u instanceof Mahasiswa && u.getNim().equals(nim)) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        System.out.println("NIM sudah terdaftar.");
                    } else {
                        Mahasiswa mhs = new Mahasiswa(nama, nim);
                        LoginSystem.userList.add(mhs);
                        System.out.println("Mahasiswa berhasil ditambahkan.");
                    }
                    break;

                case 2:
                    System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
                    String nimHapus = scanner.nextLine();

                    Iterator<User> iter = LoginSystem.userList.iterator();
                    boolean removed = false;

                    while (iter.hasNext()) {
                        User u = iter.next();
                        if (u instanceof Mahasiswa && u.getNim().equals(nimHapus)) {
                            iter.remove();
                            removed = true;
                            break;
                        }
                    }

                    if (removed) {
                        System.out.println("Mahasiswa berhasil dihapus.");
                    } else {
                        System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
                    }
                    break;

                case 0:
                    System.out.println("Kembali ke menu utama.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Admin berhasil!");
        System.out.println("Nama: " + getNama());
        System.out.println("NIM : " + getNim());
    }
}
