import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class projectAntrian {
    public static void main(String[] args) {
        Deque<String> deques = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            String pasien;
            while (true) {
                System.out.print("Masukkan nama (atau ketik 'selesai' untuk mengakhiri): ");
                pasien = scanner.nextLine();
                
                if (pasien.equalsIgnoreCase("selesai")) {
                    break;
                }
                
                if (pasien.trim().isEmpty()) {
                    throw new IllegalArgumentException("Nama tidak boleh kosong.");
                }
                
                deques.offer(pasien);
            }
            
            deques.offer(null);
            
            System.out.println("\nNo Urut Antrean Praktik Dokter Umum");

            int antrean = 1;
            for (String next = deques.pollFirst(); next != null; next = deques.pollFirst()) {
                if (next == null) {
                    throw new NullPointerException("Nama di dalam antrean adalah null.");
                }
                System.out.println(antrean + ". " + next);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                date.setDate(date.getDate());
                System.out.println("Tanggal: " + dateFormat.format(date));
                antrean++;
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Kesalahan input: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Terjadi kesalahan: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan yang tidak terduga: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
