import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class SistemAntreanRS {
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
            
            // Tambahkan null ke dalam deque secara eksplisit untuk memicu NullPointerException
            deques.offer(null);
            
            System.out.println("\nNo Urut Antrean Praktik Dokter Umum");

            int antrean = 1;
            for (String next = deques.pollFirst(); next != null; next = deques.pollFirst()) {
                if (next == null) {
                    throw new NullPointerException("Nama di dalam antrean adalah null.");
                }
                System.out.println(antrean + ". " + next);
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
