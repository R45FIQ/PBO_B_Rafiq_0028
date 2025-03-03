
//public class Main {
    //public static void main(String[] args) {
       // System.out.println("Hello, World!");
    //}
//}

    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("waktu sekarang adalah : " + timeNow.format(formatter));
        System.out.println("Bulan sekarang adalah : " + timeNow.getMonth());

    }
}
