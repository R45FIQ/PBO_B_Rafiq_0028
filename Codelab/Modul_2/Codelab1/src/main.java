// Kelas buat nyimpen data hewan
class Hewan {
    // Atribut buat nyimpen nama hewan
    String nama;
    // Atribut buat nyimpen jenis hewan (Mamalia, Reptil, dll.)
    String jenis;
    // Atribut buat nyimpen suara hewan
    String suara;

    // Method buat nampilin info hewan ke layar
    void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Jenis: " + jenis);
        System.out.println("Suara: " + suara);
        System.out.println();
    }
}

// Kelas utama buat ngejalanin program
public class main {
    public static void main(String[] args) {
        // Bikin objek hewan pertama (Kucing)
        Hewan hewan1 = new Hewan();
        hewan1.nama = "Kucing";
        hewan1.jenis = "Mamalia";
        hewan1.suara = "Nyann~~";

        // Bikin objek hewan kedua (Anjing)
        Hewan hewan2 = new Hewan();
        hewan2.nama = "Anjing";
        hewan2.jenis = "Mamalia";
        hewan2.suara = "Woof-Woof!";

        // Nampilin informasi kedua hewan
        hewan1.tampilkanInfo();
        hewan2.tampilkanInfo();
    }
}
