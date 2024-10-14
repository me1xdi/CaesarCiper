import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileRandWr {

    public String readFile(String filePath) throws IOException {
        try{
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(filePath))) {
            wr.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}