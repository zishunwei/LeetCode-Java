package mobi.zishun.dynamicprogramming;

/*
 * 188. 买卖股票的最佳时机 IV
给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1：
输入：k = 2, prices = [2,4,1]
输出：2
解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
示例 2：
输入：k = 2, prices = [3,2,6,5,0,3]
输出：7
解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
提示：
0 <= k <= 100
0 <= prices.length <= 1000
0 <= prices[i] <= 1000
* https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class MaxProfitIV {
    // MaxProfitIII进阶版
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }
        int[] states = new int[2 * k];
        // 偶数位代表第i次买入持有股票的状态
        // 奇数位代表第i次卖出后空仓的状态
        for (int i = 0; i < 2 * k; i += 2) {
            states[i] = -prices[0];
        }
        // dp
        for (int i = 1; i < prices.length; i++) {
            for (int j = 2 * k - 1; j >= 1; j--) {
                if ((j & 1) == 0) { // 偶数位代表第i次买入持有股票的状态
                    states[j] = Math.max(states[j], states[j - 1] - prices[i]);
                } else { // 奇数位代表第i次卖出后空仓的状态
                    states[j] = Math.max(states[j], states[j - 1] + prices[i]);
                }
            }
            states[0] = Math.max(states[0], -prices[i]);
        }
        return states[2 * k - 1];
    }

}
