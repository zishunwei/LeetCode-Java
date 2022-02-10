package mobi.zishun.math;

import java.util.LinkedList;
import java.util.List;

/*
 * 1447. 最简分数
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
输入：n = 4
输出：["1/2","1/3","1/4","2/3","3/4"]
解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 */
public class SimplifiedFractions {
    public List<String> simplifiedFractions(int n) {
        List<String> res = new LinkedList<>();
        if (n == 1) {
            return res;
        }
        res.add("1/2");
        if (n == 2) {
            return res;
        }
        for (int i = 3; i <= n; i++) {
            res.add(1 + "/" + i);
            res.add((i - 1) + "/" + i);
            for (int j = 2; j < i - 1; j++) {
                if (getGreatestCommonDivisor(i, j) == 1) {
                    res.add(j + "/" + i);
                }
            }
        }
        return res;
    }

    // 求两个数的最大公因数，最大公约数（Greatest Common Divisor）缩写为GCD。
    // 欧几里得算法(辗转相除法)
    // a > b
    private int getGreatestCommonDivisor(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return getGreatestCommonDivisor(b, a % b);
    }

    public static void main(String[] args) {
        SimplifiedFractions m = new SimplifiedFractions();
        List<String> res = m.simplifiedFractions(4);
        System.out.println(res);
    }

}
