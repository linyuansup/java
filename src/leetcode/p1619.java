package leetcode;

import java.util.Arrays;

public class p1619 {
    public static void main(String[] args) {
        int[] arr = { 6, 0, 7, 0, 7, 5, 7, 8, 3, 4, 0, 7, 8, 1, 6, 8, 1, 1, 2, 4, 8, 1, 9, 5, 4, 3, 8, 5, 10, 8, 6, 6,
                1, 0, 6, 10, 8, 2, 3, 4 };
        System.out.println(new Solution().trimMean(arr));
    }
}

class Solution {
    public double trimMean(int[] arr) {
        int arrLen = (int) (arr.length * 0.05);
        Arrays.sort(arr);
        double result = 0;
        for (int i = arrLen; i < arr.length - arrLen; i++) {
            result += arr[i];
        }
        result /= (arr.length - arrLen * 2);
        return result;
    }
}