import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class TampilanGui<T extends QueueItem> extends JFrame {
    private Deque<T> deques = new LinkedList<>();
    private List<T> completedQueue = new ArrayList<>();
    private JTextField namaField;
    private JTextField kelasField;
    private JTextField noAbsenField;
    private JTextField waliKelasField;
    private JRadioButton hadirButton;
    private JRadioButton izinButton;
    private JRadioButton sakitButton;
    private JTextArea DataArea;
    private JTextArea HadirArea;
    private JTextArea IjinArea;
    private JTextArea SakitArea;
    private JLabel currentPatientLabel;

    public TampilanGui() {
        // Set up the frame
        setTitle("Sistem Absensi Siswa");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create login panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(20);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(userField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);

        add(loginPanel, BorderLayout.CENTER);

        // Add login button listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                if (username.equals("admin") && password.equals("password")) {
                    remove(loginPanel);
                    initializeMainPanel();
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(TampilanGui.this, "Login gagal! Username atau password salah.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initializeMainPanel() {
        // Create main panel components
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel nameLabel = new JLabel("Masukkan nama:");
        inputPanel.add(nameLabel, gbc);

        namaField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(namaField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel kelasLabel = new JLabel("Kelas:");
        inputPanel.add(kelasLabel, gbc);

        kelasField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(kelasField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel noAbsenLabel = new JLabel("No Absen:");
        inputPanel.add(noAbsenLabel, gbc);

        noAbsenField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(noAbsenField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel waliKelasLabel = new JLabel("Wali Kelas:");
        inputPanel.add(waliKelasLabel, gbc);

        waliKelasField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(waliKelasField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        JLabel statusLabel = new JLabel("Keterangan:");
        inputPanel.add(statusLabel, gbc);

        JPanel statusPanel = new JPanel();
        hadirButton = new JRadioButton("Hadir");
        izinButton = new JRadioButton("Izin");
        sakitButton = new JRadioButton("Sakit");

        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(hadirButton);
        statusGroup.add(izinButton);
        statusGroup.add(sakitButton);

        statusPanel.add(hadirButton);
        statusPanel.add(izinButton);
        statusPanel.add(sakitButton);

        gbc.gridx = 1;
        inputPanel.add(statusPanel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addButton = new JButton("Tambah");
        inputPanel.add(addButton, gbc);

        // Create bottom panel for finish and clear buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton processButton = new JButton("Proses Data");
        bottomPanel.add(processButton);

        JButton resetButton = new JButton("Reset Data");
        bottomPanel.add(resetButton);

        // Create output area
        DataArea = new JTextArea(10, 30);
        DataArea.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(DataArea);

        HadirArea = new JTextArea(3, 30);
        HadirArea.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(HadirArea);

        IjinArea = new JTextArea(3, 30);
        IjinArea.setEditable(false);
        JScrollPane scrollPane3 = new JScrollPane(IjinArea);

        SakitArea = new JTextArea(3, 30);
        SakitArea.setEditable(false);
        JScrollPane scrollPane4 = new JScrollPane(SakitArea);

        currentPatientLabel = new JLabel("Data sedang diproses: ");
        JPanel statusDisplayPanel = new JPanel(new BorderLayout());
        statusDisplayPanel.add(currentPatientLabel, BorderLayout.NORTH);
        statusDisplayPanel.add(scrollPane2, BorderLayout.CENTER);
        statusDisplayPanel.add(scrollPane3, BorderLayout.SOUTH);
        statusDisplayPanel.add(scrollPane4, BorderLayout.WEST);

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane1, BorderLayout.WEST);
        add(scrollPane1, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add button listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahData();
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesData();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetData();
            }
        });
    }

    private void tambahData() {
        String nama = namaField.getText().trim();
        String kelas = kelasField.getText().trim();
        String noAbsen = noAbsenField.getText().trim();
        String waliKelas = waliKelasField.getText().trim();
        String status = "";

        if (hadirButton.isSelected()) {
            status = "Hadir";
        } else if (izinButton.isSelected()) {
            status = "Izin";
        } else if (sakitButton.isSelected()) {
            status = "Sakit";
        }

        if (nama.isEmpty() || kelas.isEmpty() || noAbsen.isEmpty() || waliKelas.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(TampilanGui.this, "Semua field harus diisi.", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
        } else {
            T item = (T) new QueueItem(nama, kelas, noAbsen, waliKelas);  // Cast to generic type T
            item.setStatus(status);
            deques.offer(item);
            namaField.setText("");
            kelasField.setText("");
            noAbsenField.setText("");
            waliKelasField.setText("");
            hadirButton.setSelected(false);
            izinButton.setSelected(false);
            sakitButton.setSelected(false);
            displayData();
        }
    }

    private void prosesData() {
        if (deques.isEmpty()) {
            JOptionPane.showMessageDialog(TampilanGui.this, "Tidak ada data untuk diproses.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Buat list untuk menyimpan data siswa berdasarkan kategori
        List<T> hadirList = new ArrayList<>();
        List<T> izinList = new ArrayList<>();
        List<T> sakitList = new ArrayList<>();
        
        // Proses semua data antrian
        while (!deques.isEmpty()) {
            T item = deques.poll();
            completedQueue.add(item); // Tambahkan ke daftar yang telah diproses
            switch (item.getStatus()) {
                case "Hadir":
                    hadirList.add(item);
                    break;
                case "Izin":
                    izinList.add(item);
                    break;
                case "Sakit":
                    sakitList.add(item);
                    break;
            }
        }
        
         // Tampilkan frame baru dengan data siswa yang sudah diproses
        DataSiswa<T> processedFrame = new DataSiswa<>(hadirList, izinList, sakitList);
        processedFrame.setVisible(true);
            
        // Update tampilan antrean
        displayData();
    }
    

    private void resetData() {
        deques.clear();
        completedQueue.clear();
        DataArea.setText("");
        HadirArea.setText("");
        IjinArea.setText("");
        SakitArea.setText("");
        currentPatientLabel.setText("data sedang diproses: ");
    }

    private void displayData() {
        DataArea.setText("Daftar Siswa:\n");
        int antrean = 1;
        for (T item : deques) {
            DataArea.append(antrean + ". " + item.getNama() + " (Kelas: " + item.getKelas() + ", No Absen: " + item.getNoAbsen() + ", Wali Kelas: " + item.getWaliKelas() + ", Status: " + item.getStatus() + ")\n");
            antrean++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TampilanGui<QueueItem>().setVisible(true);  // Specify the generic type here
            }
        });
    }
}

class QueueItem {
    private String nama;
    private String kelas;
    private String noAbsen;
    private String waliKelas;
    private String status;

    public QueueItem(String nama, String kelas, String noAbsen, String waliKelas) {
        this.nama = nama;
        this.kelas = kelas;
        this.noAbsen = noAbsen;
        this.waliKelas = waliKelas;
    }

    public String getNama() {
        return nama;
    }

    public String getKelas() {
        return kelas;
    }

    public String getNoAbsen() {
        return noAbsen;
    }

    public String getWaliKelas() {
        return waliKelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
