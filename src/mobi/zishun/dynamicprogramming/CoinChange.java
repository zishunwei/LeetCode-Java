package mobi.zishun.dynamicprogramming;

import java.util.Arrays;

/*
 * 322. 零钱兑换
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
你可以认为每种硬币的数量是无限的。
* 输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
链接：https://leetcode-cn.com/problems/coin-change
 */
public class CoinChange {
    // 动态规划
    public int coinChange(int[] coins, int amount) {
        // 初始化
        int[] states = new int[amount + 1];
        // 添加大于amount的默认值，如果结果依然大于amount即说明没有任何一种硬币组合能组成总金额
        Arrays.fill(states, amount + 1);
        states[0] = 0;
        // 动态规划
        // states[i]代表凑齐金额i所需的最小硬币个数
        // 从前往后迭代状态
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    // 取当前个数和states[i - coin] + 1个数小的那个
                    states[i] = Math.min(states[i], states[i - coin] + 1);
                }
            }
        }
        // 输出结果
        int res = states[amount];
        if (res <= amount) {
            return res;
        }
        return -1;
    }

    // 递归 + 记忆化
    public int coinChangeV2(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        cache = new Integer[amount + 1];
        return recursion(coins, amount);
    }

    private Integer[] cache;

    public int recursion(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (cache[amount] != null) {
            return cache[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = recursion(coins, amount - coin);
            // 注意：判断res>=0才添加进结果
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        // 结果没有被递归操作影响，说明有任何一种硬币组合能组成总金额，输出-1
        if (min == Integer.MAX_VALUE) {
            cache[amount] = -1;
            return -1;
        }
        cache[amount] = min;
        return min;
    }

    public static void main(String[] args) {
        CoinChange m = new CoinChange();
        int[] coins = {1, 2, 5};
        System.out.println(m.coinChange(coins, 11));
    }

}
