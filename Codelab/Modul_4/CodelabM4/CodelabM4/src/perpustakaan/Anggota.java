package perpustakaan;

public class Anggota implements Peminjaman {
    private String nama;
    private String id;
    private Buku[] bukuPinjaman;
    private int jumlahPinjaman;

    public Anggota(String nama, String id) {
        this.nama = nama;
        this.id = id;
        this.bukuPinjaman = new Buku[10]; // maksimal 10 buku dipinjam
        this.jumlahPinjaman = 0;
    }

    @Override
    public void pinjamBuku(Buku buku) {
        if (jumlahPinjaman < bukuPinjaman.length) {
            bukuPinjaman[jumlahPinjaman] = buku;
            jumlahPinjaman++;
            if (buku instanceof BukuFiksi) {
                System.out.println(nama + " meminjam buku \"" + buku.judul + "\" selama 7 hari.");
            } else {
                System.out.println(nama + " meminjam buku berjudul: " + buku.judul);
            }
        } else {
            System.out.println(nama + " tidak bisa meminjam lebih banyak buku.");
        }
    }

    @Override
    public void kembalikanBuku(Buku buku) {
        boolean found = false;
        for (int i = 0; i < jumlahPinjaman; i++) {
            if (bukuPinjaman[i] == buku) {
                found = true;
                // Geser array ke kiri setelah buku dikembalikan
                for (int j = i; j < jumlahPinjaman - 1; j++) {
                    bukuPinjaman[j] = bukuPinjaman[j+1];
                }
                bukuPinjaman[jumlahPinjaman-1] = null;
                jumlahPinjaman--;
                System.out.println(nama + " mengembalikan buku berjudul: " + buku.judul);
                break;
            }
        }
        if (!found) {
            System.out.println(nama + " tidak meminjam buku tersebut.");
        }
    }

    public void displayInfo() {
        System.out.println("Anggota: " + nama + " (ID: " + id + ")");
    }
}
