package mobi.zishun.dynamicprogramming;

/*
 * 416. 分割等和子集
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

示例 1：
输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：
输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。
提示：
1 <= nums.length <= 200
1 <= nums[i] <= 100
* https://leetcode-cn.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
    // 逆向DP
    public boolean canPartition(int[] nums) {
        // 本题要求把数组分成两个等和的子集，相当于找到一个子集，其和为 sum / 2，
        // 这个 sum / 2 就是 target（target 间接给出）
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        // 外层遍历 nums 每个 num
        for (int num : nums) {
            // 内层遍历 target（由大到小）
            for (int i = target; i >= num; i--) {
                // 对于元素之和等于 i - num 的每一种组合，在最后添加 num 之后即可得到一个元素之和等于 i 的组合，
                // 因此dp[i] 依赖于 dp[i - num]，并且在计算 dp[i - num] 时，要保证索引较小的元素值不被覆盖，
                // 需要后向更新 dp[i]，并且当 i - num < i 时， dp[i] 已经更新过，于是：
                // dp[i] = dp[i] || dp[i - num]
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }

}
