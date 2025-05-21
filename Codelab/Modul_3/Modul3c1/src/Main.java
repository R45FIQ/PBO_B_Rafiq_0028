class KarakterGame {
    protected String nama; // Nama karakter dalam game
    protected int kesehatan; // Kesehatan karakter

    // Constructor untuk menginisialisasi nama dan kesehatan
    public KarakterGame(String nama, int kesehatan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
    }

    // Getter untuk mendapatkan nama karakter
    public String getNama() {
        return nama;
    }

    // Getter untuk mendapatkan nilai kesehatan karakter
    public int getKesehatan() {
        return kesehatan;
    }

    // Metode serang yang akan dioverride oleh subclass
    public void serang(KarakterGame target) {
        System.out.println(nama + " menyerang " + target.getNama());
    }
}

// Subclass Pahlawan yang mewarisi KarakterGame
class Pahlawan extends KarakterGame {
    public Pahlawan(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        // Menampilkan pesan serangan dengan Orbital Strike
        System.out.println(nama + " menyerang " + target.getNama() + " menggunakan Orbital Strike!");
        // Mengurangi kesehatan target sebesar 20 poin
        target.kesehatan -= 20;
        // Menampilkan kesehatan target setelah diserang
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }
}

// Subclass Musuh yang mewarisi KarakterGame
class Musuh extends KarakterGame {
    public Musuh(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        // Menampilkan pesan serangan dengan Snake Bite
        System.out.println(nama + " menyerang " + target.getNama() + " menggunakan Snake Bite!");
        // Mengurangi kesehatan target sebesar 15 poin
        target.kesehatan -= 15;
        // Menampilkan kesehatan target setelah diserang
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }
}

// Kelas utama untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        // Membuat objek karakter umum, pahlawan, dan musuh
        KarakterGame karakterUmum = new KarakterGame("Karakter Umum", 100);
        Pahlawan brimstone = new Pahlawan("Brimstone", 150);
        Musuh viper = new Musuh("Viper", 200);

        // Menampilkan status awal karakter
        System.out.println("Status awal:");
        System.out.println(brimstone.getNama() + " memiliki kesehatan: " + brimstone.getKesehatan());
        System.out.println(viper.getNama() + " memiliki kesehatan: " + viper.getKesehatan());

        // Simulasi pertarungan
        brimstone.serang(viper);
        viper.serang(brimstone);
    }
}
