package mobi.zishun.binarysearch;

/*
69. Sqrt(x)
给你一个非负整数 x ，计算并返回x的 算术平方根 。
由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
提示：
0 <= x <= 2^31 - 1
链接：https://leetcode-cn.com/problems/sqrtx
 */
public class Sqrt {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int res = -1;
        while (left <= right) {
            int medium = (right - left) / 2 + left;
            long product = (long) medium * medium;
            if (product <= x) {
                // 结果是向下取整
                // 当product <= x时即可能触及答案
                res = medium;
                left = medium + 1;
            } else {
                right = medium - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Sqrt m = new Sqrt();
        System.out.println(m.mySqrt(1));
    }
}
