import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        int count = 1;
        while (true) {
            System.out.println("Введите путь:");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);

            boolean isDirectory = file.isDirectory();
            boolean fileExists = file.exists();
            boolean fileNotExists = Files.notExists(Path.of(path));

            if (isDirectory) {
                System.out.println("Указан путь не к файлу");
                continue;
            } else if (fileNotExists) {
                System.out.println("Такого файла не существует");
                continue;
            } else if (fileExists) {
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер N " + count);
            }
            count++;

            try (FileReader fileReader = new FileReader(path);
                 BufferedReader reader = new BufferedReader(fileReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line;
                int totalLines = 0;
                int maxLength = 0;
                int minLength = Integer.MAX_VALUE;

                while ((line = reader.readLine()) != null) {
                    totalLines++;
                    maxLength = Math.max(maxLength, line.length());
                    minLength = Math.min(minLength, line.length());

                    if (line.length() > 1024) {
                        throw new MyExeptionClass("Строка в файле длиннее 1024 символов");
                    }

                }

                System.out.println("Общее количество строк в файле: " + totalLines);
                System.out.println("Длина самой длинной строки в файле: " + maxLength);
                System.out.println("Длина самой короткой строки в файле: " + minLength);

                reader.close();
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

}




