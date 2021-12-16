package mobi.zishun.binarysearch;

/*
69. Sqrt(x)
给你一个非负整数 x ，计算并返回x的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

链接：https://leetcode-cn.com/problems/sqrtx
 */
public class Sqrt {
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        } else if (x == 1 || x == 2) {
            return 1;
        }

        long low = 1;
        long high = x;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid <= x && (mid + 1) * (mid + 1) > x) {
                return (int) mid;
            } else if (mid * mid > x) {
                high = mid - 1;
            } else if ((mid + 1) * (mid + 1) < x) {
                low = mid + 1;
            } else if ((mid + 1) * (mid + 1) == x) {
                return (int) mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
}
