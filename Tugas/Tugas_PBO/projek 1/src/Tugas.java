import java.util.Scanner;

public class Tugas {
    public static void main(String[] args) {
        int pilihan; //Deklarasi variabel
        String usernameAdmin = "admin028"; //username untuk admin
        String passwordAdmin= "password028"; //password untuk admin
        String namaMahasiswa = "Muhammad sulaiman rafiq"; //nama untuk mahasiswa
        String NimMahasiswa = "202410370110028"; //Nim untuk mahasiswa
        String username =""; //variabel untuk menyimpan input dari pengguna
        String password =""; //variabel untuk menyimpan input dari pengguna

        //menampilkan opsi login
        Scanner objInput = new Scanner(System.in); //membuat scanner membaca input
        System.out.println(" Pilih login:\n 1. Admin\n 2. Mahasiswa\n"); //menampilkan opsi login
        System.out.print("Masukkan pilihan : ");
        pilihan = objInput.nextInt();
        objInput.nextLine();
        if (pilihan == 1) { //untuk inputan admin
            System.out.print("Masukkan username : ");
            username = objInput.next();
            System.out.print("Masukkan password : ");
            password = objInput.next();

            if (username.equals(usernameAdmin) && password.equals(passwordAdmin)) {
                System.out.print("Login admin berhasil");
            } else {
                System.out.print("Login gagal!!username atau password salah.\n");
            }
        }else if (pilihan == 2) { //untuk pilihan mahasiswa
            System.out.print("Masukkan Nama : ");
            username = objInput.nextLine();
            System.out.print("Masukkan NIM : ");
            password = objInput.next();

            if (username.equals(namaMahasiswa) && password.equals(NimMahasiswa)) {
                System.out.print("Login mahasiswa berhasil!!!");
            } else {
                System.out.print("Login gagal!! Nama atau Nim salah. ");
            }
        }
    }
}
