package mobi.zishun.dynamicprogramming;

/*
 * 152. 乘积最大子数组
给你一个整数数组 nums，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
测试用例的答案是一个32-位 整数。
子数组 是数组的连续子序列。
输入: nums = [2,3,-2,4]
输出: 6
解释:子数组 [2,3] 有最大乘积 6。
链接：https://leetcode-cn.com/problems/maximum-product-subarray
 */
public class MaximumProductSubarray {
    // 这里的定义并不满足「最优子结构」
    // 有负数，会导致随时最大值和最小值互转，需要同时记录前i的最小值和最大值
    // dp[i] = max(nums[i] * pre_max, nums[i] * pre_min, nums[i]),
    // 这里0 不需要单独考虑, 因为当相乘不管最大值和最小值,都会置0
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int preMax = nums[0];
        int preMin = nums[0];
        int res = preMax;
        for (int i = 1; i < n; i++) {
            // 避免在求preMin的时候preMax已经更新了
            int curPreMax = preMax;
            int curPreMin = preMin;
            preMax = Math.max(nums[i] * curPreMax, Math.max(nums[i] * curPreMin, nums[i]));
            preMin = Math.min(nums[i] * curPreMax, Math.min(nums[i] * curPreMin, nums[i]));
            if (preMax > res) {
                res = preMax;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaximumProductSubarray m = new MaximumProductSubarray();
        int[] nums = {1, 2, -1, -2, 2, 1, -2, 1, 4, -5, 4};
        System.out.println(m.maxProduct(nums));
    }

}
