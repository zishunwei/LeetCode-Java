package mobi.zishun.binarysearch;

/*
 * 29. 两数相除
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
返回被除数 dividend 除以除数 divisor 得到的商。
整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

示例 1:
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
示例 2:
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
提示：
被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
* https://leetcode.cn/problems/divide-two-integers/
 */
public class DivideTwoIntegers {
    // 由于题目限定了我们不能使用乘法、除法和 mod 运算符。
    // 我们可以先实现一个「倍增乘法」，
    // 然后利用对于 x 除以 y，结果 x / y 必然落在范围 [0, x] 的规律进行二分。
    public int divide(int dividend, int divisor) {
        // 允许使用long
        long x = dividend;
        long y = divisor;
        // 处理正负关系
        boolean flag = (x > 0 && y < 0) || (x < 0 && y > 0);
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        // 二分查找
        long left = 0;
        long right = x;
        while (left < right) {
            long mid = (right + left + 1) / 2;
            // 不允许用乘法，自己实现
            long product = multiply(mid, y);
            if (product > x) {
                right = mid - 1;
            } else if (product <= x) {
                left = mid;
            }
        }
        if (flag) {
            left = -left;
        }
        if (left > Integer.MAX_VALUE || left < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) left;
    }


    private long multiply(long x, long y) {
        long res = 0;
        while (y > 0) {
            if ((y & 1) == 1) {
                res += x;
            }
            y >>= 1;
            x += x;
        }
        return res;
    }


}
