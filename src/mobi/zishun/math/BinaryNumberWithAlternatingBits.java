package mobi.zishun.math;

/*
 * 693. 交替位二进制数
给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
示例 1：
输入：n = 5
输出：true
解释：5 的二进制表示是：101
示例 2：
输入：n = 11
输出：false
解释：11 的二进制表示是：1011.
提示：
1 <= n <= 2^31 - 1
* https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
 */
public class BinaryNumberWithAlternatingBits {
    public boolean hasAlternatingBits(int n) {
        // n右移一位并与n进行异或计算
        int a = n ^ (n >> 1);
        // 检验a是否全为1，111...
        return (a & (a + 1)) == 0;
    }

}
