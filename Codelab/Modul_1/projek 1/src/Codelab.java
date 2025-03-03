import java.util.Scanner; //mengimpor kelas scanner untuk membaca input dari pengguna
import java.time.LocalDateTime; //mengimpor LocalDateTime unruk mendapatkan wktu sekarang

    public class Codelab {
        public static void main(String[] args) {
            //Deklarasi variabel
        String Nama; //deklarasi untuk menyimpan nama
        String jenisKelamin; //deklarasi untuk menyimpan jenis kelamin
        int tahunLahir; //deklarasi untuk menyimpan tahun lahir
        int umur; //deklarasi untuk menyimpan umur
            Scanner objInput = new Scanner(System.in); //membuat objek scanner membaca input dri pengguna
            System.out.print("Masukkan Nama :"); //menampilkan permintaan input
            Nama = objInput.nextLine(); //membaca input dari pengguna dan menyimpan ke variabel Nama
            System.out.print("Masukkan jenis Kelamin  (L/P):"); //menampilkan permintaan input jenis kelamin
            jenisKelamin = objInput.nextLine(); //membaca input dari pengguna dan menyimpannya di variabel jenisKelamin
            System.out.print("Masukkan Tahun Lahir :"); //menampilkan permintaan input tahun  lahir kepada pengguna
            tahunLahir = objInput.nextInt(); //membaca innput dari pengguna dan menyimpannya di varibel tanggalLahir
            System.out.format( "Nama          : %s \n", Nama); //menampilkan Nama yang sudah di input
            //percabangan untuk jenis kelamin
            if (jenisKelamin.equals("P")) {
                System.out.print("Jenis Kelamin : Perempuan\n"); //menampilkan jenis kelamin perempuan jika pengguna menginput "P"
            }else if (jenisKelamin.equals("L")){
                System.out.print("Jenis Kelamin : Laki-Laki\n"); //menampilkan jenis kelamin Laki-Laki jika pengguna menginput "L"
            }

            umur = LocalDateTime.now().getYear() - tahunLahir ; //untuk menghitung umur
            System.out.format("Umur          : %d Tahun",umur ); //menampilkan umur


}
}
