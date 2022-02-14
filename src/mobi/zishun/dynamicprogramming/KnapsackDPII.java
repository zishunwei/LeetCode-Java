package mobi.zishun.dynamicprogramming;

import java.util.Arrays;

/*
 * 0-1背包问题
 * 求可放进背包的物品的最大价值
 */
public class KnapsackDPII {
    /**
     * 解决背包问题的主函数
     *
     * @param weights  物品的重量数组
     * @param values   物品的价值数组
     * @param capacity 背包容量
     * @return 最大价值
     */
    public int knapSack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // states数组记录每个状态的总价值
        int[][] states = new int[n][capacity + 1];
        // states初始化赋值为-1
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                states[i][j] = -1;
            }
        }
        // 处理第一行
        states[0][0] = 0;
        if (weights[0] <= capacity) {
            states[0][weights[0]] = values[0];
        }
        // 动态规划
        for (int i = 1; i < n; i++) {
            // 第i个物品不放入背包
            for (int j = 0; j <= capacity; j++) {
                if (states[i - 1][j] != -1) {
                    // 继承上一层
                    states[i][j] = states[i - 1][j];
                }
            }
            // 第i个物品放入背包
            for (int j = 0; j <= capacity - weights[i]; j++) {
                if (states[i - 1][j] != -1) {
                    // 从上一层迭代增加，第i个物品放入后总物品价值curVal
                    int curTotalVal = states[i - 1][j] + values[i];
                    // 如果当前价值大于之前状态的价值，更新此状态位的价值
                    if (curTotalVal > states[i][j + weights[i]]) {
                        states[i][j + weights[i]] = curTotalVal;
                    }
                }
            }
        }
        // 输出结果，为states最后一行记录值最大值
        int res = 0;
        for (int j = 0; j <= capacity; j++) {
            int curVal = states[n - 1][j];
            if (curVal > res) {
                res = curVal;
            }
        }
        return res;
    }

    /**
     * 优化空间复杂度版本
     * 仅需额外一维数组(capacity + 1)
     * 迭代更新数组同一层
     */
    public int knapSackV2(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[] states = new int[capacity + 1];
        Arrays.fill(states, -1);

        states[0] = 0;
        if (weights[0] <= capacity) {
            states[weights[0]] = values[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = capacity - weights[i]; j >= 0; j--) {
                if (states[j] != -1) {
                    int curTotalVal = states[j] + values[i];
                    if (curTotalVal > states[j + weights[i]]) {
                        states[j + weights[i]] = curTotalVal;
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j <= capacity; j++) {
            if (states[j] > res) {
                res = states[j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] w = {2, 1, 3, 2};
        int[] v = {12, 10, 20, 15};
        KnapsackDPII m = new KnapsackDPII();
        int res = m.knapSackV2(w, v, 5);
        System.out.println(res);
    }


}
