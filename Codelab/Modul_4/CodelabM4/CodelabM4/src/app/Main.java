import perpustakaan.*;

public class Main {
    public static void main(String[] args) {
        // Membuat objek buku
        Buku buku1 = new NonFiksi("Madilog", "Tan Malaka", "Sejarah & Ilmu Pengetahuan");
        Buku buku2 = new Fiksi("Hainuwele: Sang Putri Kelapa", "Lilis Hu", "Dongeng");

        // Menampilkan info buku
        buku1.displayInfo();
        buku2.displayInfo();
        System.out.println();

        // Membuat anggota
        Anggota anggota1 = new Anggota("Wahyu Andika", "B075");
        Anggota anggota2 = new Anggota("Ega Faiz", "A047");

        // Menampilkan info anggota
        anggota1.displayInfo();
        anggota2.displayInfo();
        System.out.println();

        // Anggota meminjam buku
        anggota1.pinjamBuku(buku1);
        anggota2.pinjamBuku(buku2);
        System.out.println();

        // Anggota mengembalikan buku
        anggota1.kembalikanBuku();
        anggota2.kembalikanBuku();
    }
}
