import java.io.IOException;
import java.util.Scanner;
public class Main {

    private static final String EN_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String RU_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FileRandWr fileRandWr = new FileRandWr();
        Validator validator = new Validator();

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

        Ciper cipher = new Ciper(alphabet);

        System.out.println("Выберите режим работы:");
        System.out.println("1: Шифрование");
        System.out.println("2: Расшифровка");
        int menuMode = scan.nextInt();
        scan.nextLine();
        if (menuMode != 1 & menuMode != 2) {
            System.out.println("Выбран неверный режим работы.");
            return;
        }

//      /Users/me1xdi/Desktop/пример для шифровки.txt
        System.out.println("Введите путь к исходному файлу:");
        String inputFilePath = scan.nextLine();
        if (!validator.validFile(inputFilePath)) {
            System.out.println("Файл " + inputFilePath + " не найден.");
            return;
        }
//      /Users/me1xdi/Desktop/для расшифровки.txt
        System.out.println("Введите путь для вывода результата:");
        String outputFilePath = scan.nextLine();
        if (!validator.validFile(outputFilePath)) {
            System.out.println("Файл " + outputFilePath + " не найден." );
            return;
        }

        System.out.println("Введите ключ от 0 до " + (alphabet.length() - 1) + ":");
        int key = scan.nextInt();

        if (!validator.validKey(key, alphabet.length())) {
            System.out.println("Некорректный ключ. Он должен быть в диапазоне от 0 до " + (alphabet.length() - 1));
            return;
        }

        try {
            String fileContent = fileRandWr.readFile(inputFilePath);
            String result;

            if (menuMode == 1) {

                result = cipher.encrypt(fileContent, key);
                System.out.println("Файл успешно зашифрован.");
                fileRandWr.writeFile(outputFilePath, result);
                System.out.println("Результат записан в файл: " + outputFilePath);
            } else if (menuMode == 2) {

                result = cipher.decrypt(fileContent, key);
                System.out.println("Файл успешно расшифрован.");
                fileRandWr.writeFile(outputFilePath, result);
                System.out.println("Результат записан в файл: " + outputFilePath);
            }


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}