package mobi.zishun.math;

/*
 * 461. 汉明距离
两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
示例 1：
输入：x = 1, y = 4
输出：2
解释：
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
上面的箭头指出了对应二进制位不同的位置。
示例 2：
输入：x = 3, y = 1
输出：1
提示：
0 <= x, y <= 2^31 - 1
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        // 异或操作，提取出x,y差异位
        // 汉明距离即为 diff 的二进制表示中 1 的数量。
        int diff = x ^ y;
        int res = 0;
        // 重复直到 diff=0 为止。这样计数器中就累计了 diff 的二进制表示中 1 的数量。
        while (diff > 0) {
            // 不断地检查 diff 的最低位，如果最低位为 1，那么令计数器加一
            res += diff & 1;
            // diff 整体右移一位，这样 ss 的最低位将被舍去
            diff >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(74 ^ 32); // 106
        // 74：10010010
        // 32：01000000
        //106：11010010
        HammingDistance m = new HammingDistance();
        System.out.println(m.hammingDistance(74,32)); // 4
    }

}
