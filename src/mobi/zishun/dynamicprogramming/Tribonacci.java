package mobi.zishun.dynamicprogramming;

/*
 * 1137. 第 N 个泰波那契数
泰波那契序列 Tn 定义如下：
T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
输入：n = 4
输出：4
解释：
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
 */
public class Tribonacci {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        int first = 0;
        int second = 1;
        int third = 1;
        int res = 0;
        for (int i = 1; i < n; i++) {
            res = first + second + third;
            first = second;
            second = third;
            third = res;
        }
        return res;
    }

}
