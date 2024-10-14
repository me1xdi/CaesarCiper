import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {


    public String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }


    public void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(filePath))) {
            wr.write(content);
        }
    }
}