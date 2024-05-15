import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class latihan {
    public static void main(String[] args) throws NumberFormatException {
        List<Integer> numberList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan angka (atau 'null' untuk menguji pengecualian): ");
        String input = scanner.nextLine();

        try {
            Integer number = parseInput(input);
            addNumberToList(numberList, number);
            System.out.println("Angka berhasil ditambahkan ke dalam list: " + numberList);
        } catch (IllegalArgumentException e) {
            System.err.println("Input tidak valid: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static void addNumberToList(List<Integer> numberList, Integer number) {
        if (number == null) {
            throw new IllegalArgumentException("Number tidak boleh null");
        }
        numberList.add(number);
    }

    private static Integer parseInput(String input) {
        if ("null".equalsIgnoreCase(input)) {
            return null;
        } else {
            if (input.isEmpty() ||!input.matches("\\d+")) {
                throw new NumberFormatException("Input tidak bisa diubah menjadi angka: " + input);
            }
            return Integer.parseInt(input);
        }
    }
}