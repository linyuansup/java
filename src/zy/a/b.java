package zy.a;

import java.util.Scanner;

import static java.lang.Math.PI;

public class b {
    public static void main(String[] args) {
        // 创建 Scanner
        Scanner scanner = new Scanner(System.in);
        // 读取输入
        float v = scanner.nextFloat();
        System.out.println(v * v * v * 4 / 3 * PI);
    }
}
