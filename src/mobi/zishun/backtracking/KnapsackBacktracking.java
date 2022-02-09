package mobi.zishun.backtracking;

/*
 * 0-1背包问题
 */
public class KnapsackBacktracking {

    /**
     * 解决背包问题的主函数
     *
     * @param items    物品的重量数组
     * @param capacity 背包容量
     * @return 最大价值
     */
    public int knapSack(int[] items, int capacity) {
        int size = items.length;
        // 从最后一个物品向前开始递归（自顶向下）
        solveKS(0, 0, items, size, capacity);
        return maxWight;
    }

    public int maxWight = 0; //存储背包中物品总重量的最大值

    /**
     * 解决背包问题的主函数
     *
     * @param index    当前遍历的物品索引
     * @param wight    已经放入背包的物品重量
     * @param items    物品的重量数组
     * @param size     物品个数
     * @param capacity 背包容量
     */
    public void solveKS(int index, int wight, int[] items, int size, int capacity) {
        if (wight == capacity || index == size) { // wight==capacity表示装满了;index==size表示已经考察完所有的物品
            if (wight > maxWight) {
                maxWight = wight;
            }
            return;
        }
        solveKS(index + 1, wight, items, size, capacity);
        if (wight + items[index] <= capacity) {// 已经超过可以背包承受的重量的时候，就不要再装了
            solveKS(index + 1, wight + items[index], items, size, capacity);
        }
    }

    public static void main(String[] args){
        KnapsackBacktracking m = new KnapsackBacktracking();
        int[] items = {2,4,5,6};
        int res = m.knapSack(items,13);
        System.out.println(res);
    }

}
