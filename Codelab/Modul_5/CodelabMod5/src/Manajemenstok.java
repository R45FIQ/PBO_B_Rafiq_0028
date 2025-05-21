import java.util.ArrayList;
import java.util.Scanner;

public class Manajemenstok {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        boolean jalan = true;

        while (jalan) {
            System.out.println("\n===== Menu Manajemen Stok =====");
            System.out.println("1. Tambah Barang Baru");
            System.out.println("2. Tampilkan Semua Barang");
            System.out.println("3. Kurangi Stok Barang");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");
            int opsi = input.nextInt();
            input.nextLine(); // konsumsi newline

            switch (opsi) {
                case 1:
                    System.out.print("Masukkan nama barang: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan stok awal: ");
                    int stok = input.nextInt();
                    daftarBarang.add(new Barang(nama, stok));
                    System.out.println("Barang '" + nama + "' berhasil ditambahkan.");
                    break;

                case 2:
                    System.out.println("\n--- Daftar Barang ---");
                    for (int i = 0; i < daftarBarang.size(); i++) {
                        Barang b = daftarBarang.get(i);
                        System.out.println(i + ". Nama: " + b.getNama() + ", Stok: " + b.getStok());
                    }
                    System.out.println("----------------------");
                    break;

                case 3:
                    System.out.println("\n--- Daftar Barang (Pilih untuk Kurangi Stok) ---");
                    for (int i = 0; i < daftarBarang.size(); i++) {
                        Barang b = daftarBarang.get(i);
                        System.out.println(i + ". " + b.getNama() + " (Stok: " + b.getStok() + ")");
                    }

                    System.out.print("Masukkan nomor indeks barang: ");
                    int index = input.nextInt();
                    System.out.print("Masukkan jumlah stok yang akan diambil: ");
                    int jumlah = input.nextInt();

                    try {
                        Barang barang = daftarBarang.get(index);
                        barang.kurangiStok(jumlah);
                        System.out.println("Stok barang '" + barang.getNama() + "' berhasil dikurangi. Sisa stok: " + barang.getStok());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Indeks tidak valid.");
                    } catch (StokTidakCukupException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    jalan = false;
                    System.out.println("Terima kasih! Program berakhir.");
                    break;

                default:
                    System.out.println("Opsi tidak dikenali.");
                    break;
            }
        }

        input.close();
    }
}
