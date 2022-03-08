package mobi.zishun.greedy;

/*
 * 55. 跳跃游戏
给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个下标。
* 输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
链接：https://leetcode-cn.com/problems/jump-game
 */
public class JumpGame {
    // 贪心
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxJumpLength = 0;
        for (int i = 0; i < n; i++) {
            if (maxJumpLength >= i) {
                maxJumpLength = Math.max(maxJumpLength, i + nums[i]);
                if (maxJumpLength >= n-1){
                    return true;
                }
            }
        }
        return false;
    }

    // DP
    public boolean canJumpV2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] < i) {
                return false;
            }
            dp[i] = Math.max(dp[i - 1], i + nums[i]);
        }
        return true;
    }


    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(jumpGame.canJump(nums1));
        System.out.println(jumpGame.canJump(nums2));
    }

}
