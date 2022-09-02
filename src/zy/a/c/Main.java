package zy.a.c;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger result = new BigInteger("0");
        int i = 1;
        do {
            // 每次加上阶乘后的值
            result = result.add(multi(i));
            i++;
            // 直到 100
        } while (i <= 100);
        System.out.println(result);
    }

    /**
     * 指定数的阶乘
     * @param a 阶乘的值
     * @return 阶乘后的值
     */
    private static BigInteger multi(int a) {
        BigInteger result = new BigInteger("1");
        for (int i = 1; i <= a; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
