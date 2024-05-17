
import java.io.BufferedReader;
import java.io.FileReader;

public class caraMembacaFile {
    public static void main(String[] args) {
        try{
            FileReader reader = new FileReader(f);
            BufferedReader buff = new BufferedReader(reader);
            String x = buff.readLine();
            while(x != null){
                System.out.println(x);
                x = buff.readLine();
            }
            buff.close();
            reader.close();
        } catch(Exception e){
            System.out.println("File Tidak Ada");
        }
    }
}
