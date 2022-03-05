package mobi.zishun.dynamicprogramming;

/*
 * 213. 打家劫舍 II
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
输入：nums = [2,7,9,3,1,5]
输出：4
解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
链接：https://leetcode-cn.com/problems/house-robber-ii
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robNoCycle(nums, n, 0, n - 1), robNoCycle(nums, n, 1, n));
    }

    private int robNoCycle(int[] nums, int n, int start, int end) {
        int[] dp = new int[n - 1];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start + 1], dp[0]);
        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(nums[start + i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 2];
    }

    public static void main(String[] args) {
        HouseRobberII m = new HouseRobberII();
        int[] nums = {2, 7, 9, 3, 1, 5};
        System.out.println(m.rob(nums));
    }


}
