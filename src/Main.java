import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите текст и нажмите <Enter>:");
        String Text = new Scanner(System.in).nextLine();
        System.out.println("Длина текста: " + Text.length());
    }
}
