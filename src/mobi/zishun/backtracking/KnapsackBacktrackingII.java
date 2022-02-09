package mobi.zishun.backtracking;

/*
 * 0-1背包问题
 * 求可放进背包的物品的最大价值
 */
public class KnapsackBacktrackingII {
    private int[][] cache;

    /**
     * 解决背包问题的主函数
     *
     * @param w 物品的重量数组
     * @param v 物品的价值数组
     * @param C 背包容量
     * @return 最大价值
     */
    public int knapSack(int[] w, int[] v, int C) {
        int size = w.length;
        cache = new int[size][C + 1];
        // 从最后一个物品向前开始递归（自顶向下）
        return solveKS(w, v, size - 1, C);
    }

    /**
     * 解决背包问题的递归函数
     *
     * @param w        物品的重量数组
     * @param v        物品的价值数组
     * @param index    当前待选择的物品索引
     * @param capacity 当前背包有效容量
     * @return 最大价值
     */
    private int solveKS(int[] w, int[] v, int index, int capacity) {
        //基准条件：如果索引无效或者容量不足，直接返回当前价值0
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        //如果此子问题已经求解过，则直接返回上次求解的结果
        if (cache[index][capacity] != 0) {
            return cache[index][capacity];
        }
        //不放第index个物品所得价值
        int res = solveKS(w, v, index - 1, capacity);
        //放第index个物品所得价值（前提是：第index个物品可以放得下）
        if (w[index] <= capacity) {
            res = Math.max(res, v[index] + solveKS(w, v, index - 1, capacity - w[index]));
        }
        //添加子问题的解，便于下次直接使用
        cache[index][capacity] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] w = {2, 1, 3, 2};
        int[] v = {12, 10, 20, 15};
        KnapsackBacktrackingII m = new KnapsackBacktrackingII();
        int res = m.knapSack(w, v, 5);
        System.out.println(res);
    }

}
