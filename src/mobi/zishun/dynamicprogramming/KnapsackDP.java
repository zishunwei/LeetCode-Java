package mobi.zishun.dynamicprogramming;

/*
 * 0-1背包问题（数据算法之美-40-初识动态规划）
 * 求可放进背包的最大重量
 */
public class KnapsackDP {
    /**
     * 解决背包问题的主函数
     * 需使用额外二维数组(n * (capacity + 1))
     *
     * @param items    物品的重量数组
     * @param capacity 背包容量
     * @return 最大价值
     */
    public int knapSack(int[] items, int capacity) {
        int n = items.length;
        boolean[][] states = new boolean[n][capacity + 1];

        states[0][0] = true; // 第一行的数据要特殊处理，可以利用哨兵优化
        // 生成第一行结果 (第一个物品放入背包的情况)
        if (items[0] <= capacity) {
            states[0][items[0]] = true;
        }
        // 动态规划状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) { // 不把第i个物品放入背包
                if (states[i - 1][j]) {
                    // 没有放入背包，当前层继承上层的状态
                    states[i][j] = true;
                }
            }
            for (int j = 0; j <= capacity - items[i]; j++) { //把第i个物品放入背包
                if (states[i - 1][j]) {
                    states[i][j + items[i]] = true;
                }
            }
        }
        // 输出结果（n-1层为最终结果）
        for (int j = capacity; j >= 0; j++) {
            if (states[n - 1][j]) {
                return j;
            }
        }
        return 0;
    }

    /**
     * 优化空间复杂度版本
     * 仅需额外一维数组(capacity + 1)
     * 迭代更新数组同一层
     */
    public int knapSackV2(int[] items, int capacity) {
        int n = items.length;
        boolean[] states = new boolean[capacity + 1];

        states[0] = true;
        if (items[0] <= capacity) {
            states[items[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            // 把第i个物品放入背包
            for (int j = capacity - items[i]; j >= 0; j--) {// j需要从大到小计算
                // 因为从小到大，当前阶段刚刚因为决策放入而加上去的重量，在后续遍历时会被当做上个阶段的true。
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }

        for (int j = capacity; j >= 0; j--) {
            if (states[j]) {
                return j;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        KnapsackDP m = new KnapsackDP();
        int[] items = {2, 2, 4, 6, 3};
        System.out.println(m.knapSackV2(items, 9));
    }

}
