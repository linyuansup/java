package zy.a;

import java.util.Scanner;

public class a {
    public static void main(String[] args) {
        // 创建 Scanner
        Scanner scanner = new Scanner(System.in);
        // 读取数据
        float v = scanner.nextFloat();
        System.out.println(v * 9 / 5 + 32);
        scanner.close();
    }
}
