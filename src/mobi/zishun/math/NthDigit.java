package mobi.zishun.math;

/*
 * 400. 第 N 位数字
给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
* 中文里的“数字”其实几乎可以指代任意数，512、0.98都是数字，但是digit却特指0~9的数字。
* 其实看题目的英文就比较明确了——findNthDigit，特指找到第N个digit（0~9，即十进制的一位）

示例 1：
输入：n = 3
输出：3
示例 2：
输入：n = 11
输出：0
解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
提示：
1 <= n <= 2^31 - 1
* https://leetcode.cn/problems/nth-digit/
 */
public class NthDigit {
    public int findNthDigit(int n) {
        if (n == 0) {
            return 0;
        }
        // 数位（个位/十位/百位/...，就是1/2/3/...）
        int digit = 1;
        // 属于该数位的所有数的起始点数（个位是1，十位是10，百位是100）
        long start = 1;
        // 该数位的数一共的索引个数（不是数字个数)
        long indexCount = 9;
        while (n > indexCount) {
            // 找出 n 属于那个数位里的索引
            n -= indexCount;
            digit++;
            start *= 10;
            // 2 * 9 * 10 = 180 (10到99所有的数位，90个两位数）
            indexCount = digit * 9 * start;
        }
        // 上面的循环结束后：
        // digit 等于原始的 n 所属的数位；start 等于原始的 n 所属数位的数的起始点
        // indexCount 等于原始的 n 所属数位的索引总个数（不重要了，下面不用）
        // n 等于在当前数位里的第 n - 1 个索引（索引从 0 开始算起）

        // 算出原始的 n 到底对应那个数字
        long num = start + (n - 1) / digit;
        // 余数就是原始的 n 是这个数字中的第几位
        int remainder = (n - 1) % digit;
        // 将该数字转为 string 类型
        String numString = String.valueOf(num);
        // numString的第remainder位即为答案
        return numString.charAt(remainder) - '0';
    }
}
