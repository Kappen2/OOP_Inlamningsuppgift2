import java.io.*;
import java.time.LocalDate;

public class WriteFile extends ReadFile{
    ReadFile rf = new ReadFile();
    public void createVisitorFile() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/customervisits", true)))) {
            out.println(info);
            out.println(LocalDate.now());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
