package mobi.zishun.binarysearch;

/*
69. Sqrt(x)
给你一个非负整数 x ，计算并返回x的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

链接：https://leetcode-cn.com/problems/sqrtx
 */
public class Sqrt {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long left = 1;
        long right = x;
        while (left <= right) {
            long medium = (right - left) / 2 + left;
            if (medium * medium > x) {
                right = medium - 1;
            } else if (medium * medium == x) {
                return (int) medium;
            } else {
//            else if (medium * medium < x) {
                if ((medium + 1) * (medium + 1) > x) {
                    return (int) medium;
                } else
                if ((medium + 1) * (medium + 1) == x) {
                    return (int) medium + 1;
//              else if ((medium + 1) * (medium + 1) < x) {
                } else {
                    left = medium + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Sqrt m = new Sqrt();
        System.out.println(m.mySqrt(2147395599));
    }
}
