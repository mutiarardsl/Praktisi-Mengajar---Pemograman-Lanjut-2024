import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class DataSiswa<T extends QueueItem> extends JFrame {
    private JTextArea hadirArea;
    private JTextArea izinArea;
    private JTextArea sakitArea;

    public DataSiswa(List<T> hadirList, List<T> izinList, List<T> sakitList) {
        setTitle("Data Siswa yang Diproses");
        setSize(600, 400);
        setLayout(new GridLayout(3, 1));

        hadirArea = new JTextArea();
        izinArea = new JTextArea();
        sakitArea = new JTextArea();

        hadirArea.setEditable(false);
        izinArea.setEditable(false);
        sakitArea.setEditable(false);

        JScrollPane hadirScrollPane = new JScrollPane(hadirArea);
        JScrollPane izinScrollPane = new JScrollPane(izinArea);
        JScrollPane sakitScrollPane = new JScrollPane(sakitArea);

        add(hadirScrollPane);
        add(izinScrollPane);
        add(sakitScrollPane);

        displayData(hadirList, izinList, sakitList);
        saveDataToFile(hadirList, izinList, sakitList);
    }

    private void displayData(List<T> hadirList, List<T> izinList, List<T> sakitList) {
        hadirArea.setText("Daftar Siswa Hadir:\n");
        for (T item : hadirList) {
            hadirArea.append(item.getNama() + " (Kelas: " + item.getKelas() + ", No Absen: " + item.getNoAbsen() + ", Wali Kelas: " + item.getWaliKelas() + ")\n");
        }

        izinArea.setText("Daftar Siswa Izin:\n");
        for (T item : izinList) {
            izinArea.append(item.getNama() + " (Kelas: " + item.getKelas() + ", No Absen: " + item.getNoAbsen() + ", Wali Kelas: " + item.getWaliKelas() + ")\n");
        }

        sakitArea.setText("Daftar Siswa Sakit:\n");
        for (T item : sakitList) {
            sakitArea.append(item.getNama() + " (Kelas: " + item.getKelas() + ", No Absen: " + item.getNoAbsen() + ", Wali Kelas: " + item.getWaliKelas() + ")\n");
        }
    }

    private void saveDataToFile(List<T> hadirList, List<T> izinList, List<T> sakitList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data_siswa.txt"))) {
            writer.write("Daftar Siswa Hadir:\n");
            for (T item : hadirList) {
                writer.write(item.getNama() + " (Kelas: " + item.getKelas() + ", No Absen: " + item.getNoAbsen() + ", Wali Kelas: " + item.getWaliKelas() + ")\n");
            }

            writer.write("\nDaftar Siswa Izin:\n");
            for (T item : izinList) {
                writer.write(item.getNama() + " (Kelas: " + item.getKelas() + ", No Absen: " + item.getNoAbsen() + ", Wali Kelas: " + item.getWaliKelas() + ")\n");
            }

            writer.write("\nDaftar Siswa Sakit:\n");
            for (T item : sakitList) {
                writer.write(item.getNama() + " (Kelas: " + item.getKelas() + ", No Absen: " + item.getNoAbsen() + ", Wali Kelas: " + item.getWaliKelas() + ")\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}