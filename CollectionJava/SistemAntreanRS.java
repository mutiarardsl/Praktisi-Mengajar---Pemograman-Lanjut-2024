import java.util.Deque;
import java.util.LinkedList;

public class SistemAntreanRS {
    public static void main(String[] args) {
        Deque<String> deques = new LinkedList<>();
            deques.offer("Tiara");
            deques.offer("Tirta");
            deques.offer("Tirtul");

            System.out.println("No Urut Antrean Praktik Dokter Umum");

            int nomor = 1;
            for(String next = deques.pollFirst(); next != null; next = deques.pollFirst()){
            System.out.println(nomor + ". " + next);
            nomor++;
            }
    }
}
