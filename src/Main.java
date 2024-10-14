
import java.io.IOException;
import java.util.Scanner;
public class Main {
    // Определение алфавитов
    private static final String EN_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String RU_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        Validator validator = new Validator();

        // Выбор алфавита
        System.out.println("Выберите алфавит:");
        System.out.println("1 - Латиница");
        System.out.println("2 - Кириллица");
        int сhoiceАlphabet = scan.nextInt();
        scan.nextLine();

        String alphabet;
        if (сhoiceАlphabet == 1) {
            alphabet = EN_ALPHABET;
        } else if (сhoiceАlphabet == 2) {
            alphabet = RU_ALPHABET;
        } else {
            System.out.println("Алфавит выбран некорректно. Выход из программы.");
            return;
        }

        CaesarCiper cipher = new CaesarCiper(alphabet);

        System.out.println("Выберите режим работы:");
        System.out.println("1: Шифрование");
        System.out.println("2: Расшифровка");
        int menuMode = scan.nextInt();
        scan.nextLine();
//      /Users/me1xdi/Desktop/пример для шифровки.txt
        System.out.println("Введите путь к исходному файлу:");
        String inputFilePath = scan.nextLine();
//      /Users/me1xdi/Desktop/для расшифровки.txt
        System.out.println("Введите путь для сохранения результата:");
        String outputFilePath = scan.nextLine();

        System.out.println("Введите ключ (0-" + (alphabet.length() - 1) + "):");
        int key = scan.nextInt();

        if (!validator.validFile(inputFilePath)) {
            System.out.println("Файл не найден: " + inputFilePath);
            return;
        }

        if (!validator.validKey(key, alphabet.length())) {
            System.out.println("Некорректный ключ. Он должен быть в диапазоне от 0 до " + (alphabet.length() - 1));
            return;
        }

        try {
            String fileContent = fileHandler.readFile(inputFilePath);
            String result;

            if (menuMode == 1) {

                result = cipher.encrypt(fileContent, key);
                System.out.println("Файл успешно зашифрован.");
            } else if (menuMode == 2) {

                result = cipher.decrypt(fileContent, key);
                System.out.println("Файл успешно расшифрован.");
            } else {
                System.out.println("Выбран неверный режим работы.");
                return;
            }

            fileHandler.writeFile(outputFilePath, result);
            System.out.println("Результат записан в файл: " + outputFilePath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}