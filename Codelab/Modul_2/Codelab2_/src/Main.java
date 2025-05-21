// Kelas buat nyimpen data rekening bank
class RekeningBank {
    // Atribut buat nyimpen nomor rekening
    long nomorRekening;
    // Atribut buat nyimpen nama pemilik rekening
    String namaPemilik;
    // Atribut buat nyimpen saldo rekening
    double saldo;

    // Method buat nampilin info rekening ke layar
    void tampilkanInfo() {
        System.out.println("Nomor Rekening: " + nomorRekening);
        System.out.println("Nama Pemilik: " + namaPemilik);
        // Nampilin saldo dalam format Rupiah yang lebih rapi
        System.out.printf("Saldo: Rp%,.2f%n", saldo);
        System.out.println();
    }

    // Method buat nyetor uang ke rekening
    void setorUang(double jumlah) {
        saldo += jumlah; // Saldo ditambahin sesuai jumlah yang disetor
        // Nampilin info transaksi biar tahu saldo setelah setor
        System.out.printf("%s menyetor Rp%,.2f. Saldo sekarang: Rp%,.2f%n", namaPemilik, jumlah, saldo);
    }

    // Method buat narik uang dari rekening
    void tarikUang(double jumlah) {
        // Cek dulu, saldo cukup atau enggak buat ditarik
        if (jumlah > saldo) {
            System.out.println(namaPemilik + " mencoba menarik Rp" + jumlah + " tapi saldo tidak mencukupi!");
        } else {
            saldo -= jumlah; // Kalo cukup, saldo dikurangi sesuai jumlah yang ditarik
            // Nampilin info transaksi setelah penarikan
            System.out.printf("%s menarik Rp%,.2f. Saldo sekarang: Rp%,.2f%n", namaPemilik, jumlah, saldo);
        }
    }
}

// Kelas utama yang ngejalanin program
public class Main {
    public static void main(String[] args) {
        // Buat objek rekening pertama (punya Maharan)
        RekeningBank rekening1 = new RekeningBank();
        rekening1.nomorRekening = 2023031911239L;
        rekening1.namaPemilik = "Maharan";
        rekening1.saldo = 9500000;

        // Buat objek rekening kedua (punya Aldian)
        RekeningBank rekening2 = new RekeningBank();
        rekening2.nomorRekening = 2023031911307L;
        rekening2.namaPemilik = "Aldian";
        rekening2.saldo = 10000000;

        // Nampilin info rekening sebelum transaksi
        rekening1.tampilkanInfo();
        rekening2.tampilkanInfo();

        // Maharan nyetor Rp700.000 ke rekeningnya
        rekening1.setorUang(700000);
        // Aldian narik Rp1.500.000 dari rekeningnya
        rekening2.tarikUang(1500000);

        System.out.println();

        // Nampilin info rekening setelah transaksi
        rekening1.tampilkanInfo();
        rekening2.tampilkanInfo();
    }
}
