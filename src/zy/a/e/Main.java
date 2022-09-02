package zy.a.e;

public class Main {
    public static void main(String[] args) {
        int x = 5, y = 3, z = 0;
        System.out.println(z + y * x++);
        System.out.println(y++ + x);
        System.out.println(y + x++);
        System.out.println(~x);
        System.out.println(x ^ y);
        System.out.println(x << y);
    }
}
