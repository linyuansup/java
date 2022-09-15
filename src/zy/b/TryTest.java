package zy.b;

import java.util.Scanner;

public class TryTest {
    public static void main(String[] args) {
        int result = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            String s = scanner.nextLine();
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("你输的啥啊喂");
            return;
        }
        System.out.println(result);
    }
}
