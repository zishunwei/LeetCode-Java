package mobi.zishun.dynamicprogramming;

/*
 * 53. 最大子数组和
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
子数组 是数组中的一个连续部分。
* 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int res = nums[0];
        for (int num : nums) {
            pre = Math.max(num, pre + num);
            res = Math.max(res, pre);
        }
        return res;
    }

    public int maxSubArrayV2(int[] nums) {
        int n = nums.length;
        // states[i] 表示：以 nums[i] 结尾的连续子数组的最大和
        // 状态转移方程：dp[i]=max{nums[i],dp[i−1]+nums[i]}
        int[] states = new int[n];
        states[0] = nums[0];

        for (int i = 1; i < n; i++) {
            if (states[i - 1] > 0) {
                states[i] = states[i - 1] + nums[i];
            } else {
                states[i] = nums[i];
            }
        }

        int res = states[0];
        for (int i = 1; i < n; i++) {
            if (states[i] > res) {
                res = states[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSubArray m = new MaxSubArray();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(m.maxSubArrayV2(nums));
    }
}
