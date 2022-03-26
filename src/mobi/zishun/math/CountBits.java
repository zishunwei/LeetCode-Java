package mobi.zishun.math;

/*
 * 338. 比特位计数
给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
输入：n = 5
输出：[0,1,1,2,1,2]
解释：
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
提示：
0 <= n <= 105
进阶：
很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 */
public class CountBits {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i <= n; i++) {
            // & 两边只要有一个为0，就为0
            if ((i & 1) == 1) { // 奇数
                // 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
                result[i] = result[i - 1] + 1;
            } else { // 偶数
                // 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。
                // 因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
                result[i] = result[i/2];
            }
        }
        return result;
    }

}
