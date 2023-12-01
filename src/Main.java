import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

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

            int totalRequests = 0;
            int googleBotCount = 0;
            int yandexBotCount = 0;

            Statistics statistics = new Statistics();

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

                    if (line.length() > 1024) {
                        throw new MyExeptionClass("Строка в файле длиннее 1024 символов");
                    }

                }

                System.out.println("Общее количество строк в файле: " + totalLines);

                reader.close();
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line;

                while ((line = reader.readLine()) != null) {
                    String useragentPart = getuseragentPart(line);
                    LogEntry logEntry = new LogEntry(line);
                    statistics.addEntry(logEntry);
                    if (useragentPart.contains("Googlebot")) {
                        googleBotCount++;
                    } else if (useragentPart.contains("YandexBot")) {
                        yandexBotCount++;
                    }

                    totalRequests++;
                }
            } catch (IOException e) {
                System.out.println("Строка содержит не полные данные");
            }

            double googleBotRatio = (double) googleBotCount / totalRequests;
            double yandexBotRatio = (double) yandexBotCount / totalRequests;

            System.out.println("Доля запросов от GoogleBot: " + googleBotRatio);
            System.out.println("Доля запросов от YandexBot: " + yandexBotRatio);

            System.out.println("Statistics: " + statistics.getTrafficRate());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line;
                while ((line = reader.readLine()) != null) {
                    LogEntry logEntry = new LogEntry(line);
                    UserAgent userAgent = new UserAgent(line);

                    System.out.println("IP Address: " + logEntry.getIpAddress());
                    System.out.println("Date/Time: " + logEntry.getDateTime());
                    System.out.println("Method: " + logEntry.getHttpMethod());
                    System.out.println("Path: " + logEntry.getPath());
                    System.out.println("Response Code: " + logEntry.getResponseCode());
                    System.out.println("Data Size: " + logEntry.getDataSize());
                    System.out.println("HTTPS Referer: " + logEntry.getProtocol());
                    System.out.println("OS: " + userAgent.getOs());
                    System.out.println("Browser: " + userAgent.getBrowser());


                }

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

    }

    private static String getuseragentPart(String line) {

        String firstBrackets = line.substring(line.indexOf("(compatible") + 1);
        String[] parts = firstBrackets.split(";");
        if (parts.length >= 2) {
            String fragment = parts[1];
            int slashIndex = fragment.indexOf("/");
            if (slashIndex != -1) {
                fragment = fragment.substring(0, slashIndex);
            }
            return fragment;
        }
        return "";
    }

}