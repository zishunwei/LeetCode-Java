package mobi.zishun.math;

/*
 * 231. 2 的幂
给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。
示例 1：
输入：n = 1
输出：true
解释：2^0 = 1
示例 2：
输入：n = 16
输出：true
解释：2^4 = 16
https://leetcode-cn.com/problems/power-of-two/
 */
public class IsPowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        // & 表示按位与运算。该位运算技巧可以直接将 n 二进制表示的最低位 1 移除
        return n > 0 && (n & (n - 1)) == 0;
    }

}
