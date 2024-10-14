
import java.io.File;
public class Validator {


    public boolean validFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    // Валидация ключа
    public boolean validKey(int key, int alphLength) {
        return key >= 0 && key < alphLength;
    }
}