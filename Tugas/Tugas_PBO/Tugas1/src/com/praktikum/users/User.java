package com.praktikum.users;

// Superclass com.praktikum.users.User
public abstract class User {
    private String nama;
    private String nim;

    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    // Setter
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    // Method login
    public abstract boolean login(String nama, String nim);

    // Method aksesMenu
    public abstract void aksesMenu();

    // Method displayAppMenu yang perlu diimplementasikan oleh subclass
    public abstract void displayAppMenu();

    // Method displayInfo
    public void displayInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM : " + nim);
    }
}
