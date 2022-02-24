package mobi.zishun.dynamicprogramming;

import java.util.Arrays;

/*
 * 300. 最长递增子序列
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; i++) {
            // i与i前面的元素比较
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 找比i小的元素，找到一个，就让当前序列的最长子序列长度加1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 简化输出流程，在dp过程中找到dp数组的最大值
            res  = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence m = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(m.lengthOfLIS(nums));
    }
}
