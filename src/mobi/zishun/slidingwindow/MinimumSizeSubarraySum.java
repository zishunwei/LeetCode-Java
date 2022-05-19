package mobi.zishun.slidingwindow;

/*
 * 209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。
找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
* 并返回其长度。如果不存在符合条件的子数组，返回 0 。

示例 1：
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
示例 2：
输入：target = 4, nums = [1,4,4]
输出：1
示例 3：
输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0
提示：
1 <= target <= 10^9
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
进阶：
如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
* https://leetcode.cn/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {
    // 前缀和
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        int start = -1;
        int end = 0;
        int res = n + 1;
        while (end < n) {
            // start为-1时代表当前统计nums[0...end]的所有元素和
            int curSum = start == -1 ? nums[end] : nums[end] - nums[start];
            if (curSum < target) {
                end++;
            } else {
                res = Math.min(res, end - start);
                start++;
            }
        }
        return res == n + 1 ? 0 : res;
    }

}
