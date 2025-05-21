public class Barang {
    private String nama;
    private int stok;

    public Barang(String nama, int stok) {
        this.nama = nama;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public void tambahStok(int jumlah) {
        stok += jumlah;
    }

    public void kurangiStok(int jumlah) throws StokTidakCukupException {
        if (jumlah > stok) {
            throw new StokTidakCukupException("Stok untuk " + nama + " tidak cukup!");
        }
        stok -= jumlah;
    }

    @Override
    public String toString() {
        return nama + " - " + stok;
    }
}
