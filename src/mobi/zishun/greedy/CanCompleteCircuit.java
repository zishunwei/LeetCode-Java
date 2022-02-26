package mobi.zishun.greedy;

/*
 * 134. 加油站
在一条环路上有 n个加油站，其中第 i个加油站有汽油gas[i]升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
输出: 3
解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。
链接：https://leetcode-cn.com/problems/gas-station
 */
public class CanCompleteCircuit {
    // 注意：一条环路！
    // 思路：首先判断总油量是否小于总油耗，如果是则肯定不能走一圈。如果否，那肯定能跑一圈。
    // 接下来就是循环数组，从第一个站开始，计算每一站剩余的油量，如果油量为负了，就以这个站为起点从新计算。
    // 如果到达某一个点为负，说明起点到这个点中间的所有站点都不能到达该点。
    // 1 将每个加油站的剩余油量累加给left,即left+=gas[i]-cost[i],如果left<0,那么从出发站到i都不是起点；
    // 2 如果sum(gas)>=cost(gas)，那么问题一定有解。
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int start = 0;
        int totalSum = 0;
        int curSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += (gas[i] - cost[i]);
            curSum += gas[i] - cost[i];
            // 假设一定有解（有无解靠totalSum决定），不考虑当前站点的消耗，直接从下一个索引开始
            if (curSum < 0) {
                start = i + 1;
                curSum = 0;
            }
        }
        // 如果sum(gas) < cost(gas)，那么问题无有解。
        if (totalSum < 0) {
            return -1;
        }
        return start;
    }

    public static void main(String[] args) {
        CanCompleteCircuit m = new CanCompleteCircuit();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(m.canCompleteCircuit(gas, cost));
    }

}
