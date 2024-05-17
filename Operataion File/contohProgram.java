import java.io.FileInputStream;
import java.io.FileOutputStream;

public class contohProgram{
    public static void main(String[] args) {
        int kar;
        try{
            FileInputStream file1 = new FileInputStream("d:\\teks.txt");
            FileOutputStream fileout = new FileOutputStream("teks.txt");
            while((kar = file1.read()) != -1){
                fileout.write(kar);
                System.out.print((char)kar);
            }
            fileout.close();
            file1.close();
        } catch(Exception e){
            System.out.println("File Tidak Ada");
        }
        System.out.println("");
    }
}
