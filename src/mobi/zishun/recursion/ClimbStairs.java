package mobi.zishun.recursion;

import java.util.HashMap;
import java.util.LinkedHashMap;

/*
70. 爬楼梯
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
注意：给定 n 是一个正整数。
 */
public class ClimbStairs {
    HashMap<Integer, Integer> hashMap = new LinkedHashMap<>();

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        if (hashMap.containsKey(n)) {
            return hashMap.get(n);
        }
        int res = climbStairs(n - 1) + climbStairs(n - 2);
        hashMap.put(n, res);
        return res;
    }

    public int climbStairsSimply(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }


    public int climbStairsByLoop(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        // 爬楼梯问题 == 斐波拉契数列
        // 例子：到第4级走法 = 到第3级走法（加一步） + 到第2级走法（加两步）
        int ret = 0;
        int pre = 2;
        int prepre = 1;
        for (int i = 3; i <= n; ++i) {
            ret = pre + prepre;
            prepre = pre;
            pre = ret;
        }
        return ret;
    }
}